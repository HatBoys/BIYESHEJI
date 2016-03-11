package com.gdin.teach.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 黄培彦 on 16/3/10.
 * Email: peiyanhuang@yeah.net
 * 类说明: Fragment基类
 */
public abstract class BaseFragment extends Fragment {

    public Activity mActivity;//提供一个Fragment可以随时获取的上下文环境
    public View mView;//提供一个全局的View

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = initView();
        return mView;
    }

    public abstract View initView();//必须初始化实现View

}
