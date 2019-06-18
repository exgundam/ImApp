package com.kk2.user.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ahuo.tool.util.ToastUtil;
import com.kk2.user.R;
import com.kk2.user.base.BaseTitleFragment;
import com.kk2.user.contacts.adapter.ContactAdapter;
import com.kk2.user.contacts.cn.CNPinyin;
import com.kk2.user.contacts.cn.CNPinyinFactory;
import com.kk2.user.contacts.search.CharIndexView;
import com.kk2.user.contacts.stickyheader.StickyHeaderDecoration;
import com.kk2.user.entity.other.ChatEntity;
import com.kk2.user.entity.response.FriendsBean;
import com.kk2.user.ui.activity.ChatDetailActivity;
import com.kk2.user.ui.activity.GroupChatActivity;
import com.kk2.user.ui.widget.MyAppBar;
import com.kk2.user.util.ChatUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by ahuo on 17-9-19.
 */

public class ContactFragment extends BaseTitleFragment {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.charIndexView)
    CharIndexView mCharIndexView;
    @BindView(R.id.tvIndex)
    TextView mTvIndex;

    private ContactAdapter adapter;
    private ArrayList<CNPinyin<FriendsBean>> contactList = new ArrayList<>();
    private Subscription subscription;

    private List<FriendsBean> friendsBeanList;

    @Override
    public void setPresenter() {
    }

    @Override
    public MyAppBar.TitleConfig getTitleViewConfig() {
        TextView textView = new TextView(getContext());
        textView.setText("群聊");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupChatActivity.startActivity(getActivity());
            }
        });
        return buildDefaultConfig(getString(R.string.fragment_title_contact)).setRightView(new View[]{textView});
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contact;
    }

    @Override
    public void initData() {
    /*
            findViewById(R.id.bt_search).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SearchActivity.lanuch(MainActivity.this, contactList);
                }
            });
    */
        friendsBeanList = ChatUtils.getFriendList();
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ContactAdapter(contactList);
        adapter.setListener(new ContactAdapter.Listener() {
            @Override
            public void onItemClick(FriendsBean contact) {
                ToastUtil.showToast(contact.getFriendNick());
                ChatEntity entity = new ChatEntity();
                entity.name = contact.getFriendNick();
                entity.friendId = contact.getFriendId();
                ChatDetailActivity.startActivity(getActivity(), entity);
            }
        });
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new

                StickyHeaderDecoration(adapter));
        mCharIndexView.setOnCharIndexChangedListener(new CharIndexView.OnCharIndexChangedListener() {
            @Override
            public void onCharIndexChanged(char currentIndex) {
                for (int i = 0; i < contactList.size(); i++) {
                    if (contactList.get(i).getFirstChar() == currentIndex) {
                        linearLayoutManager.scrollToPositionWithOffset(i, 0);
                        return;
                    }
                }
            }

            @Override
            public void onCharIndexSelected(String currentIndex) {
                if (currentIndex == null) {
                    mTvIndex.setVisibility(View.INVISIBLE);
                } else {
                    mTvIndex.setVisibility(View.VISIBLE);
                    mTvIndex.setText(currentIndex);
                }
            }
        });

        getPinyinList();

    }

    private void getPinyinList() {
        subscription = Observable.create(new Observable.OnSubscribe<List<CNPinyin<FriendsBean>>>() {
            @Override
            public void call(Subscriber<? super List<CNPinyin<FriendsBean>>> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    List<CNPinyin<FriendsBean>> contactList = CNPinyinFactory.createCNPinyinList(friendsBeanList);
                    Collections.sort(contactList);
                    subscriber.onNext(contactList);
                    subscriber.onCompleted();
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<CNPinyin<FriendsBean>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<CNPinyin<FriendsBean>> cnPinyins) {
                        contactList.addAll(cnPinyins);
                        adapter.notifyDataSetChanged();
                    }
                });
    }


    @Override
    public void onDestroy() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
        super.onDestroy();
    }

    @Override
    public void refresh() {

    }


    @Override
    protected void onViewClick(View v) {
        super.onViewClick(v);
        switch (v.getId()) {

            default:
                break;
        }
    }
}
