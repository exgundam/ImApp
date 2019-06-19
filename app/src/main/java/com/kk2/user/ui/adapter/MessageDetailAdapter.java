package com.kk2.user.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahuo.tool.imageloader.GlideLoaderUtil;
import com.kk2.user.MyApp;
import com.kk2.user.R;
import com.kk2.user.entity.other.ChatEntity;
import com.kk2.user.entity.other.MessageChatEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageDetailAdapter extends RecyclerView.Adapter<MessageDetailAdapter.ViewHolder> {
    private Context context;
    private List<ChatEntity> items;
    private ClickListener clickListener;

    public MessageDetailAdapter(Context context, List<ChatEntity> items) {
        this.context = context;
        this.items = items;
    }

    public interface ClickListener {
        void onItemClick(View v, int position, List<MessageChatEntity> items);
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        ViewHolder viewHolder = null;

        switch (viewType) {
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_msg_list_left, parent, false);
                viewHolder = new ViewHolder(view);
                break;
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_msg_list_right, parent, false);
                viewHolder = new ViewHolder(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).type;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ChatEntity messageBean = items.get(position);
        holder.mItemMsg.setText(messageBean.text);
        GlideLoaderUtil.loadCircleImage(MyApp.getInstance().getApplicationContext(),messageBean.avatar,-1,holder.mHeadImg,true);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * ViewHolder
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_msg)
        TextView mItemMsg;
        @BindView(R.id.head_img)
        ImageView mHeadImg;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}