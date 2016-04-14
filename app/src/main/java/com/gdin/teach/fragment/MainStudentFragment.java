package com.gdin.teach.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.gdin.teach.Constan;
import com.gdin.teach.R;
import com.gdin.teach.activity.MainStudentActivity;
import com.gdin.teach.adapter.MainTeacherAdapter;
import com.gdin.teach.view.NoScrollViewPager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 黄培彦 on 16/4/11.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class MainStudentFragment extends BaseFragment {
    @Bind(R.id.vp_main_student)
    NoScrollViewPager mVpMainStudent;
    @Bind(R.id.rb_student_before_class)
    RadioButton mRbStudentBeforeClass;
    @Bind(R.id.rb_student_in_class)
    RadioButton mRbStudentInClass;
    @Bind(R.id.rb_student_personal)
    RadioButton mRbStudentPersonal;
    @Bind(R.id.rg_main_student_bottom)
    RadioGroup mRgMainStudentBottom;
    private ArrayList<BaseFragment> mFragmentArrayList;
    private FragmentManager mFragmentManager;
    private MainStudentActivity mMainStudentActivity;

    @Override
    public View initView() {
        View mView = getActivity().getLayoutInflater().inflate(R.layout.fragment_main_student, null);
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
        mMainStudentActivity = (MainStudentActivity) getActivity();
        clickRbStuBefore();
        initData();
        mFragmentManager = getActivity().getSupportFragmentManager();
        MainTeacherAdapter mMainTeacherAdapter = new MainTeacherAdapter(mFragmentArrayList, mFragmentManager);
        mVpMainStudent.setAdapter(mMainTeacherAdapter);
        mRbStudentBeforeClass.setChecked(true);
        mVpMainStudent.setCurrentItem(0);
        mVpMainStudent.setOffscreenPageLimit(3);
    }

    private void initData() {
        mFragmentArrayList = new ArrayList<BaseFragment>();
        mFragmentArrayList.add(new BeforeClassTeacherFragment());
        mFragmentArrayList.add(new TakeNotesFragment());
        mFragmentArrayList.add(new StudentClassListFragment());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.rb_student_before_class, R.id.rb_student_in_class, R.id.rb_student_personal})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_student_before_class:
                clickRbStuBefore();
                break;
            case R.id.rb_student_in_class:
                mRgMainStudentBottom.check(R.id.rb_student_in_class);
                mMainStudentActivity.reSetToolBar();
                mMainStudentActivity.setToolBar(true);
                mMainStudentActivity.setFragmentTitle(Constan.TAKENOTES);
                mVpMainStudent.setCurrentItem(1);
                settingMenuVisiable(true);
                break;
            case R.id.rb_student_personal:
                mRgMainStudentBottom.check(R.id.rb_student_personal);
                mMainStudentActivity.reSetToolBar();
                mMainStudentActivity.setToolBar(true);
                mMainStudentActivity.setFragmentTitle(Constan.PERSONALTEST);
                mVpMainStudent.setCurrentItem(2);
                settingMenuVisiable(false);
                break;
        }
    }

    private void settingMenuVisiable(boolean visiable) {
        if (mMainStudentActivity != null) {
            mMainStudentActivity.setMenuVisable(visiable);
        }
    }

    /**
     * 抽出「课前」点中方法
     */
    private void clickRbStuBefore() {
        mRgMainStudentBottom.check(R.id.rb_student_before_class);
        mMainStudentActivity.reSetToolBar();
        mMainStudentActivity.setToolBar(true);
        mMainStudentActivity.setFragmentTitle(Constan.STUDENTCLASS);
        mVpMainStudent.setCurrentItem(0);
        settingMenuVisiable(false);
    }
}
