package com.kk2.user.ui.fragment;

import android.view.View;
import android.widget.Button;

import com.kk2.user.R;
import com.kk2.user.base.BaseTitleFragment;
import com.kk2.user.core.AppConfig;
import com.kk2.user.ui.activity.MyWebViewActivity;
import com.kk2.user.ui.widget.MyAppBar;

import butterknife.BindView;

/**
 * Created by ahuo on 17-9-19.
 */

public class DiscoveryFragment extends BaseTitleFragment {
    @BindView(R.id.btSend)
    Button mTvDiscovery;

    @Override
    public void setPresenter() {

    }

    @Override
    public MyAppBar.TitleConfig getTitleViewConfig() {
        return buildDefaultConfig(getString(R.string.fragment_title_discovery));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_discovery;
    }

    @Override
    public void initData() {
        mTvDiscovery.setOnClickListener(mClickListener);

    }

    @Override
    protected void onViewClick(View v) {
        super.onViewClick(v);
        switch (v.getId()){
            case R.id.btSend:

                MyWebViewActivity.startActivity(getActivity(), AppConfig.API_HOST_WEB);

                break;

            default:
                break;
        }
    }

    @Override
    public void refresh() {

    }

}
