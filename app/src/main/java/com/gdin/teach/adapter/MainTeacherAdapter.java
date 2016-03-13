package com.gdin.teach.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.gdin.teach.fragment.BaseFragment;

import java.util.ArrayList;

/**
 * Created by 黄培彦 on 16/3/11.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class MainTeacherAdapter extends FragmentPagerAdapter {

    private ArrayList<BaseFragment> mFragmentArrayList;

    public MainTeacherAdapter(FragmentManager fm) {
        super(fm);
    }

    public MainTeacherAdapter(ArrayList<BaseFragment> fragmentArrayList, FragmentManager fragmentManager) {
        super(fragmentManager);
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
