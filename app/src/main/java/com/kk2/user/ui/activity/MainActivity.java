package com.kk2.user.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TabHost;

import com.ahuo.tool.util.MyLog;
import com.ahuo.tool.util.ToastUtil;
import com.alibaba.fastjson.JSON;
import com.kk2.user.R;
import com.kk2.user.base.BaseActivity;
import com.kk2.user.base.BaseChatRsp;
import com.kk2.user.core.ChatMsgType;
import com.kk2.user.entity.other.MessageChatEntity;
import com.kk2.user.entity.response.FriendPushNoticeRsp;
import com.kk2.user.entity.response.FriendTalkRsp;
import com.kk2.user.local.UserInfo;
import com.kk2.user.ui.fragment.ChatFragment;
import com.kk2.user.ui.fragment.ContactFragment;
import com.kk2.user.ui.fragment.DiscoveryFragment;
import com.kk2.user.ui.fragment.PersonFragment;
import com.kk2.user.ui.widget.MyTabView;
import com.kk2.user.util.MyUtils;
import com.zhangke.websocket.SimpleListener;
import com.zhangke.websocket.SocketListener;
import com.zhangke.websocket.WebSocketHandler;
import com.zhangke.websocket.response.ErrorResponse;

import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

import static com.kk2.user.ui.fragment.ContactFragment.ARGUMENT;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tabContent)
    FrameLayout mTabContent;
    @BindView(R.id.tabHost)
    FragmentTabHost mTabHost;
    private FriendPushNoticeRsp mFriendPushNoticeRsp;
    private static final String INTENT_ENTITY = "intent_entity";
    private Class[] mClassFragments = {ChatFragment.class, ContactFragment.class, DiscoveryFragment.class, PersonFragment.class};
    private String[] mStrTab;
    private int[] mIRTab = {R.drawable.bg_tab_selector_main, R.drawable.bg_tab_selector_main, R.drawable.bg_tab_selector_discover, R.drawable.bg_tab_selector_person};
    private boolean mIsExit;


    public static void startActivity(Activity activity, FriendPushNoticeRsp friendPushNoticeRsp) {
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra(INTENT_ENTITY, friendPushNoticeRsp);
        activity.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        WebSocketHandler.getDefault().addListener(socketListener);
        mFriendPushNoticeRsp=getIntent().getParcelableExtra(INTENT_ENTITY);
        mStrTab = getResources().getStringArray(R.array.main_tab_tag);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.tabContent);
        for (int i = 0; i < mClassFragments.length; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mStrTab[i]).setIndicator(getTabView(i));
            Bundle bundle = null;
            if (i==1){
                bundle = new Bundle();
                bundle.putParcelable(ARGUMENT, mFriendPushNoticeRsp);
            }
            mTabHost.addTab(tabSpec, mClassFragments[i],bundle);
        }
        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);

    }


    private MyTabView getTabView(int position) {
        MyTabView tabView = new MyTabView(this);
        tabView.setImageResource(mIRTab[position]).setTextContent(mStrTab[position]);
        return tabView;
    }

    @Override
    public void refresh() {

    }

    @Override
    public void setPresenter() {

    }


    @Override
    public void onBackPressed() {
        exitByDoubleClick();
    }

    private void exitByDoubleClick() {
        Timer tExit;
        if (!mIsExit) {
            mIsExit = true;
            ToastUtil.showToast(getString(R.string.public_str_app_back_hint));
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    mIsExit = false;
                }
            }, 2000);

        } else {
            //退出操作
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WebSocketHandler.getDefault().removeListener(socketListener);
    }
    private int test;
    private void receiveMsg(FriendTalkRsp rsp) {
        MessageChatEntity messageBean = new MessageChatEntity();
        messageBean.setDate(new SimpleDateFormat("MM-dd HH:mm").format(new java.util.Date()));
        messageBean.setMsg(MyUtils.Base64DecodeUtf8(rsp.getContent()));
        messageBean.setType(1);
        messageBean.setState(0);
        MyTabView view= (MyTabView) mTabHost.getTabWidget().getChildTabViewAt(0);
        if (!rsp.getFriendId().equals(UserInfo.inChatId)) {
            view.setTipCount(++test);
        }
        // MyUtils.hideSoftKeyboard(this, mMsgInput);
    }

    private SocketListener socketListener = new SimpleListener() {
        @Override
        public void onConnected() {
            MyLog.e("onConnected");
        }

        @Override
        public void onConnectFailed(Throwable e) {
            if (e != null) {
                MyLog.e("onConnectFailed:" + e.toString());
            } else {
                MyLog.e("onConnectFailed:null");
            }
        }

        @Override
        public void onDisconnect() {
            MyLog.e("onDisconnect");
        }

        @Override
        public void onSendDataError(ErrorResponse errorResponse) {
            MyLog.e("onSendDataError:" + errorResponse.toString());
            errorResponse.release();
        }

        @Override
        public <T> void onMessage(String message, T data) {
            //String  message2=message.replaceAll("\"","")+"---";
            message = message.replace("\\n", "");
            BaseChatRsp baseResponse = JSON.parseObject(message, BaseChatRsp.class);
            if (baseResponse.msgType.equals(ChatMsgType.FriendTalkNotice)) {
                FriendTalkRsp rsp = JSON.parseObject(baseResponse.message,FriendTalkRsp.class);
                receiveMsg(rsp);
            }

            Log.e("response----===", message);
            MyLog.e("onMessage(String, T):" + message);
        }

        @Override
        public <T> void onMessage(ByteBuffer bytes, T data) {
            MyLog.e("onMessage(ByteBuffer, T):" + bytes);
        }
    };


}
