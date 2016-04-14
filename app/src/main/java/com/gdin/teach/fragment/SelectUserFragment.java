package com.gdin.teach.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gdin.teach.Constan;
import com.gdin.teach.MyApplication;
import com.gdin.teach.R;
import com.gdin.teach.activity.LoginActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 黄培彦 on 16/3/11.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class SelectUserFragment extends BaseFragment {
    @Bind(R.id.tv_user_student)
    TextView mTvUserStudent;
    @Bind(R.id.tv_user_teacher)
    TextView mTvUserTeacher;

    @Override
    public View initView() {
        return mActivity.getLayoutInflater().inflate(R.layout.select_user_fragment, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.tv_user_student)
    public void skipToStudent() {
        LoginActivity.start2LoginActivity(getActivity(), Constan.STUDENT);
        MyApplication.mSharedPreferences.edit().putString("user","student").commit();
    }

    @OnClick(R.id.tv_user_teacher)
    public void skipToTeacger() {
        LoginActivity.start2LoginActivity(getActivity(), Constan.TEACHER);
        MyApplication.mSharedPreferences.edit().putString("user","teacher").commit();
    }
}
