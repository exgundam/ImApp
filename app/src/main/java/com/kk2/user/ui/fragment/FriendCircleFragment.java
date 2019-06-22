package com.kk2.user.ui.fragment;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.kk2.user.R;
import com.kk2.user.base.BaseTitleFragment;
import com.kk2.user.entity.response.FriendCircleBean;
import com.kk2.user.ui.adapter.FriendCircleAdapter;
import com.kk2.user.ui.widget.MyAppBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by ahuo on 17-9-19.
 */

public class FriendCircleFragment extends BaseTitleFragment implements XRecyclerView.LoadingListener {
    @BindView(R.id.xRecyclerView)
    XRecyclerView mXRecyclerView;

    FriendCircleAdapter mFriendCircleAdapter;
    List<FriendCircleBean> mFriendCircleList;

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
        mFriendCircleAdapter = new FriendCircleAdapter(getContext());
        mFriendCircleList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            FriendCircleBean friendCircleBean = new FriendCircleBean();
            friendCircleBean.viewType = 1;
            mFriendCircleList.add(friendCircleBean);
        }
        mFriendCircleAdapter.setFriendCircleBeans(mFriendCircleList);
        mXRecyclerView.setAdapter(mFriendCircleAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mXRecyclerView.setLayoutManager(layoutManager);
        mXRecyclerView.setLoadingMoreEnabled(true);
        View header = LayoutInflater.from(getContext()).inflate(R.layout.header_friend_circle, null, false);
        mXRecyclerView.addHeaderView(header);
        mXRecyclerView.setLoadingListener(this);


    }

    @Override
    public void refresh() {

    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(() -> mXRecyclerView.refreshComplete(), 1000);

    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mXRecyclerView.loadMoreComplete();
                mXRecyclerView.setNoMore(true);
            }
        },1000);
       // new Handler().postDelayed(() -> , 1000);

    }
}
