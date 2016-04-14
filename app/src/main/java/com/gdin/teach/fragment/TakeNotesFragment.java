package com.gdin.teach.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gdin.teach.R;
import com.gdin.teach.activity.MainStudentActivity;
import com.gdin.teach.adapter.TakeNotesVpAdapter;
import com.gdin.teach.bean.TakeNotesMottosBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 黄培彦 on 16/4/13.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class TakeNotesFragment extends BaseFragment implements ViewPager.OnPageChangeListener {
    @Bind(R.id.vp_take_notes)
    ViewPager mVpTakeNotes;
    private ArrayList<TakeNotesMottosBean> mBeanArrayList;
    private MainStudentActivity mStudentActivity;

    @Override
    public View initView() {
        View mView = mActivity.getLayoutInflater().inflate(R.layout.fragment_take_notes, null);
        return mView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mStudentActivity = (MainStudentActivity) mActivity;

        settingIgnoreVp(false);

        initData();
        mVpTakeNotes.setAdapter(new TakeNotesVpAdapter(mActivity, mBeanArrayList));
        mVpTakeNotes.setOnPageChangeListener(this);
    }

    private void settingIgnoreVp(boolean b) {
        if (b) {
            mStudentActivity.setSligdingMenuIgnorView(mVpTakeNotes);
        } else {
            mStudentActivity.clearSlidingMenuIgnoreView();
        }
    }

    private void initData() {
        mBeanArrayList = new ArrayList<TakeNotesMottosBean>();

        String[] content = getResources().getStringArray(R.array.takeNotesMottoContent);
        String[] author = getResources().getStringArray(R.array.takeNotesMottoAuthor);

        for (int i = 0; i < content.length; i++) {
            TakeNotesMottosBean mMottosBean = new TakeNotesMottosBean();
            mMottosBean.setContent(content[i]);
            mMottosBean.setAuthor(author[i]);
            mBeanArrayList.add(mMottosBean);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.bt_notes, R.id.bt_photo, R.id.bt_record, R.id.bt_video})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_notes:
                break;
            case R.id.bt_photo:
                break;
            case R.id.bt_record:
                break;
            case R.id.bt_video:
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            settingIgnoreVp(false);
        } else {
            settingIgnoreVp(true);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
