package com.gdin.teach.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import com.gdin.teach.activity.MainActivityTeacher;
import com.gdin.teach.fragment.BaseFragment;

import java.util.ArrayList;

/**
 * Created by 黄培彦 on 16/3/11.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class MainTeacherAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private ArrayList<BaseFragment> mFragmentArrayList;
    private View mCurrentView;

    public MainTeacherAdapter(FragmentManager fm) {
        super(fm);
    }

    public MainTeacherAdapter(MainActivityTeacher mainActivityTeacher, ArrayList<BaseFragment> fragmentArrayList, FragmentManager fragmentManager) {
        super(fragmentManager);
        mContext = mainActivityTeacher;
        mFragmentArrayList = fragmentArrayList;
    }

    @Override
    public int getCount() {
        return mFragmentArrayList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentArrayList.get(position);
    }

}
