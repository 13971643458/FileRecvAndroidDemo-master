package pundix.merchant.com.filerecvandroiddemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import pundix.merchant.com.filerecvandroiddemo.R;
import pundix.merchant.com.filerecvandroiddemo.utils.GlideCircleTransform;

public class MeFragment extends Fragment {
    private View view;
    private ImageView image_setting;
    private String imageUrl = "http://img3.imgtn.bdimg.com/it/u=1676759634,3205141129&fm=27&gp=0.jpg";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        view = getView();
        image_setting = (ImageView) view.findViewById(R.id.image_setting);

        Glide.with(getActivity())
                .load(imageUrl)
                .transform(new GlideCircleTransform(getActivity()))
                .into(image_setting);
    }
}
