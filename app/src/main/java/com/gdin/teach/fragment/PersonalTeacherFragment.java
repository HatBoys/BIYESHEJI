package com.gdin.teach.fragment;

import android.view.View;

import com.gdin.teach.R;

/**
 * Created by 黄培彦 on 16/3/11.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class PersonalTeacherFragment extends BaseFragment {


    @Override
    public View initView() {
        return mActivity.getLayoutInflater().inflate(R.layout.fragment_personal_teacher, null);
    }
}
