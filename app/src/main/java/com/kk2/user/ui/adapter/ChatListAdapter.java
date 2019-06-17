package com.kk2.user.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kk2.user.R;
import com.kk2.user.entity.other.ChatEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {


    private List<ChatEntity> mDataList;
    private Listener mListener;

    public void setData(List<ChatEntity> dataList) {
        this.mDataList = dataList;
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chat_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final ChatEntity entity = mDataList.get(i);
        viewHolder.tvTitle.setText(entity.name);
        viewHolder.tvContent.setText(entity.text);
        int unReadCount = entity.unReadCount;
        viewHolder.tvUpReadCount.setVisibility(unReadCount > 0 ? View.VISIBLE : View.INVISIBLE);
        viewHolder.tvUpReadCount.setText(unReadCount + "");
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
        @BindView(R.id.tvContent)
        TextView tvContent;
        @BindView(R.id.tvUpReadCount)
        TextView tvUpReadCount;
        View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

            ButterKnife.bind(this, itemView);
        }
    }

    public interface Listener {
        void onItemClick(ChatEntity entity);
    }
}
