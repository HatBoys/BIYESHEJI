package com.gdin.teach.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.gdin.teach.R;
import com.gdin.teach.bean.StudentTestDataBean;
import com.gdin.teach.bean.TakeNotesMottosBean;

import java.util.ArrayList;


/**
 * Created by 黄培彦 on 16/4/13.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class TakeNotesVpAdapter extends PagerAdapter {

    private ArrayList<TakeNotesMottosBean> mBeanArrayList;
    private Activity mActivity;


    public TakeNotesVpAdapter(Activity activity, ArrayList<TakeNotesMottosBean> beanArrayList) {
        mActivity = activity;
        mBeanArrayList = beanArrayList;

    }

    @Override
    public int getCount() {
        return mBeanArrayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View mView = mActivity.getLayoutInflater().inflate(R.layout.take_notes_adapter_item, null);

        TextView mContent = (TextView) mView.findViewById(R.id.tv_content);
        TextView mAuthor = (TextView) mView.findViewById(R.id.tv_author);

        mContent.setText("         " + mBeanArrayList.get(position).getContent());
        mAuthor.setText("——" + mBeanArrayList.get(position).getAuthor());

        container.addView(mView, null);
        return mView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


}
