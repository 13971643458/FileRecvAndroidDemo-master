package pundix.merchant.com.filerecvandroiddemo.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.paic.hyperion.core.hfqrcode.HFQRCode;
import com.paic.hyperion.core.hfqrcode.HFQRCodeConfig;
import com.paic.hyperion.core.hfqrcode.HFQRCodeDelegate;
import com.paic.hyperion.core.hfqrcode.HFQRCodeView;
import pundix.merchant.com.filerecvandroiddemo.R;
import pundix.merchant.com.filerecvandroiddemo.utils.PermissionsChecker;

public class ScanActivity extends BaseActivity implements HFQRCodeDelegate {

    private static final String TAG = ScanActivity.class.getSimpleName();
    private String photoPath;
    private HFQRCodeView mQRCodeView;
    //调用系统相册-选择图片
    private static final int IMAGE = 1;

    private PermissionsChecker mPermissionsChecker; // 权限检测器
    private static final int REQUEST_CODE = 0; // 请求码

    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        mQRCodeView = (HFQRCodeView) findViewById(R.id.zxingview);



        mPermissionsChecker = new PermissionsChecker(this);



        HFQRCodeConfig config = new HFQRCodeConfig(this);
        // 除去扫描框，其余部分阴影颜色 #3FFFFFFF
        config.mMaskColor = Color.parseColor("#4cFFFFFF");
        // 扫描框边角线的颜色
        config.mCornerColor = getResources().getColor(R.color.backgroundColor);
        // 扫描边框的颜色
        config.mBorderColor = Color.TRANSPARENT;
//      config.mCustomGridScanLineDrawable = getResources().getDrawable(R.mipmap.custom_grid_scan_line);
//      config.mCustomScanLineDrawable = getResources().getDrawable(R.mipmap.custom_scan_line);
        config.mScanLineColor = getResources().getColor(R.color.backgroundColor);
        // 扫描框距离 toolbar 底部的距离，没有 toolbar 的话就是顶部的间距
        config.mTopOffset = dp2px(this, 100);
        // Toolbar的高度，当有设置 qrcv_isCenterVertical 属性时
        // 通过该属性来修正有 Toolbar 时导致扫描框垂直居中的偏差，默认值为0dp
//      config.mToolbarHeight = dp2px(this, 50);
        // 扫描框是否垂直居中，该属性为 true 时会忽略 mTopOffset 属性，默认值为 false
//        config.mIsCenterVertical = true;
        config.mTipTextMargin = dp2px(this, 30);

        config.mQRCodeTipText = "Put the QRcode into the frame";
        config.mTipTextColor = getResources().getColor(R.color.whiteColor);
        config.mTipTextSize = dp2px(this, 14);
        mQRCodeView.setCustomConfig(config);
        mQRCodeView.setDelegate(this);
        mQRCodeView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mQRCodeView.setVisibility(View.VISIBLE);
            }
        }, 800);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        // 缺少权限时, 进入权限配置页面
        if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
            startPermissionsActivity();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        mQRCodeView.startCamera();
        mQRCodeView.startSpot();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
        mQRCodeView.stopSpot();
        mQRCodeView.stopCamera();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        mQRCodeView.onDestroy();
        super.onDestroy();
    }

    private void startPermissionsActivity() {
        PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Log.i(TAG, "result:" + result);
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
//        vibrate();
//        mQRCodeView.startSpot();

        mQRCodeView.stopSpot();
        mQRCodeView.stopCamera();

        if (!TextUtils.isEmpty(result)){
            Intent intent = new Intent(ScanActivity.this,ScanWebActivity.class);
            intent.putExtra("SCANSTAR",result);
            startActivity(intent);
        }
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        Log.e(TAG, "打开相机出错");
    }

    public void onClick(View v) {
        if (v.getId() == R.id.choose_qrcode_pic) {
            Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, IMAGE);
        } else if(v.getId() == R.id.imae_codeBack){
            finish();
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    /**
     * 根据路径获得图片并压缩，返回bitmap用于显示
     * @param filePath
     * @return
     */
    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 480, 800);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * 计算图片的缩放值
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            Toast.makeText(ScanActivity.this, " 缺少主要权限,无法运行", Toast.LENGTH_SHORT).show();
            finish();
        }

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case IMAGE:
                    // 获取选中图片的路径
                    Uri uri = data.getData();
                    photoPath = getRealPathFromURI(uri);
                    // 同步解析本地图片二维码，耗时操作，请在子线程中调用。
                    String result = HFQRCode.syncDecodeQRCode(getSmallBitmap(photoPath));

                    if (!TextUtils.isEmpty(result)){
                        Intent intent = new Intent(ScanActivity.this,ScanWebActivity.class);
                        intent.putExtra("SCANSTAR",result);
                        startActivity(intent);
                    }else {
                        Toast.makeText(ScanActivity.this, "二维码不合法", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public static int dp2px(Context context, float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }
}
