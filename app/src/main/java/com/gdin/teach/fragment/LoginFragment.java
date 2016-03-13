package com.gdin.teach.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gdin.teach.Constan;
import com.gdin.teach.R;
import com.gdin.teach.activity.MainActivityTeacher;
import com.gdin.teach.activity.SelectUserActivity;
import com.gdin.teach.util.CommomUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 黄培彦 on 16/3/11.
 * Email: peiyanhuang@yeah.net
 * 类说明: 登录Fragment
 */
public class LoginFragment extends BaseFragment {
    @Bind(R.id.iv_user_icon)
    ImageView mIvUserIcon;
    @Bind(R.id.et_user_name)
    EditText mEtUserName;
    @Bind(R.id.et_user_password)
    EditText mEtUserPassword;
    @Bind(R.id.bt_login)
    TextView mBtLogin;
    private String mUser;
    private SelectUserActivity mSelectUserActivity;

    @SuppressLint("ValidFragment")
    public LoginFragment(String user, SelectUserActivity selectUserActivity) {
        mUser = user;
        mSelectUserActivity = selectUserActivity;
    }

    public LoginFragment() {
    }

    @Override
    public View initView() {
        return mActivity.getLayoutInflater().inflate(R.layout.fragment_login_layout, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        mIvUserIcon.setScaleX(0.5f);
        mIvUserIcon.setScaleY(0.5f);
        if (Constan.STUDENT.equals(mUser)) {
            mIvUserIcon.setImageResource(R.drawable.select_user_student);
        } else {
            mIvUserIcon.setImageResource(R.drawable.select_user_teacher);
        }
        return rootView;
    }

    @OnClick(R.id.bt_login)
    public void login() {
        if ("".equals(mEtUserName.getText().toString()) || "".equals(mEtUserPassword.getText().toString())) {
            CommomUtil.toastMessage(getContext(), getString(R.string.login_error_toast));
            return;
        }
        if (Constan.STUDENT.equals(mUser)) {
            // TODO: 16/3/11 Student Fragment
        } else {
            //Teacher Fragment
            MainActivityTeacher.start2MainActivityTeacher(getContext());
            if (mSelectUserActivity instanceof SelectUserActivity) {
                mSelectUserActivity.finish();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
