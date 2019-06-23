package com.kk2.user.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
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
import com.kk2.user.entity.request.Content;
import com.kk2.user.entity.request.ReqEntity;
import com.kk2.user.local.UserInfo;
import com.kk2.user.ui.widget.MyAppBar;
import com.kk2.user.util.MyUtils;
import com.zhangke.websocket.SimpleListener;
import com.zhangke.websocket.SocketListener;
import com.zhangke.websocket.WebSocketHandler;
import com.zhangke.websocket.response.ErrorResponse;

import java.nio.ByteBuffer;

import butterknife.BindView;

public class AddFriendActivity extends BaseTitleActivity {

    @BindView(R.id.etInputId)
    EditText etInputId;

    public static void startActivity(Activity activity) {
        Intent intent = new Intent(activity, AddFriendActivity.class);
        activity.startActivity(intent);
    }


    @Override
    public MyAppBar.TitleConfig getTitleViewConfig() {
        return buildDefaultConfig(getString(R.string.activity_title_add_friend));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_friend;
    }

    @Override
    public void initData() {
        WebSocketHandler.getDefault().addListener(socketListener);
        etInputId.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    MyUtils.hideSoftKeyboard(AddFriendActivity.this, etInputId);
                    addFriend();
                    return true;
                }
                return false;
            }
        });

    }

    private void addFriend() {
        String content = etInputId.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            ToastUtil.showToast("请输入");
            return;
        }
        String[] phones=new String[]{content};
        String addFriend = ReqEntity.getBuilder()
                .setMsgType(ChatMsgType.AddFriendsTask)
                .setWeChatId(UserInfo.weChatId)
                .setPhones(phones)
                .setTaskId(4)
                .setMessage("你好")
                .setRemark("friend")
                .buildJsonToString();
        WebSocketHandler.getDefault().send(addFriend);
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

            BaseChatRsp baseResponse = JSON.parseObject(message, BaseChatRsp.class);
            if (baseResponse.msgType.equals("MsgReceivedAck")) {
               ToastUtil.showToast("已发送");
            }
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
//{"msgType":"FriendTalkNotice","message":"{  "WeChatId": "wxid_mtoqu5cui1lm12",  "FriendId": "wxid_ingw614r1e4722",  "ContentType": "Text",  "Content": "5oiR6YCa6L+H5LqG5L2g55qE5pyL5Y+L6aqM6K+B6K+35rGC77yM546w5Zyo5oiR5Lus5Y+v5Lul5byA5aeL6IGK5aSp5LqG",  "MsgId": "4",  "msgSvrId": "7178915017499643410"}"}
}
