package pundix.merchant.com.filerecvandroiddemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;
import pundix.merchant.com.filerecvandroiddemo.R;

public class ScanWebActivity extends BaseActivity{

    private String webStr = "";
    private TextView tv_scanStr ;
    private WebView webview ;

    private WebSettings mWebSettings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_web);

        webStr = getIntent().getStringExtra("SCANSTAR");

        initViews();
    }

    private void initViews(){
        tv_scanStr = (TextView) findViewById(R.id.tv_scanStr);
        webview = (WebView) findViewById(R.id.webview);

        mWebSettings = webview.getSettings();
        mWebSettings.setDomStorageEnabled(true);

        if (webStr.startsWith("http")){
            webview.loadUrl(webStr);
            webview.setVisibility(View.VISIBLE);
            tv_scanStr.setVisibility(View.GONE);
        }else {
            tv_scanStr.setText(webStr);
            webview.setVisibility(View.GONE);
            tv_scanStr.setVisibility(View.VISIBLE);
        }
    }

    public void onClick(View v) {
        if (v.getId() == R.id.image_back) {
            finish();
        }
    }
}
