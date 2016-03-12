package com.gdin.teach.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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

    public ClassInfoAdapter() {
    }

    public ClassInfoAdapter(Context context, ArrayList<String> stringArrayList) {
        this.mContext = context;
        this.mStringArrayList = stringArrayList;
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
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();//从标签中取出来
        }

        mViewHolder.mTextView.setText(mStringArrayList.get(position));
        return convertView;
    }

    public static class ViewHolder {
        TextView mTextView;
    }
}
