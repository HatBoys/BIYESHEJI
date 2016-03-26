package com.gdin.teach.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gdin.teach.R;
import com.gdin.teach.bean.BroadCastBean;

import java.util.List;

/**
 * Created by 黄培彦 on 16/3/26.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class BroadcastAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<BroadCastBean> mBroadCastBeanList;
    private Context mContext;

    public BroadcastAdapter() {
    }

    public BroadcastAdapter(List<BroadCastBean> broadCastBeanList, Context context) {
        mBroadCastBeanList = broadCastBeanList;
        mContext = context;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.item_broadcast_adapter, parent, false);
        return new MyViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyViewHolder mMyViewHolder = (MyViewHolder) holder;
        mMyViewHolder.tvTitle.setText(mBroadCastBeanList.get(position).getTitle());
        mMyViewHolder.tvContent.setText(mBroadCastBeanList.get(position).getContent());

        if (mOnItemClickListener != null) {
            mMyViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = mMyViewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(mMyViewHolder.itemView, pos);
                }
            });

            mMyViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = mMyViewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(mMyViewHolder.itemView, pos);
                    return false;
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return mBroadCastBeanList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvContent;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_broadcast_item_title);
            tvContent = (TextView) itemView.findViewById(R.id.tv_broadcast_item_content);
        }
    }
}
