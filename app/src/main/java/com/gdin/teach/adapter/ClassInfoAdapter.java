package com.gdin.teach.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.gdin.teach.MyApplication;
import com.gdin.teach.R;
import com.gdin.teach.bean.StudentClassInfoBean;

import java.util.ArrayList;

/**
 * Created by 黄培彦 on 16/3/12.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class ClassInfoAdapter extends BaseAdapter {

    private Context mContext;
    private ImageLoader mLoader;
    private ArrayList<StudentClassInfoBean> mInfoBeanArrayList;

    public ClassInfoAdapter() {
    }

    public ClassInfoAdapter(Context context, ArrayList<StudentClassInfoBean> infoBeanArrayList) {
        this.mContext = context;
        this.mInfoBeanArrayList = infoBeanArrayList;
    }

    @Override
    public int getCount() {
        return mInfoBeanArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mInfoBeanArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder = null;
        if (convertView == null) {
            mViewHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.class_info_list_item, null);
            mViewHolder.mClassName = (TextView) convertView.findViewById(R.id.tv_class_info_list);
            mViewHolder.mNetworkImageView = (NetworkImageView) convertView.findViewById(R.id.ni_class_info_list);
            mViewHolder.mClassTeacher = (TextView) convertView.findViewById(R.id.tv_class_info_teacher);
            mViewHolder.mClassLocation = (TextView) convertView.findViewById(R.id.tv_class_info_location);
            mViewHolder.mClassTime = (TextView) convertView.findViewById(R.id.tv_class_info_time);

            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();//从标签中取出来
        }

        mLoader = new ImageLoader(MyApplication.getInstance().getRequestQueue(), new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });

        mViewHolder.mClassName.setText(mInfoBeanArrayList.get(position).getClassName());
        mViewHolder.mClassTeacher.setText(mInfoBeanArrayList.get(position).getClassTeacher());
        mViewHolder.mClassLocation.setText(mInfoBeanArrayList.get(position).getClassPosition());
        mViewHolder.mClassTime.setText(mInfoBeanArrayList.get(position).getClassTime());

        mViewHolder.mNetworkImageView.setDefaultImageResId(R.mipmap.loading_image);
        mViewHolder.mNetworkImageView.setErrorImageResId(R.mipmap.faild_load);
        int imagePositon = position % mInfoBeanArrayList.size();
        mViewHolder.mNetworkImageView.setImageUrl(mInfoBeanArrayList.get(imagePositon).getImageUrl(), mLoader);

        return convertView;
    }

    public static class ViewHolder {
        TextView mClassName;
        TextView mClassTeacher;
        TextView mClassLocation;
        TextView mClassTime;
        NetworkImageView mNetworkImageView;
    }
}
