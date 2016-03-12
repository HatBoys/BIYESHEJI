package com.gdin.teach.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gdin.teach.Constan;
import com.gdin.teach.R;
import com.gdin.teach.adapter.ClassInfoAdapter;
import com.gdin.teach.util.CommomUtil;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 黄培彦 on 16/3/11.
 * Email: peiyanhuang@yeah.net
 * 类说明: 课前课程信息
 */
public class BeforeClassTeacherFragment extends BaseFragment implements AdapterView.OnItemClickListener {


    @Bind(R.id.lv_info_class)
    ListView mLvInfoClass;
    private ArrayList<String> mStringArrayList;

    public BeforeClassTeacherFragment() {
    }


    @Override
    public View initView() {
        return mActivity.getLayoutInflater().inflate(R.layout.fragment_before_class_teacher, null);
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
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        mLvInfoClass.setAdapter(new ClassInfoAdapter(getContext(), mStringArrayList));
        mLvInfoClass.setOnItemClickListener(this);
    }

    /**
     * 虚拟数据
     */
    private void initData() {
        mStringArrayList = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            mStringArrayList.add(Constan.XINHAOYUXITONG + i);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CommomUtil.toastMessage(getContext(), "Click" + position);
    }
}
