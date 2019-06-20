package com.kk2.user.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahuo.tool.imageloader.GlideLoaderUtil;
import com.kk2.user.MyApp;
import com.kk2.user.R;
import com.kk2.user.entity.response.ChatRoomsBean;
import com.kk2.user.ui.activity.ChatDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupChatAdapter extends RecyclerView.Adapter<GroupChatAdapter.ViewHolder> {


    private List<ChatRoomsBean> mDataList;
    private Listener mListener;

    public void setData(List<ChatRoomsBean> dataList) {
        this.mDataList = dataList;
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_group_chat_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final ChatRoomsBean entity = mDataList.get(i);
        viewHolder.tvTitle.setText(entity.getNickName());
        GlideLoaderUtil.loadCircleImage(MyApp.getInstance().getApplicationContext(), entity.getAvatar(), -1, viewHolder.ivAvatar, true);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(entity);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mDataList == null || mDataList.isEmpty()) {
            return 0;
        }
        return mDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.ivAvatar)
        ImageView ivAvatar;
        View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

            ButterKnife.bind(this, itemView);
        }
    }

    public interface Listener {
        void onItemClick(ChatRoomsBean entity);
    }
}
