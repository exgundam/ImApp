package com.kk2.user.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ahuo.tool.util.ToastUtil;
import com.kk2.user.R;
import com.kk2.user.base.BaseTitleActivity;
import com.kk2.user.entity.other.ChatEntity;
import com.kk2.user.entity.response.ChatRoomsBean;
import com.kk2.user.ui.adapter.GroupChatAdapter;
import com.kk2.user.ui.widget.MyAppBar;
import com.kk2.user.util.ChatUtils;

import java.util.List;

import butterknife.BindView;

public class GroupChatActivity extends BaseTitleActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private GroupChatAdapter mGroupChatAdapter;
    private List<ChatRoomsBean> mGroupChatList;

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
        mGroupChatList= ChatUtils.getChatRoomList();
        mGroupChatAdapter.setData(mGroupChatList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mGroupChatAdapter);
        mGroupChatAdapter.setListener(new GroupChatAdapter.Listener() {
            @Override
            public void onItemClick(ChatRoomsBean entity) {
                ToastUtil.showToast(entity.getNickName());
                ChatEntity chatEntity=new ChatEntity();
                chatEntity.name=entity.getNickName();
                chatEntity.friendId=entity.getUserName();
                ChatDetailActivity.startActivity(GroupChatActivity.this,chatEntity);
            }
        });

    }

    @Override
    public void refresh() {

    }

    @Override
    public void setPresenter() {

    }

    @Override
    public MyAppBar.TitleConfig getTitleViewConfig() {
        return buildDefaultConfig(getString(R.string.activity_title_chat_group));
    }
}
