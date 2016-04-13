package com.gdin.teach.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gdin.teach.R;
import com.gdin.teach.adapter.StudentTestAdapter;
import com.gdin.teach.bean.StudentClassInfoBean;
import com.gdin.teach.bean.StudentTestDataBean;
import com.gdin.teach.fragment.BaseFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 黄培彦 on 16/4/13.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class StudentTestFragment extends BaseFragment implements ViewPager.OnPageChangeListener {
    @Bind(R.id.vp_student_test)
    ViewPager mVpStudentTest;
    @Bind(R.id.tv_answer)
    TextView mTvAnswer;
    private StudentClassInfoBean mClassInfoBean;
    private ArrayList<StudentTestDataBean> mBeanArrayList;
    private StudentTestAdapter mAdapter;
    private boolean toggleCheckeVisiable = true;

    public StudentTestFragment() {
    }

    @SuppressLint("ValidFragment")
    public StudentTestFragment(StudentClassInfoBean classInfoBean) {
        mClassInfoBean = classInfoBean;
    }

    @Override
    public View initView() {
        View mView = getActivity().getLayoutInflater().inflate(R.layout.fragment_student_class_test, null);
        return mView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        mVpStudentTest.setOffscreenPageLimit(6);
        mVpStudentTest.setFilterTouchesWhenObscured(true);
        mAdapter = new StudentTestAdapter(mActivity, mBeanArrayList);
        mVpStudentTest.setAdapter(mAdapter);
        mTvAnswer.setText("答案:" + mBeanArrayList.get(0).getAnswer());
        mVpStudentTest.setOnPageChangeListener(this);
    }


    private void initData() {

        mBeanArrayList = new ArrayList<StudentTestDataBean>();

        String[] title = getResources().getStringArray(R.array.studentTestTitle);
        String[] questionA = getResources().getStringArray(R.array.studentTestA);
        String[] questionB = getResources().getStringArray(R.array.studentTestB);
        String[] questionC = getResources().getStringArray(R.array.studentTestC);
        String[] questionD = getResources().getStringArray(R.array.studentTestD);
        String[] answer = getResources().getStringArray(R.array.studentTestCorrect);

        for (int i = 0; i < title.length; i++) {

            StudentTestDataBean mTestDataBean = new StudentTestDataBean();

            mTestDataBean.setTitle(title[i]);
            mTestDataBean.setQuestionA(questionA[i]);
            mTestDataBean.setQuestionB(questionB[i]);
            mTestDataBean.setQuestionC(questionC[i]);
            mTestDataBean.setQuestionD(questionD[i]);
            mTestDataBean.setAnswer(answer[i]);

            mBeanArrayList.add(mTestDataBean);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.bt_before_que, R.id.bt_next_que})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_before_que:
                mVpStudentTest.setCurrentItem(mVpStudentTest.getCurrentItem() - 1, false);
                break;
            case R.id.bt_next_que:
                mVpStudentTest.setCurrentItem(mVpStudentTest.getCurrentItem() + 1, false);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mTvAnswer.setText("答案:" + mBeanArrayList.get(position).getAnswer());
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick(R.id.bt_check_answer)
    public void onClick() {
        if (toggleCheckeVisiable) {
            mTvAnswer.setVisibility(View.VISIBLE);
        } else {
            mTvAnswer.setVisibility(View.GONE);
        }
        toggleCheckeVisiable = !toggleCheckeVisiable;
    }
}
