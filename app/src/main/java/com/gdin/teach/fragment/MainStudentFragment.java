package com.gdin.teach.fragment;

import android.view.View;

import com.gdin.teach.R;

/**
 * Created by 黄培彦 on 16/4/11.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class MainStudentFragment extends BaseFragment {
    @Override
    public View initView() {
        View mView = getActivity().getLayoutInflater().inflate(R.layout.fragment_main_student, null);
        return mView;
    }
}
