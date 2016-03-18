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

import java.util.ArrayList;

/**
 * Created by 黄培彦 on 16/3/12.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class ClassInfoAdapter extends BaseAdapter {

    private ArrayList<String> mStringArrayList;
    private Context mContext;
    private ArrayList<String> mImageUrlList;
    private ImageLoader mLoader;

    public ClassInfoAdapter() {
    }

    public ClassInfoAdapter(Context context, ArrayList<String> stringArrayList, ArrayList<String> imageUrlArrayList) {
        this.mContext = context;
        this.mStringArrayList = stringArrayList;
        this.mImageUrlList = imageUrlArrayList;
    }

    @Override
    public int getCount() {
        return mStringArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mStringArrayList.get(position);
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
            mViewHolder.mTextView = (TextView) convertView.findViewById(R.id.tv_class_info_list);
            mViewHolder.mNetworkImageView = (NetworkImageView) convertView.findViewById(R.id.ni_class_info_list);
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

        mViewHolder.mTextView.setText(mStringArrayList.get(position));

        mViewHolder.mNetworkImageView.setDefaultImageResId(R.mipmap.loading_image);
        mViewHolder.mNetworkImageView.setErrorImageResId(R.mipmap.faild_load);
        int imagePositon = position % mImageUrlList.size();
        mViewHolder.mNetworkImageView.setImageUrl(mImageUrlList.get(imagePositon), mLoader);

        return convertView;
    }

    public static class ViewHolder {
        TextView mTextView;
        NetworkImageView mNetworkImageView;
    }
}
