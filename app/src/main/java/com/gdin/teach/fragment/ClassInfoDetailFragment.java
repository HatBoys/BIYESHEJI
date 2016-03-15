package com.gdin.teach.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.gdin.teach.Constan;
import com.gdin.teach.R;
import com.gdin.teach.activity.ClassInfoDetailActivity;

/**
 * Created by 黄培彦 on 16/3/13.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class ClassInfoDetailFragment extends BaseFragment {

    private ClassInfoDetailActivity mClassInfoDetailActivity;

    @Override
    public View initView() {
        return mActivity.getLayoutInflater().inflate(R.layout.fragment_class_info_detail, null);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mClassInfoDetailActivity = (ClassInfoDetailActivity) getActivity();
        mClassInfoDetailActivity.setTitle(Constan.CLASSDETAILTITLE);
    }
}
