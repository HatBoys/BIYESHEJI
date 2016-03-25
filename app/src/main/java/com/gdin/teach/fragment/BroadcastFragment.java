package com.gdin.teach.fragment;

import android.view.View;

import com.gdin.teach.R;

/**
 * Created by 黄培彦 on 16/3/25.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class BroadcastFragment extends BaseFragment {
    @Override
    public View initView() {
        View mView = getActivity().getLayoutInflater().inflate(R.layout.fragment_broadcast, null);
        return mView;
    }
}
