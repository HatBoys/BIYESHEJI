package com.gdin.teach.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.gdin.teach.Constan;
import com.gdin.teach.R;
import com.gdin.teach.adapter.InClassVPAdapter;
import com.gdin.teach.view.NoScrollViewPager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 黄培彦 on 16/3/11.
 * Email: peiyanhuang@yeah.net
 * 类说明: 上课Fragment
 */
public class InClassTeacherFragment extends BaseFragment implements View.OnClickListener {


    @Bind(R.id.rb_mention)
    RadioButton mRbMention;
    @Bind(R.id.rb_score)
    RadioButton mRbScore;
    @Bind(R.id.rg_in_class_top_tab)
    RadioGroup mRgInClassTopTab;
    @Bind(R.id.vp_in_class)
    NoScrollViewPager mVpInClass;
    private ArrayList<BaseFragment> mFragmentArrayList;
    private FragmentManager mFragmentManager;
    private ArrayList<String> mImageUrlArrayList;//学生头像URL

    @Override
    public View initView() {
        return mActivity.getLayoutInflater().inflate(R.layout.fragment_in_class_teacher, null);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentArrayList = new ArrayList<BaseFragment>();
        mImageUrlArrayList = new ArrayList<String>();
        for (int i = 1; i < 19; i++) {
            mImageUrlArrayList.add(Constan.MENTIONIMAGEHEAD + i + Constan.MENTIONIMAGEEND);//虚拟学生头像
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        initListener();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        mFragmentManager = getActivity().getSupportFragmentManager();
        mVpInClass.setOffscreenPageLimit(2);
        mVpInClass.setAdapter(new InClassVPAdapter(mFragmentManager, mFragmentArrayList));
    }

    private void initData() {
        mFragmentArrayList.add(new InClassMentionFragment(mImageUrlArrayList));
        mFragmentArrayList.add(new InClassScoreFragment(mImageUrlArrayList));
    }

    private void initListener() {
        mRbMention.setOnClickListener(this);
        mRbScore.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_mention:
                mRgInClassTopTab.check(R.id.rb_mention);
                mVpInClass.setCurrentItem(0, true);
                break;
            case R.id.rb_score:
                mRgInClassTopTab.check(R.id.rb_score);
                mVpInClass.setCurrentItem(1, true);
                break;
        }
    }
}
