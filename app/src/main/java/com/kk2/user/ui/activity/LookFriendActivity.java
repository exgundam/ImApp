package com.kk2.user.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahuo.tool.imageloader.GlideLoaderUtil;
import com.kk2.user.MyApp;
import com.kk2.user.R;
import com.kk2.user.base.BaseTitleActivity;
import com.kk2.user.entity.other.ChatEntity;
import com.kk2.user.entity.response.FriendsBean;
import com.kk2.user.ui.widget.MyAppBar;

import butterknife.BindView;

public class LookFriendActivity extends BaseTitleActivity {

    @BindView(R.id.ivAvatar)
    ImageView ivAvatar;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvWxId)
    TextView tvWxId;
    @BindView(R.id.tvArea)
    TextView tvArea;
    @BindView(R.id.tvSendMsg)
    TextView tvSendMsg;

    public static final String INTENT_ENTITY = "intent_entity";

    private FriendsBean mFriendsBean;

    public static void startActivity(Activity activity, FriendsBean entity) {
        Intent intent = new Intent(activity, LookFriendActivity.class);
        intent.putExtra(INTENT_ENTITY, entity);
        activity.startActivity(intent);
    }


    @Override
    public MyAppBar.TitleConfig getTitleViewConfig() {
        return buildDefaultConfig("");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_look_friend;
    }


    @Override
    public void initData() {
        mFriendsBean = (FriendsBean) getIntent().getSerializableExtra(INTENT_ENTITY);
        tvName.setText(mFriendsBean.getFriendNick());
        tvWxId.setText("微信号："+mFriendsBean.getFriendId());
        tvArea.setText("地区："+mFriendsBean.getProvince()+"  "+mFriendsBean.getCity());
        GlideLoaderUtil.loadSquareAvatar(MyApp.getInstance().getApplicationContext(), mFriendsBean.getAvatar(), -1, ivAvatar);
        tvSendMsg.setOnClickListener(mClickListener);

    }

    @Override
    protected void onViewClick(View v) {
        super.onViewClick(v);
        switch (v.getId()) {
            case R.id.tvSendMsg:
                ChatEntity chatEntity = new ChatEntity();
                chatEntity.name = mFriendsBean.getFriendNick();
                chatEntity.friendId = mFriendsBean.getFriendId();
                ChatDetailActivity.startActivity(LookFriendActivity.this, chatEntity);
                break;
            default:
                break;
        }
    }

    @Override
    public void refresh() {

    }

    @Override
    public void setPresenter() {

    }
}
