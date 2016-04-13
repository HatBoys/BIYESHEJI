package com.gdin.teach.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gdin.teach.R;
import com.gdin.teach.bean.StudentTestDataBean;

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * Created by 黄培彦 on 16/4/13.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class StudentTestAdapter extends PagerAdapter {

    private ArrayList<StudentTestDataBean> mBeanArrayList;
    private Activity mActivity;

    public StudentTestAdapter() {
    }

    public StudentTestAdapter(Activity activity, ArrayList<StudentTestDataBean> beanArrayList) {
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
        View mView = mActivity.getLayoutInflater().inflate(R.layout.test_detail_item, null);
        TextView mTitle = (TextView) mView.findViewById(R.id.tv_student_test_detail);

        TextView mQueA = (TextView) mView.findViewById(R.id.rb_que_a);
        TextView mQueB = (TextView) mView.findViewById(R.id.rb_que_b);
        TextView mQueC = (TextView) mView.findViewById(R.id.rb_que_c);
        TextView mQueD = (TextView) mView.findViewById(R.id.rb_que_d);

        mTitle.setText(mBeanArrayList.get(position).getTitle());
        mQueA.setText(mBeanArrayList.get(position).getQuestionA());
        mQueB.setText(mBeanArrayList.get(position).getQuestionB());
        mQueC.setText(mBeanArrayList.get(position).getQuestionC());
        mQueD.setText(mBeanArrayList.get(position).getQuestionD());


        container.addView(mView, null);
        return mView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
