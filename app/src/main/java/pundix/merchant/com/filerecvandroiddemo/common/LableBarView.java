package pundix.merchant.com.filerecvandroiddemo.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import pundix.merchant.com.filerecvandroiddemo.R;

/**
 * Created by zzh on 2016/8/4.
 */
public class LableBarView extends LinearLayout {
    public static final int TITLE_SIZE_DEF_VALUE = 15;
    public static final int SUB_TITLE_DEF_VALUE = 15;
    public static final String TAG = "LableBarView";
    private TextView tvTitle;
    private TextView tvSubtitle;
    private ImageView ivIco;
    private ImageView icArrows;
    public TextView getTvTitle() {
        return tvTitle;
    }
    public TextView getTvSubtitle() {
        return tvSubtitle;
    }
    public LableBarView(Context context) {
        this(context, null, 0);
    }
    public LableBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LableBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = inflate(context, R.layout.layout_lablebar, this);
        ivIco = (ImageView) view.findViewById(R.id.ico);
        tvTitle = (TextView) view.findViewById(R.id.title);
        tvSubtitle = (TextView) view.findViewById(R.id.subtitle);
        icArrows = (ImageView) view.findViewById(R.id.arrows);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LableBarView);
        Drawable ico = typedArray.getDrawable(R.styleable.LableBarView_ico);

        boolean hasArrows = typedArray.getBoolean(R.styleable.LableBarView_has_arrows, true);

        int titleColor = typedArray.getColor(R.styleable.LableBarView_title_color, 0);
        String titleTxt = typedArray.getString(R.styleable.LableBarView_title);
        int titleSize = typedArray.getDimensionPixelSize(R.styleable.LableBarView_title_size, sp2px(context, TITLE_SIZE_DEF_VALUE));

        int subTitleColor = typedArray.getColor(R.styleable.LableBarView_subtitle_color, 0);
        String subTitleTxt = typedArray.getString(R.styleable.LableBarView_subtitle);
        int subTitleSize = typedArray.getDimensionPixelSize(R.styleable.LableBarView_subtitle_size, sp2px(context, SUB_TITLE_DEF_VALUE));

        int icoPadding = typedArray.getDimensionPixelSize(R.styleable.LableBarView_ico_padding, 0);

        icArrows.setVisibility(hasArrows ? VISIBLE : INVISIBLE);

        if (icoPadding > 0) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ivIco.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.setMargins(0, 0, icoPadding, 0);
            }
        }
        if (ico != null) {
            ivIco.setVisibility(VISIBLE);
            ivIco.setImageDrawable(ico);
        }
        if (titleTxt != null) {
            tvTitle.setText(titleTxt);
        }

        if (titleColor != 0) {
            tvTitle.setTextColor(titleColor);
        }

        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize);

        if (subTitleTxt != null) {
            tvSubtitle.setText(subTitleTxt);
        }

        if (subTitleColor != 0) {
            tvSubtitle.setTextColor(subTitleColor);
        }

        tvSubtitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, subTitleSize);

    }

    public int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public void setIco(Drawable drawable) {

        if (ivIco == null) {
            return;
        }

        if (drawable == null) {
            ivIco.setVisibility(GONE);
        } else {
            ivIco.setVisibility(VISIBLE);
            ivIco.setImageDrawable(drawable);
        }
    }
}

