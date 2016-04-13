package com.gdin.teach.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gdin.teach.R;
import com.gdin.teach.activity.MainStudentActivity;
import com.gdin.teach.activity.StudentTestActivity;
import com.gdin.teach.adapter.ClassListAdapter;
import com.gdin.teach.bean.StudentClassInfoBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 黄培彦 on 16/4/13.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class StudentClassListFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    @Bind(R.id.lv_class_list)
    ListView mLvClassList;
    private MainStudentActivity mStudentActivity;
    private ArrayList<StudentClassInfoBean> mInfoBeanArrayList;
    private ClassListAdapter mAdapter;

    @Override
    public View initView() {
        View mView = mActivity.getLayoutInflater().inflate(R.layout.fragment_student_test, null);
        mStudentActivity = (MainStudentActivity) mActivity;
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
        mAdapter = new ClassListAdapter(mActivity, mInfoBeanArrayList);
        mLvClassList.setOnItemClickListener(this);
        mLvClassList.setAdapter(mAdapter);
    }

    private void initData() {
        mInfoBeanArrayList = new ArrayList<StudentClassInfoBean>();

        String[] classInfoImageUrl = getResources().getStringArray(R.array.classInfoImageUrl);
        String[] classInfoClassName = getResources().getStringArray(R.array.classInfoClassName);
        String[] classInfoClassPosition = getResources().getStringArray(R.array.classInfoClassPosition);
        String[] classInfoClassTeacher = getResources().getStringArray(R.array.classInfoClassTeacher);
        String[] classInfoClassTime = getResources().getStringArray(R.array.classInfoClassTime);
        String[] classInfoClassYuYueNum = getResources().getStringArray(R.array.classInfoClassYuYueNum);
        String[] classInfoClassYingDaoNum = getResources().getStringArray(R.array.classInfoClassYingDaoNum);

        for (int i = 0; i < classInfoClassName.length; i++) {
            StudentClassInfoBean mInfoBean = new StudentClassInfoBean();

            mInfoBean.setImageUrl(classInfoImageUrl[i]);
            mInfoBean.setClassName(classInfoClassName[i]);
            mInfoBean.setClassPosition(classInfoClassPosition[i]);
            mInfoBean.setClassTeacher(classInfoClassTeacher[i]);
            mInfoBean.setClassTime(classInfoClassTime[i]);
            mInfoBean.setYuYueNum(Integer.parseInt(classInfoClassYuYueNum[i]));
            mInfoBean.setYinDaoNum(Integer.parseInt(classInfoClassYingDaoNum[i]));
            mInfoBeanArrayList.add(mInfoBean);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        StudentTestActivity.start2StudentTestActivity(mActivity,mAdapter.getItem(position));
    }
}
