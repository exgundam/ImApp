package com.kk2.user.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kk2.user.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ahuo on 17-9-19.
 */

public class MyTabView extends LinearLayout {


    @BindView(R.id.ivTab)
    ImageView mIvTab;
    @BindView(R.id.tvContent)
    TextView mTvContent;
    @BindView(R.id.tvTip)
    TextView mTvTip;

    public MyTabView(Context context) {
        this(context, null);
    }

    public MyTabView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view= LayoutInflater.from(getContext()).inflate(R.layout.view_tab, this, true);
        ButterKnife.bind(this, view);
    }

    public MyTabView setImageResource(int imageResource) {
        mIvTab.setImageResource(imageResource);
        return this;
    }

    public MyTabView setTextContent(String value) {
        mTvContent.setText(value);
        return this;
    }

    public void setTipCount(int count){
        mTvTip.setVisibility(count>0?VISIBLE:INVISIBLE);
        mTvTip.setText(count+"");
    }

    public void setTextFocus(boolean focus) {
        mTvContent.setTextColor(focus ? getResources().getColor(R.color.colorPrimary) : getResources().getColor(R.color.public_text_title_color));
    }

}
