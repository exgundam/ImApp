package com.kk2.user.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ahuo.tool.util.MyLog;
import com.ahuo.tool.util.ToastUtil;
import com.alibaba.fastjson.JSON;
import com.kk2.user.R;
import com.kk2.user.base.BaseChatReq;
import com.kk2.user.base.BaseChatRsp;
import com.kk2.user.base.BaseTitleActivity;
import com.kk2.user.core.ChatMsgType;
import com.kk2.user.entity.Request.Content;
import com.kk2.user.entity.Request.DeviceAuthRsp;
import com.kk2.user.entity.Request.WeChatInfo;
import com.kk2.user.entity.other.ChatErrorRsp;
import com.kk2.user.entity.response.FriendPushNoticeRsp;
import com.kk2.user.entity.response.FriendsBean;
import com.kk2.user.entity.response.GetWeChatRsp;
import com.kk2.user.local.UserInfo;
import com.kk2.user.ui.widget.MyAppBar;
import com.kk2.user.util.MyUtils;
import com.zhangke.websocket.SimpleListener;
import com.zhangke.websocket.SocketListener;
import com.zhangke.websocket.WebSocketHandler;
import com.zhangke.websocket.response.ErrorResponse;

import java.nio.ByteBuffer;

import butterknife.BindView;

public class LoginActivity extends BaseTitleActivity {

    @BindView(R.id.etAccount)
    EditText etAccount;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.tvForgetPsw)
    TextView tvForgetPsw;
    @BindView(R.id.tvLogin)
    TextView tvLogin;

    private String SupplierId;
    private boolean isLogin;

    @Override
    public MyAppBar.TitleConfig getTitleViewConfig() {
        return buildDefaultConfig(getString(R.string.activity_title_login)).setLeftOnClickListener(null);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {
        tvLogin.setOnClickListener(mClickListener);
    }

    @Override
    protected void onViewClick(View v) {
        super.onViewClick(v);
        login();
    }

    private void login() {
        String account = etAccount.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            ToastUtil.showToast("请输入账号！");
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtil.showToast("请输入密码！");
        }
        WebSocketHandler.getDefault().addListener(socketListener);
        BaseChatReq request = new BaseChatReq();
        request.MsgType = ChatMsgType.DeviceAuthReq;
        request.Content = new Content();
        request.Content.AuthType = "2";
        String Credential = MyUtils.Base64EncodeToString(account + ":" + password);
        request.Content.Credential = Credential.trim();
        MyLog.e(JSON.toJSONString(request) + "--");
        WebSocketHandler.getDefault().send(JSON.toJSONString(request));
    }

    @Override
    public void refresh() {

    }

    @Override
    public void setPresenter() {

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
            // MyLog.e("onMessage(String, T):"+message.replace("\\n", "").replace("\\", ""));
            BaseChatRsp baseResponse = JSON.parseObject(message, BaseChatRsp.class);
            if (baseResponse.msgType.equals(ChatMsgType.DeviceAuthRsp)) {
                DeviceAuthRsp deviceAuthRsp = JSON.parseObject(baseResponse.message, DeviceAuthRsp.class);
                UserInfo.AccessToken = deviceAuthRsp.AccessToken;
                UserInfo.AccountType = deviceAuthRsp.Extra.AccountType;
                UserInfo.UnionId = deviceAuthRsp.Extra.Unionid;
                SupplierId = deviceAuthRsp.Extra.SupplierId;
                BaseChatReq getWeChat = new BaseChatReq();
                getWeChat.MsgType = ChatMsgType.GetWeChatsReq;
                getWeChat.Content = new Content();
                getWeChat.Content.AccountType = deviceAuthRsp.Extra.AccountType;
                getWeChat.Content.UnionId = deviceAuthRsp.Extra.Unionid;
                // Log.e("request===", JSON.toJSONString(getWeChat));
                WebSocketHandler.getDefault().send(JSON.toJSONString(getWeChat));

            } else if (baseResponse.msgType.equals(ChatMsgType.GetWeChatsRsp)) {
                GetWeChatRsp getWeChatRsp = JSON.parseObject(baseResponse.message, GetWeChatRsp.class);
                String weChatId = getWeChatRsp.getWeChats().get(0).getWeChatId();
                UserInfo.weChatId = weChatId;

                BaseChatReq loginNotice = new BaseChatReq();
                loginNotice.MsgType = ChatMsgType.WeChatLoginNotice;
                loginNotice.Content = new Content();
                loginNotice.Content.SupplierId = SupplierId;
                loginNotice.Content.UnionId = UserInfo.UnionId;
                loginNotice.Content.AccountType = UserInfo.AccountType;
                WeChatInfo weChatInfo = new WeChatInfo();
                weChatInfo.WeChatId = weChatId;
                weChatInfo.IsLogin = true;
                loginNotice.Content.WeChats = new WeChatInfo[]{weChatInfo};
                isLogin = true;
                WebSocketHandler.getDefault().send(JSON.toJSONString(loginNotice));


            } else if (baseResponse.msgType.equals(ChatMsgType.MsgReceivedAck)) {
                if (isLogin) {
                    isLogin = false;
                    BaseChatReq getFriend = new BaseChatReq();
                    getFriend.MsgType = ChatMsgType.TriggerFriendPushTask;
                    getFriend.Content = new Content();
                    getFriend.Content.WeChatId = UserInfo.weChatId;
                    //friendRequest.accesstoken=accessToken;
                    // Log.e("request===", JSON.toJSONString(getFriend));
                    WebSocketHandler.getDefault().send(JSON.toJSONString(getFriend));
                }

            } else if (baseResponse.msgType.equals(ChatMsgType.FriendPushNotice)) {
                FriendPushNoticeRsp friendPushNoticeRsp = JSON.parseObject(baseResponse.message, FriendPushNoticeRsp.class);
                for (FriendsBean friendsBean:friendPushNoticeRsp.getFriends()) {
                    UserInfo.friendsBeanHashMap.put(friendsBean.getFriendId(),friendsBean);
                }
                MainActivity.startActivity(LoginActivity.this);
                finish();
            } else if (baseResponse.msgType.equals(ChatMsgType.Error)) {
                ChatErrorRsp chatErrorRsp = JSON.parseObject(baseResponse.message, ChatErrorRsp.class);
                ToastUtil.showToast(chatErrorRsp.ErrorMsg);
            } else {

            }


            // MyLog.e("onMessage(String, T):" + message);
        }

        @Override
        public <T> void onMessage(ByteBuffer bytes, T data) {
            MyLog.e("onMessage(ByteBuffer, T):" + bytes);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WebSocketHandler.getDefault().removeListener(socketListener);
    }
}
