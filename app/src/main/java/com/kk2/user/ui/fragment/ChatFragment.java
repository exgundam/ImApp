package com.kk2.user.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ahuo.tool.util.MyLog;
import com.ahuo.tool.util.ToastUtil;
import com.alibaba.fastjson.JSON;
import com.kk2.user.R;
import com.kk2.user.base.BaseChatRsp;
import com.kk2.user.base.BaseTitleFragment;
import com.kk2.user.core.ChatMsgType;
import com.kk2.user.entity.other.ChatEntity;
import com.kk2.user.entity.response.FriendTalkRsp;
import com.kk2.user.local.UserInfo;
import com.kk2.user.ui.activity.AddFriendActivity;
import com.kk2.user.ui.activity.ChatDetailActivity;
import com.kk2.user.ui.adapter.ChatListAdapter;
import com.kk2.user.ui.widget.MyAppBar;
import com.kk2.user.util.MyUtils;
import com.zhangke.websocket.SimpleListener;
import com.zhangke.websocket.SocketListener;
import com.zhangke.websocket.WebSocketHandler;
import com.zhangke.websocket.response.ErrorResponse;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChatFragment extends BaseTitleFragment implements ChatListAdapter.Listener {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private ChatListAdapter mChatListAdapter;
    private List<ChatEntity> mMessageList;

    @Override
    public MyAppBar.TitleConfig getTitleViewConfig() {
        TextView textView = new TextView(getContext());
        textView.setText("添加好友");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFriendActivity.startActivity(getActivity());
            }
        });

        TextView textViewLeft = new TextView(getContext());
        textViewLeft.setText("发起群聊");
        textViewLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast("发起群聊");
            }
        });
        return buildDefaultConfig(getString(R.string.fragment_title_chat)).setRightView(new View[]{textView}).setLeftViews(new View[]{textViewLeft});
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chat;
    }

    @Override
    public void initData() {
        WebSocketHandler.getDefault().addListener(socketListener);
        mChatListAdapter = new ChatListAdapter();
        mChatListAdapter.setListener(this);
        mMessageList = new ArrayList<>();
       /* for (int i = 0; i < 20; i++) {
            ChatEntity entity=new ChatEntity();
            entity.name="小朋友"+i;
            entity.time="22：00";
            mMessageList.add(entity);
        }*/
        mChatListAdapter.setData(mMessageList);
        mChatListAdapter.setListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mChatListAdapter);

    }

    @Override
    public void refresh() {

    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void onItemClick(ChatEntity entity) {
        ChatDetailActivity.startActivity(getActivity(), entity);
    }

    private int getChatPos(String friendId) {
        for (int i = 0; i < mMessageList.size(); i++) {
            if (mMessageList.get(i).friendId.equals(friendId)) {
                return i;
            }
        }
        return -1;
    }

    private void receiveMsg(FriendTalkRsp rsp) {
        int pos=getChatPos(rsp.getFriendId());
        if ( pos>= 0) {
            ChatEntity entity = mMessageList.get(pos);
            entity.text = MyUtils.Base64DecodeUtf8(rsp.getContent());
            entity.friendId = rsp.getFriendId();
            entity.name = rsp.getFriendId();
            entity.time = "22：00";
            if (!entity.friendId.equals(UserInfo.inChatId)){
                entity.unReadCount++;
            }
            mMessageList.remove(entity);
            mMessageList.add(0, entity);

        } else {
            ChatEntity entity = new ChatEntity();
            entity.name = rsp.getFriendId();
            entity.friendId = rsp.getFriendId();
            entity.time = "22：00";
            entity.text = MyUtils.Base64DecodeUtf8(rsp.getContent());
            entity.unReadCount=1;
            mMessageList.add(0, entity);
        }
        mChatListAdapter.notifyDataSetChanged();


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
                FriendTalkRsp rsp = JSON.parseObject(baseResponse.message, FriendTalkRsp.class);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        WebSocketHandler.getDefault().removeListener(socketListener);
    }
}
