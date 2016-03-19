package com.gdin.teach.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.gdin.teach.fragment.BaseFragment;

import java.util.ArrayList;

/**
 * Created by 黄培彦 on 16/3/19.
 * Email: peiyanhuang@yeah.net
 * 类说明: 上课界面的Viewpager adapter
 */
public class InClassVPAdapter extends FragmentPagerAdapter {


    private ArrayList<BaseFragment> mFragmentArrayList;


    public InClassVPAdapter(FragmentManager fm, ArrayList<BaseFragment> fragmentArrayList) {
        super(fm);
        mFragmentArrayList = fragmentArrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentArrayList.size();
    }
}
