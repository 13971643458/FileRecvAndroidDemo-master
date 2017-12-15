package pundix.merchant.com.filerecvandroiddemo.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.*;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import pundix.merchant.com.filerecvandroiddemo.MainActivity;
import pundix.merchant.com.filerecvandroiddemo.R;
import pundix.merchant.com.filerecvandroiddemo.activity.CodeActivity;
import pundix.merchant.com.filerecvandroiddemo.activity.ScanActivity;
import pundix.merchant.com.filerecvandroiddemo.adapter.MyRecyclerAdapter;
import pundix.merchant.com.filerecvandroiddemo.common.RecyclerViewDivider;
import pundix.merchant.com.filerecvandroiddemo.datamodel.ServiceModle;

import java.util.ArrayList;
import java.util.List;

public class ServiceFragment extends Fragment implements View.OnClickListener ,AppBarLayout.OnOffsetChangedListener {

    private static final String TAG = "ServiceFragment";
    private View view;
    private RecyclerView recyclerView;
    private TextView tv_scan;
    private TextView tv_RQCode;
    private MyRecyclerAdapter recycleAdapter;
    private List<ServiceModle> mDatas;
    private View service_in;
    private View service_bottom;
    private View view_serviceTitle;

    private AppBarLayout abl_bar;
    private int mMaskColor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_service, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        view = getView();
        mMaskColor = getResources().getColor(R.color.backgroundColor);
        initViews();
        initData();
    }

    private void initViews() {
        tv_scan = (TextView) view.findViewById(R.id.tv_scan);
        tv_RQCode = (TextView) view.findViewById(R.id.tv_RQCode);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_content);
        abl_bar = (AppBarLayout) view.findViewById(R.id.abl_bar);
        service_in = view.findViewById(R.id.service_in);
        service_bottom = view.findViewById(R.id.service_bottom);
        view_serviceTitle = view.findViewById(R.id.view_serviceTitle);

        tv_scan.setOnClickListener(this);
        tv_RQCode.setOnClickListener(this);
        abl_bar.addOnOffsetChangedListener(this);
    }

    private void initData() {
        mDatas = new ArrayList<>();
        ServiceModle serviceModler1 =new ServiceModle();
        serviceModler1.setBaseStore("7-11 Softwre Base Stor");
        serviceModler1.setDistance("10% Off for All goods");
        serviceModler1.setExpires("Expires Soon");
        serviceModler1.setPath("http://img1.imgtn.bdimg.com/it/u=1545018350,2636164307&fm=11&gp=0.jpg");
        serviceModler1.setTerm("1.5km");
        mDatas.add(serviceModler1);

        ServiceModle serviceModler2 =new ServiceModle();
        serviceModler2.setBaseStore("Little Bee Central Park");
        serviceModler2.setDistance("30% Off for All today");
        serviceModler2.setExpires("Expires Soon");
        serviceModler2.setPath("http://img3.imgtn.bdimg.com/it/u=3161033144,832028455&fm=11&gp=0.jpg");
        serviceModler2.setTerm("2km");
        mDatas.add(serviceModler2);

        ServiceModle serviceModler3 =new ServiceModle();
        serviceModler3.setBaseStore("super Lunch Campus");
        serviceModler3.setDistance("20% Off Lunch");
        serviceModler3.setExpires("Expires Soon");
        serviceModler3.setPath("http://img1.imgtn.bdimg.com/it/u=1119956128,340159857&fm=11&gp=0.jpg");
        serviceModler3.setTerm("1km");
        mDatas.add(serviceModler3);

        recycleAdapter = new MyRecyclerAdapter(getContext(), mDatas);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        recyclerView.setAdapter(recycleAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addItemDecoration(new RecyclerViewDivider(getActivity(), LinearLayoutManager.VERTICAL, 80, ContextCompat.getColor(getActivity(), R.color.divide_gray_color)));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_scan:
                startActivity(new Intent(getActivity(), ScanActivity.class));
                break;
            case R.id.tv_RQCode:
                startActivity(new Intent(getActivity(), CodeActivity.class));
                break;
        }
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        Log.d(TAG, "verticalOffset="+verticalOffset);
        int offset = Math.abs(verticalOffset);
        int total = appBarLayout.getTotalScrollRange();
        int alphaIn = offset;
        int alphaOut = (200-offset)<0?0:200-offset;
        int maskColorIn = Color.argb(alphaIn, Color.red(mMaskColor), Color.green(mMaskColor), Color.blue(mMaskColor));
        int maskColorInDouble = Color.argb(alphaIn*2, Color.red(mMaskColor), Color.green(mMaskColor), Color.blue(mMaskColor));
        int maskColorOut = Color.argb(alphaOut*2, Color.red(mMaskColor), Color.green(mMaskColor), Color.blue(mMaskColor));
        if (offset <= total / 2) {
            //service_in.setVisibility(View.VISIBLE);
            //tl_collapse.setVisibility(View.GONE);
            //v_expand_mask.setBackgroundColor(maskColorInDouble);
        } else {
            //service_in.setVisibility(View.GONE);
            //tl_collapse.setVisibility(View.VISIBLE);
            //v_collapse_mask.setBackgroundColor(maskColorOut);
        }
        //v_pay_mask.setBackgroundColor(maskColorIn);
    }
}
