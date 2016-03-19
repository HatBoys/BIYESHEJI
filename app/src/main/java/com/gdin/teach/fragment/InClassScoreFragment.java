package com.gdin.teach.fragment;

import android.view.View;

import com.gdin.teach.R;

/**
 * Created by 黄培彦 on 16/3/19.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class InClassScoreFragment extends BaseFragment {
    @Override
    public View initView() {
        return mActivity.getLayoutInflater().inflate(R.layout.fragment_in_class_score, null);
    }
}
