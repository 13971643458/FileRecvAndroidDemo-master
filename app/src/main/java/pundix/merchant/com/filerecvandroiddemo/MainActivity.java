package pundix.merchant.com.filerecvandroiddemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import pundix.merchant.com.filerecvandroiddemo.activity.BaseActivity;
import pundix.merchant.com.filerecvandroiddemo.activity.ScanActivity;
import pundix.merchant.com.filerecvandroiddemo.adapter.ViewPagerFragmentAdapter;
import pundix.merchant.com.filerecvandroiddemo.fragment.MeFragment;
import pundix.merchant.com.filerecvandroiddemo.fragment.ServiceFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private String[] titleName = new String[]{"Service", "Me"};
    private List<Fragment> mFragmentList = new ArrayList<>();
    private TextView tv_pandi;
    private RelativeLayout linear_service;
    private RelativeLayout linear_me;
    private View view_service;
    private View view_me;
    private ViewPager mViewPager;
    private ViewPagerFragmentAdapter mViewPagerFragmentAdapter;
    private FragmentManager mFragmentManager;
    private View view_top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getSupportFragmentManager();
        setContentView(R.layout.activity_main);
        initFragmetList();
        mViewPagerFragmentAdapter = new ViewPagerFragmentAdapter(mFragmentManager, mFragmentList);
        initViews();
        initViewPager();
    }

    private void initFragmetList() {
        ServiceFragment serviceFragment = new ServiceFragment();
        MeFragment meFragment = new MeFragment();
        mFragmentList.add(serviceFragment);
        mFragmentList.add(meFragment);
    }

    private void initViews() {
        view_top= findViewById(R.id.view_top);
        tv_pandi = (TextView) findViewById(R.id.tv_pandi);
        linear_service = (RelativeLayout) findViewById(R.id.linear_service);
        linear_me = (RelativeLayout) findViewById(R.id.linear_me);
        view_service = findViewById(R.id.view_service);
        view_me = findViewById(R.id.view_me);
        mViewPager = (ViewPager) findViewById(R.id.ViewPagerLayout);
        linear_service.setOnClickListener(this);
        linear_me.setOnClickListener(this);

        SpannableStringBuilder style = new SpannableStringBuilder("PANDI-PANDI");
        style.setSpan(new ForegroundColorSpan(Color.WHITE), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style.setSpan(new ForegroundColorSpan(Color.YELLOW), 6, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv_pandi.setText(style);
    }

    public void initViewPager() {
        mViewPager.addOnPageChangeListener(new ViewPagetOnPagerChangedLisenter());
        mViewPager.setAdapter(mViewPagerFragmentAdapter);
        mViewPager.setCurrentItem(0);
        //titleTextView.setText(titleName[0]);
        updateBottomLinearLayoutSelect(true, false);
    }

    private void updateBottomLinearLayoutSelect(boolean f, boolean s) {
        linear_service.setSelected(f);
        linear_me.setSelected(s);
        if (f){
            view_service.setVisibility(View.VISIBLE);
            view_me.setVisibility(View.INVISIBLE);
            SpannableStringBuilder style = new SpannableStringBuilder("PANDI-PANDI");
            style.setSpan(new ForegroundColorSpan(Color.WHITE), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            style.setSpan(new ForegroundColorSpan(Color.YELLOW), 6, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv_pandi.setText(style);
        }else {
            view_service.setVisibility(View.INVISIBLE);
            view_me.setVisibility(View.VISIBLE);
            tv_pandi.setText("PANDI_PANDI");
        }
    }

    public void TitleIsShow(boolean IsShow){
        if (IsShow){
            view_top.setVisibility(View.VISIBLE);
        }else {
            view_top.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.linear_service:
                mViewPager.setCurrentItem(0);
                updateBottomLinearLayoutSelect(true, false);
                view_service.setVisibility(View.VISIBLE);
                view_me.setVisibility(View.INVISIBLE);

                SpannableStringBuilder style = new SpannableStringBuilder("PANDI-PANDI");
                style.setSpan(new ForegroundColorSpan(Color.WHITE), 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                style.setSpan(new ForegroundColorSpan(Color.YELLOW), 6, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                tv_pandi.setText(style);
                break;

            case R.id.linear_me:
                mViewPager.setCurrentItem(1);
                updateBottomLinearLayoutSelect(false, true);
                view_service.setVisibility(View.INVISIBLE);
                view_me.setVisibility(View.VISIBLE);

                tv_pandi.setText("PANDI_PANDI");
                break;
        }
    }

    class ViewPagetOnPagerChangedLisenter implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            boolean[] state = new boolean[titleName.length];
            state[position] = true;
            //titleTextView.setText(titleName[position]);
            updateBottomLinearLayoutSelect(state[0], state[1]);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

}

