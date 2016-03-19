package com.gdin.teach.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdin.teach.R;
import com.gdin.teach.adapter.InClassMentionAdapter;
import com.gdin.teach.util.CommomUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.animators.ScaleInLeftAnimator;

/**
 * Created by 黄培彦 on 16/3/19.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class InClassMentionFragment extends BaseFragment implements InClassMentionAdapter.OnItemClickLitener {

    @Bind(R.id.rl_in_class_mention)
    RecyclerView mRlInClassMention;
    private ArrayList<String> mUrlList;
    private LinearLayoutManager mLinearLayoutManager;
    private InClassMentionAdapter mAdapter;

    public InClassMentionFragment() {
    }

    public InClassMentionFragment(ArrayList<String> urlArrayList) {
        mUrlList = urlArrayList;
    }

    @Override
    public View initView() {
        return mActivity.getLayoutInflater().inflate(R.layout.fragment_in_class_mention, null);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//        new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL)//瀑布流
//        new GridLayoutManager(getActivity(), 3)

//        mRlInClassMention.addItemDecoration(new DividerItemDecoration(getActivity(),
//                DividerItemDecoration.HORIZONTAL_LIST));//ListView 格式的Divider

//        mRlInClassMention.addItemDecoration(new DividerGridItemDecoration(getContext()));
        mRlInClassMention.setLayoutManager(mLinearLayoutManager);//RecycleView的用法
        mRlInClassMention.setItemAnimator(new ScaleInLeftAnimator());
        mRlInClassMention.setHasFixedSize(true);
        mAdapter = new InClassMentionAdapter(getActivity(), mUrlList);
        mAdapter.setOnItemClickLitener(this);
        mRlInClassMention.setAdapter(mAdapter);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        CommomUtil.toastMessage(getContext(), "Click" + position);
        mUrlList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }
}
