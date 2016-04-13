package com.gdin.teach.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.gdin.teach.R;
import com.gdin.teach.bean.StudentTestDataBean;
import com.gdin.teach.util.CommomUtil;


import java.util.ArrayList;


/**
 * Created by 黄培彦 on 16/4/13.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class StudentTestAdapter extends PagerAdapter {

    private ArrayList<StudentTestDataBean> mBeanArrayList;
    private Activity mActivity;
    private RadioGroup mRadioGroup;
    private RadioButton mQueA;
    private RadioButton mQueB;
    private RadioButton mQueC;
    private RadioButton mQueD;

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

        mRadioGroup = (RadioGroup) mView.findViewById(R.id.rg_test_list);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });
        mQueA = (RadioButton) mView.findViewById(R.id.rb_que_a);
        mQueB = (RadioButton) mView.findViewById(R.id.rb_que_b);
        mQueC = (RadioButton) mView.findViewById(R.id.rb_que_c);
        mQueD = (RadioButton) mView.findViewById(R.id.rb_que_d);

        TextView mShowPos = (TextView) mView.findViewById(R.id.tv_show_que_pos);
        mShowPos.setText((position + 1) + "/" + mBeanArrayList.size());

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
