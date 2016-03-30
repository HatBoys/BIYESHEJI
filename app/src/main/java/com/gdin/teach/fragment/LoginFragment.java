package com.gdin.teach.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gdin.teach.Constan;
import com.gdin.teach.MyApplication;
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
    Button mBtLogin;
    private String mUser;
    private SelectUserActivity mSelectUserActivity;
    private String mInserUserPass;

    @SuppressLint("ValidFragment")
    public LoginFragment(String user) {
        mUser = user;
//        mSelectUserActivity = selectUserActivity;
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
        if (Constan.STUDENT.equals(mUser)) {
            mIvUserIcon.setImageResource(R.mipmap.student);
        } else {
            mIvUserIcon.setImageResource(R.mipmap.education);
        }
        return rootView;
    }

    @OnClick(R.id.bt_login)
    public void login() {

        String userName = mEtUserName.getText().toString().trim();
        String userPass = mEtUserPassword.getText().toString().trim();

        if (MyApplication.mSharedPreferences.contains(Constan.USERPASS)) {
            mInserUserPass = MyApplication.mSharedPreferences.getString(Constan.USERPASS, "");
        } else {
            mInserUserPass = Constan.USERPASS;
        }

        if ("".equals(userName) || "".equals(userPass) || !Constan.USERNAME.equals(userName) || !mInserUserPass.equals(userPass)) {
            CommomUtil.toastMessage(getActivity().getApplicationContext(), getString(R.string.login_error_toast));
            return;
        }
        if (Constan.STUDENT.equals(mUser)) {
            // TODO: 16/3/11 Student Fragment
            CommomUtil.toastApplicationMessage(getContext(),"暂未开发，请用教师端登录");
        } else {
            //Teacher Fragment
            MainActivityTeacher.start2MainActivityTeacher(getContext());
            /*if (getActivity() instanceof SelectUserActivity) {
                getActivity().finish();
            }*/
            MyApplication.mSharedPreferences.edit().putString(Constan.USERPASS, userPass).commit();
        }

        getActivity().finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
