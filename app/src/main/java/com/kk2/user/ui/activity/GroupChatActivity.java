package com.kk2.user.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ahuo.tool.util.ToastUtil;
import com.kk2.user.R;
import com.kk2.user.base.BaseActivity;
import com.kk2.user.ui.adapter.GroupChatAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GroupChatActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private GroupChatAdapter mGroupChatAdapter;
    private List<String> mGroupChatList;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, GroupChatActivity.class);
        activity.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_group_chat;
    }

    @Override
    public void initData() {
        mGroupChatAdapter=new GroupChatAdapter();
        mGroupChatList=new ArrayList<>();
        mGroupChatList.add("群聊1");
        mGroupChatList.add("群聊2");
        mGroupChatAdapter.setData(mGroupChatList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mGroupChatAdapter);
        mGroupChatAdapter.setListener(new GroupChatAdapter.Listener() {
            @Override
            public void onItemClick(String entity) {
                ToastUtil.showToast(entity);
            }
        });

    }

    @Override
    public void refresh() {

    }

    @Override
    public void setPresenter() {

    }
}
