package com.kk2.user.contacts.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ahuo.tool.imageloader.GlideLoaderUtil;
import com.kk2.user.MyApp;
import com.kk2.user.R;
import com.kk2.user.contacts.cn.CNPinyin;
import com.kk2.user.contacts.stickyheader.StickyHeaderAdapter;
import com.kk2.user.entity.response.FriendsBean;

import java.util.List;


/**
 * Created by you on 2017/9/11.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactHolder> implements StickyHeaderAdapter<HeaderHolder> {

    private final List<CNPinyin<FriendsBean>> cnPinyinList;

    private Listener mListener;

    public ContactAdapter(List<CNPinyin<FriendsBean>> cnPinyinList) {
        this.cnPinyinList = cnPinyinList;
    }

    public void setListener(Listener listener){
        this.mListener=listener;
    }

    @Override
    public int getItemCount() {
        return cnPinyinList.size();
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact, parent, false));
    }


    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {
        final FriendsBean contact = cnPinyinList.get(position).data;
       //holder.iv_header.setImageResource(contact.getAvatar());
        GlideLoaderUtil.loadCircleImage(MyApp.getInstance().getApplicationContext(),contact.getAvatar(),-1,holder.iv_header,true);
        holder.tv_name.setText(contact.getFriendNick());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener!=null){
                    mListener.onItemClick(contact);
                }
            }
        });
    }

    @Override
    public long getHeaderId(int childAdapterPosition) {
        return cnPinyinList.get(childAdapterPosition).getFirstChar();
    }

    @Override
    public void onBindHeaderViewHolder(HeaderHolder holder, int childAdapterPosition) {
        holder.tv_header.setText(String.valueOf(cnPinyinList.get(childAdapterPosition).getFirstChar()));
    }

    @Override
    public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return new HeaderHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_contact_header, parent, false));
    }

   public interface Listener{
        void onItemClick(FriendsBean contact);
    }
}
