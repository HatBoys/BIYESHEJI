package com.gdin.teach.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gdin.teach.Constan;
import com.gdin.teach.R;
import com.gdin.teach.activity.MainActivityTeacher;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by 黄培彦 on 16/3/21.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class InClassScoreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private MainActivityTeacher mMainActivityTeacher;
    private ArrayList<String> mUrlList;

    public InClassScoreAdapter() {
    }

    public InClassScoreAdapter(FragmentActivity activity, ArrayList<String> imageUrlList) {
        mMainActivityTeacher = (MainActivityTeacher) activity;
        mUrlList = imageUrlList;
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mLayoutInflater = LayoutInflater.from(mMainActivityTeacher);
        View mView = mLayoutInflater.inflate(R.layout.fragment_in_class_score_item, null);
        return new MyViewHolder(mView);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MyViewHolder mMyViewHolder = (MyViewHolder) holder;
        Picasso.with(mMainActivityTeacher)
                .load(mUrlList.get(position))
                .resize(200, 300)
                .placeholder(R.mipmap.student)
                .error(R.mipmap.education)
                .into(mMyViewHolder.mImageView);

        mMyViewHolder.mTextView.setText(Constan.STUDENTNAME + position);

        if (mOnItemClickLitener != null) {
            mMyViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = mMyViewHolder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(mMyViewHolder.itemView, pos);
                }
            });

            mMyViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = mMyViewHolder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(mMyViewHolder.itemView, pos);
                    return false;
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return mUrlList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_score_item);
            mTextView = (TextView) itemView.findViewById(R.id.tv_score_item);
        }
    }

}
