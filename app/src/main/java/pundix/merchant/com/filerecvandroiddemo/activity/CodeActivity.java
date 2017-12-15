package pundix.merchant.com.filerecvandroiddemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import pundix.merchant.com.filerecvandroiddemo.R;
import pundix.merchant.com.filerecvandroiddemo.common.MLImageView;

public class CodeActivity extends BaseActivity {

    private ImageView image_qrCode;
    private MLImageView image_headPortrait;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        initViews();
    }

    private void initViews() {
        image_qrCode = (ImageView) findViewById(R.id.image_qrCode);
        image_headPortrait = (MLImageView) findViewById(R.id.image_headPortrait);


        Glide.with(CodeActivity.this)
                .load("http://img3.imgtn.bdimg.com/it/u=2117360506,3708551187&fm=11&gp=0.jpg")
                .into(image_headPortrait);

        Glide.with(CodeActivity.this)
                .load("http://www.liantu.com/images/2013/liantu.png")
                .into(image_qrCode);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.tv_back) {
            finish();
        }
    }
}
