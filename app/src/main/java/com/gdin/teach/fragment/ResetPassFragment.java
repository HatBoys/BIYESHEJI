package com.gdin.teach.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.gdin.teach.Constan;
import com.gdin.teach.MyApplication;
import com.gdin.teach.R;
import com.gdin.teach.util.CommomUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 黄培彦 on 16/3/29.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class ResetPassFragment extends BaseFragment {
    @Bind(R.id.et_past_pass)
    EditText mEtPastPass;
    @Bind(R.id.et_new_pass)
    EditText mEtNewPass;
    @Bind(R.id.et_resume_pass)
    EditText mEtResumePass;

    @Override
    public View initView() {
        View mView = getActivity().getLayoutInflater().inflate(R.layout.fragment_resetpass, null);
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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.bt_reset_pass)
    public void onClick() {
        String pastPass = mEtPastPass.getText().toString().trim();
        String newPass = mEtNewPass.getText().toString().trim();
        String resumPass = mEtResumePass.getText().toString().trim();
        String pastTruePass = MyApplication.mSharedPreferences.getString(Constan.USERPASS, "");

        if ("".equals(pastPass)) {
            CommomUtil.toastMessage(getContext(), Constan.SETTINGPASTPASS);
            return;
        }
        if ("".equals(newPass)) {
            CommomUtil.toastMessage(getContext(), Constan.SETTINGNEWPASS);
            return;
        }
        if ("".equals(resumPass)) {
            CommomUtil.toastMessage(getContext(), Constan.SETTINGRESUMPASS);
            return;
        }
        if (!newPass.equals(resumPass)) {
            CommomUtil.toastMessage(getContext(), Constan.NEWANDRESUMDIF);
            return;
        }
        if (!pastTruePass.equals(pastPass)) {
            CommomUtil.toastMessage(getContext(), Constan.PASTPASSERROR);
            return;
        }
        if (pastTruePass.equals(newPass)) {
            CommomUtil.toastMessage(getContext(), Constan.NEWANDPASTLIKELY);
            return;
        }

        MyApplication.mSharedPreferences.edit()
                .putString(Constan.USERPASS, resumPass).commit();
        CommomUtil.toastMessage(getContext(), Constan.RESETPASSSUCCESS);

        getActivity().finish();
    }
}
