package com.kk2.user.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ahuo.tool.util.MyLog;
import com.alibaba.fastjson.JSON;
import com.kk2.user.R;
import com.kk2.user.base.BaseChatReq;
import com.kk2.user.base.BaseChatRsp;
import com.kk2.user.base.BaseTitleActivity;
import com.kk2.user.core.ChatMsgType;
import com.kk2.user.entity.Request.Content;
import com.kk2.user.entity.other.ChatEntity;
import com.kk2.user.entity.other.MessageChatEntity;
import com.kk2.user.entity.response.FriendTalkRsp;
import com.kk2.user.local.UserInfo;
import com.kk2.user.ui.adapter.MessageDetailAdapter;
import com.kk2.user.ui.widget.MyAppBar;
import com.kk2.user.ui.widget.RecordButton;
import com.kk2.user.ui.widget.StateButton;
import com.kk2.user.util.ChatUiHelper;
import com.kk2.user.util.MyUtils;
import com.zhangke.websocket.SimpleListener;
import com.zhangke.websocket.SocketListener;
import com.zhangke.websocket.WebSocketHandler;
import com.zhangke.websocket.response.ErrorResponse;

import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChatDetailActivity extends BaseTitleActivity implements View.OnClickListener {

    @BindView(R.id.bt_message_send)
    StateButton mBtnSend;

    @BindView(R.id.et_message_input)
    EditText mEtMsgInput;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.llContent)
    LinearLayout mLlContent;
    @BindView(R.id.ivEmo)
    ImageView mIvEmo;
    @BindView(R.id.rlBottomLayout)
    RelativeLayout mRlBottomLayout;
    @BindView(R.id.llEmotion)
    LinearLayout mLlEmotion;
    @BindView(R.id.llAdd)
    LinearLayout mLlAdd;
    @BindView(R.id.ivAdd)
    ImageView mIvAdd;
    @BindView(R.id.btnAudio)
    RecordButton mBtnAudio;
    @BindView(R.id.ivAudio)
    ImageView mIvAudio;


    public static final String INTENT_ENTITY = "intent_entity";
    public static final int page_count = 10;
    private MessageDetailAdapter mAdapter;
    private List<MessageChatEntity> mList;
    private ChatEntity mChatEntity;

    public static void startActivity(Activity activity, ChatEntity entity) {
        Intent intent = new Intent(activity, ChatDetailActivity.class);
        intent.putExtra(INTENT_ENTITY, entity);
        activity.startActivity(intent);
    }

    private int j = 0;

    private void initRecycle() {
        //mBtnAudio

        //
        //2)配置Adapter
        mList = new ArrayList<>();
        mAdapter = new MessageDetailAdapter(this, mList);
        mRecyclerView.setAdapter(mAdapter);
      /*  mAdapter.setOnItemClickListener(new MessageDetailAdapter.ClickListener() {
            @Override
            public void onItemClick(View v, int position, List<MessageChatEntity> items) {

            }
        });*/
        //  mRecyclerView.scrollToPosition(mList.size() - 1);
//1)添加布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        //底部布局弹出,聊天列表上滑
        final ChatUiHelper mUiHelper = ChatUiHelper.with(this);
        mUiHelper.bindContentLayout(mLlContent)
                .bindttToSendButton(mBtnSend)
                .bindEditText(mEtMsgInput)
                .bindBottomLayout(mRlBottomLayout)
                .bindEmojiLayout(mLlEmotion)
                .bindAddLayout(mLlAdd)
                .bindToAddButton(mIvAdd)
                .bindToEmojiButton(mIvEmo)
                .bindAudioBtn(mBtnAudio)
                .bindAudioIv(mIvAudio)
                .bindEmojiData();

        mRecyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (bottom < oldBottom) {
                    mRecyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            if (mAdapter.getItemCount() > 0) {
                                mRecyclerView.smoothScrollToPosition(mAdapter.getItemCount() - 1);
                            }
                        }
                    });
                }
            }
        });
        //点击空白区域关闭键盘
        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mUiHelper.hideBottomLayout(false);
                mUiHelper.hideSoftInput();
                mEtMsgInput.clearFocus();
                mIvEmo.setImageResource(R.drawable.ic_emoji);
                return false;
            }
        });
        /*mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstCompletelyVisibleItemPosition = layoutManager.findFirstCompletelyVisibleItemPosition();
                MyLog.e("firstCompletelyVisibleItemPosition: " + firstCompletelyVisibleItemPosition);
                if (firstCompletelyVisibleItemPosition == 0) {
                    MyLog.e("direction -1: false顶部");//滑动到顶部
                    ArrayList<MessageChatEntity> arrayList = new ArrayList<>();
                    j++;
                    for (int i = 1; i <= page_count; i++) {
                        MessageChatEntity messageBean = new MessageChatEntity();
                        messageBean.setDate(new SimpleDateFormat("MM-dd HH:mm").format(new java.util.Date()));
                        messageBean.setMsg(i + "消息" + j);
                        messageBean.setType(2);
                        messageBean.setState(1);
                        arrayList.add(messageBean);
                    }
                    mList.addAll(0, arrayList);
                    mAdapter.notifyDataSetChanged();
                    mRecyclerView.scrollToPosition(page_count);
                    LinearLayoutManager mLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
                    mLayoutManager.scrollToPositionWithOffset(page_count, 0);

                }
                int lastCompletelyVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition();
                MyLog.e("lastCompletelyVisibleItemPosition: " + lastCompletelyVisibleItemPosition);
                if (lastCompletelyVisibleItemPosition == layoutManager.getItemCount() - 1) {
                    MyLog.e("direction -1: false底部");
                }
            }
        });*/

    }

    private List<MessageChatEntity> genData() {
        List<MessageChatEntity> mList = new ArrayList<>();
        String date = new SimpleDateFormat("MM-dd HH:mm").format(new java.util.Date());

        mList.add(new MessageChatEntity("你好啊", 1, date, 1));
        mList.add(new MessageChatEntity("请问在吗", 1, date, 1));
        mList.add(new MessageChatEntity("在的呢", 2, date, 1));
        mList.add(new MessageChatEntity("你好", 2, date, 1));
        mList.add(new MessageChatEntity("你好啊", 1, date, 1));
        mList.add(new MessageChatEntity("请问在吗", 1, date, 1));
        mList.add(new MessageChatEntity("在的呢", 2, date, 1));
        mList.add(new MessageChatEntity("你好", 2, date, 1));

        mList.add(new MessageChatEntity("你好啊", 1, date, 1));
        mList.add(new MessageChatEntity("请问在吗", 1, date, 1));


        return mList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_message_send: //发送
                String inputContent = mEtMsgInput.getText().toString();
                if (!TextUtils.isEmpty(inputContent)) {
                    MessageChatEntity messageBean = new MessageChatEntity();
                    messageBean.setDate(new SimpleDateFormat("MM-dd HH:mm").format(new java.util.Date()));
                    messageBean.setMsg(inputContent);
                    messageBean.setType(2);
                    messageBean.setState(1);

                    mList.add(messageBean);
                    mAdapter.notifyDataSetChanged();

                    mEtMsgInput.clearFocus();
                    mEtMsgInput.setSelected(false);
                    mEtMsgInput.setText("");
                    mRecyclerView.scrollToPosition(mList.size() - 1);
                    MyUtils.hideSoftKeyboard(this, mEtMsgInput);
                    //TalkToFriendTask
                    sendMsg(inputContent);
                }
                break;
        }
    }

    private void sendMsg(String msg) {
        BaseChatReq sendMsg = new BaseChatReq();
        sendMsg.MsgType = "TalkToFriendTask";
        sendMsg.Content = new Content();
        sendMsg.Content.WeChatId = UserInfo.weChatId;
        sendMsg.Content.ContentType = 1;
        sendMsg.Content.FriendId = mChatEntity.friendId;
        sendMsg.Content.Content = MyUtils.Base64EncodeUtf8(msg);
        WebSocketHandler.getDefault().send(JSON.toJSONString(sendMsg));
        MyLog.e("send" + JSON.toJSONString(sendMsg));
    }

    private void receiveMsg(FriendTalkRsp rsp) {
        MessageChatEntity messageBean = new MessageChatEntity();
        messageBean.setDate(new SimpleDateFormat("MM-dd HH:mm").format(new java.util.Date()));
        messageBean.setMsg(MyUtils.Base64DecodeUtf8(rsp.getContent()));
        messageBean.setType(1);
        messageBean.setState(0);

        mList.add(messageBean);
        mAdapter.notifyDataSetChanged();

        mRecyclerView.scrollToPosition(mList.size() - 1);
        // MyUtils.hideSoftKeyboard(this, mMsgInput);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_detail;
    }

    @Override
    public void initData() {
        WebSocketHandler.getDefault().addListener(socketListener);
        mBtnSend.setOnClickListener(this);
        initRecycle();
        // TODO: 2019-06-13
        //通知首页减掉未读消息
    }

    @Override
    public void refresh() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        WebSocketHandler.getDefault().removeListener(socketListener);
        UserInfo.inChatId = null;
    }

    @Override
    public MyAppBar.TitleConfig getTitleViewConfig() {
        mChatEntity = getIntent().getParcelableExtra(INTENT_ENTITY);
        UserInfo.inChatId = mChatEntity.friendId;
        MyLog.e(mChatEntity.name);
        return buildDefaultConfig(mChatEntity.name);
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
            if (baseResponse.msgType.equals(ChatMsgType.FriendTalkNotice)) {
                FriendTalkRsp rsp = JSON.parseObject(baseResponse.message, FriendTalkRsp.class);
                receiveMsg(rsp);
            }
        }

        @Override
        public <T> void onMessage(ByteBuffer bytes, T data) {
            MyLog.e("onMessage(ByteBuffer, T):" + bytes);
        }
    };

}
