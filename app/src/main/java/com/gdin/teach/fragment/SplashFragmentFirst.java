package com.gdin.teach.fragment;

import android.view.View;

import com.gdin.teach.R;

/**
 * Created by 黄培彦 on 16/3/10.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class SplashFragmentFirst extends BaseFragment {
    @Override
    public View initView() {
        View mView = mActivity.getLayoutInflater().inflate(R.layout.splash_fragment_first, null);
        return mView;
    }
}
