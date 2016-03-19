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

import butterknife.Bind;

/**
 * Created by 黄培彦 on 16/3/19.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class InClassMentionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    @Bind(R.id.iv_in_class_mention)
    ImageView mIvInClassMention;
    @Bind(R.id.tv_in_class_student_name)
    TextView mTvInClassStudentName;
    @Bind(R.id.tv_in_class_student_num)
    TextView mTvInClassStudentNum;

    private MainActivityTeacher mMainActivityTeacher;
    private ArrayList<String> mUrlList;

    public InClassMentionAdapter() {
    }

    public InClassMentionAdapter(FragmentActivity activity, ArrayList<String> urlList) {
        mMainActivityTeacher = (MainActivityTeacher) activity;
        mUrlList = urlList;
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

        LayoutInflater mInflater = LayoutInflater.from(mMainActivityTeacher);
        View mView = mInflater.inflate(R.layout.in_class_mention_vp_item, null);
        return new MyViewHolder(mView);//只是把一整个View绑定到ViewHolder上
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final MyViewHolder mViewHolder = (MyViewHolder) holder;
        Picasso.with(mMainActivityTeacher).load(mUrlList.get(position)).placeholder(R.mipmap.student)
                .error(R.mipmap.education).resize(400, 600).into(mViewHolder.mImageView);

        mViewHolder.mTvName.setText(Constan.STUDENTNAME + position);
        mViewHolder.mTvNum.setText(Constan.STUDENTNUMBER + position);

        if (mOnItemClickLitener != null) {
            mViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = mViewHolder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(mViewHolder.itemView, pos);
                }
            });

            mViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = mViewHolder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(mViewHolder.itemView, pos);
                    return false;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mUrlList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTvName;
        TextView mTvNum;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.iv_in_class_mention);
            mTvName = (TextView) itemView.findViewById(R.id.tv_in_class_student_name);
            mTvNum = (TextView) itemView.findViewById(R.id.tv_in_class_student_num);
        }

    }

}
