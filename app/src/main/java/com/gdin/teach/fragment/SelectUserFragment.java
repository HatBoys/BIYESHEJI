package com.gdin.teach.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gdin.teach.R;
import com.gdin.teach.activity.LoginActivity;
import com.gdin.teach.util.CommomUtil;

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
        CommomUtil.toastMessage(getContext(), "student");
        LoginActivity.start2LoginActivity(getContext());
    }

    @OnClick(R.id.tv_user_teacher)
    public void skipToTeacger() {
        CommomUtil.toastMessage(getContext(), "teacher");
        LoginActivity.start2LoginActivity(getContext());
    }
}
