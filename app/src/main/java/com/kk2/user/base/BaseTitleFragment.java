package com.kk2.user.base;

import android.os.Bundle;
import android.view.View;

import com.kk2.user.ui.widget.MyAppBar;


/**
 * Created by ahuo on 17-9-19.
 */

public abstract class BaseTitleFragment<P extends BasePresenter> extends BaseFragment<P> {

    protected boolean mIsViewCreated;
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!mIsViewCreated) {
            mLLAppbar.setVisibility(View.VISIBLE);
            mAppBar.setTitleConfig(getTitleViewConfig());
            mIsViewCreated=true;
        }
    }


    public abstract MyAppBar.TitleConfig getTitleViewConfig();

    /**
     * 1> 设置左边返回按钮的整件
     * 2> 设置标题文本
     *
     * @param title
     * @return
     */
    public MyAppBar.TitleConfig buildDefaultConfig(String title) {
        MyAppBar.TitleConfig config = new MyAppBar.TitleConfig(title);
        return config;
    }



}
