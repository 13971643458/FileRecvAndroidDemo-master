package pundix.merchant.com.filerecvandroiddemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import pundix.merchant.com.filerecvandroiddemo.MainActivity;
import pundix.merchant.com.filerecvandroiddemo.R;
import pundix.merchant.com.filerecvandroiddemo.datamodel.ServiceModle;
import pundix.merchant.com.filerecvandroiddemo.utils.GlideCircleTransform;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private List<ServiceModle> mDatas;
    private Context mContext;

    public MyRecyclerAdapter(Context context, List<ServiceModle> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        ServiceModle serviceModle = mDatas.get(position);
        holder.tv_serviceTitle_1.setText(serviceModle.getBaseStore());
        holder.tv_serviceTitle_2.setText(serviceModle.getDistance());
        holder.tv_serviceTitle_3.setText(serviceModle.getExpires());
        holder.tv_distance.setText(serviceModle.getTerm());

        Glide.with(mContext)
                .load(serviceModle.getPath())
                .transform(new GlideCircleTransform(mContext))
                .into(holder.image_service);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_service,null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    class MyViewHolder extends ViewHolder {
        private ImageView image_service;
        private TextView tv_serviceTitle_1;
        private TextView tv_serviceTitle_2;
        private TextView tv_serviceTitle_3;
        private TextView tv_distance;

        public MyViewHolder(View view) {
            super(view);
            image_service = (ImageView) view.findViewById(R.id.image_service);
            tv_serviceTitle_1=(TextView) view.findViewById(R.id. tv_serviceTitle_1);
            tv_serviceTitle_2=(TextView) view.findViewById(R.id. tv_serviceTitle_2);
            tv_serviceTitle_3=(TextView) view.findViewById(R.id. tv_serviceTitle_3);
            tv_distance=(TextView) view.findViewById(R.id. tv_distance);
        }
    }
}