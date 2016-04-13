package com.gdin.teach.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.gdin.teach.R;
import com.gdin.teach.bean.StudentClassInfoBean;

import java.util.ArrayList;

/**
 * Created by 黄培彦 on 16/4/13.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class ClassListAdapter extends BaseAdapter {

    private ArrayList<StudentClassInfoBean> mArrayList;
    private Activity mActivity;

    public ClassListAdapter() {
    }

    public ClassListAdapter(Activity activity, ArrayList<StudentClassInfoBean> infoBeanArrayList) {
        mArrayList = infoBeanArrayList;
        mActivity = activity;
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder mViewHolder;
        if (convertView == null) {
            mViewHolder = new ViewHolder();
            convertView = mActivity.getLayoutInflater().inflate(R.layout.class_list_item, null);
            mViewHolder.mTextView = (TextView) convertView.findViewById(R.id.tv_class_name);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mViewHolder.mTextView.setText(mArrayList.get(position).getClassName());

        return convertView;
    }

    static class ViewHolder {
        TextView mTextView;
    }

}
