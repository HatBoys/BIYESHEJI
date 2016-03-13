package com.gdin.teach.fragment;

import android.view.View;

import com.gdin.teach.R;

/**
 * Created by 黄培彦 on 16/3/13.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class ClassInfoDetailFragment extends BaseFragment {
    @Override
    public View initView() {
        return mActivity.getLayoutInflater().inflate(R.layout.fragment_class_info_detail, null);
    }
}
