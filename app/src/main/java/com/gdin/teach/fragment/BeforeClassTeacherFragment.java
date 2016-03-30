package com.gdin.teach.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gdin.teach.Constan;
import com.gdin.teach.R;
import com.gdin.teach.activity.ClassInfoDetailActivity;
import com.gdin.teach.activity.MainActivityTeacher;
import com.gdin.teach.adapter.ClassInfoAdapter;
import com.gdin.teach.util.CommomUtil;
import com.gdin.teach.view.UpLoadSwipeRefreshLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 黄培彦 on 16/3/11.
 * Email: peiyanhuang@yeah.net
 * 类说明: 课前课程信息
 */
public class BeforeClassTeacherFragment extends BaseFragment implements AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, UpLoadSwipeRefreshLayout.OnLoadListener {


    @Bind(R.id.lv_info_class)
    ListView mLvInfoClass;
    @Bind(R.id.sf_class_info)
    UpLoadSwipeRefreshLayout mSfClassInfo;
    private ArrayList<String> mStringArrayList;
    private ClassInfoAdapter mClassInfoAdapter;
    private int mRefreshTime;
    private MainActivityTeacher mMainActivityTeacher;
    private ArrayList<String> mClassList;
    private ArrayList<String> mImageUrlArrayList;

    public BeforeClassTeacherFragment() {
    }


    @Override
    public View initView() {
        return mActivity.getLayoutInflater().inflate(R.layout.fragment_before_class_teacher, null);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainActivityTeacher = (MainActivityTeacher) getActivity();
        mMainActivityTeacher.setToolBar(true);
        mMainActivityTeacher.setFragmentTitle(Constan.CLASSLISTTITLE);
        mClassList = new ArrayList<String>();
        mClassList.add(Constan.MONIDIANLU);
        mClassList.add(Constan.XINHAOYUXITONG);
        mClassList.add(Constan.SHUZIDIANLU);
        mClassList.add(Constan.DIANLUSHEJI);
        mClassList.add(Constan.DANPIANJI);
        mClassList.add(Constan.JAVABIANCHENGSIXIANG);

        mImageUrlArrayList = new ArrayList<String>();

        mImageUrlArrayList.add(Constan.FIRSTINFO);
        mImageUrlArrayList.add(Constan.SECONDINFO);
        mImageUrlArrayList.add(Constan.THIRDINFO);
        mImageUrlArrayList.add(Constan.FOUTHINFO);
        mImageUrlArrayList.add(Constan.FIFTHINFO);
        mImageUrlArrayList.add(Constan.SIXTHINFO);

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
        initListViewData();
        mSfClassInfo.setListView(mLvInfoClass);
        mLvInfoClass.setAdapter(mClassInfoAdapter);
        mSfClassInfo.setOnRefreshListener(this);
        mSfClassInfo.setOnLoadListener(this);
        mLvInfoClass.setOnItemClickListener(this);

    }

    /**
     * 虚拟数据
     */
    private void initListViewData() {
        mStringArrayList = new ArrayList<String>();

        for (int i = 0; i < 18; i++) {
            int imageIndex = i % mClassList.size();
            mStringArrayList.add(mClassList.get(imageIndex));
        }
        mClassInfoAdapter = new ClassInfoAdapter(getContext(), mStringArrayList, mImageUrlArrayList);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        CommomUtil.toastMessage(getActivity().getApplicationContext().getApplicationContext(), "Click" + position);
        // TODO: 16/3/13 跳转详情(暂时找不到跳转Fragment的方法,只能跳转去Activity)
        /*mMainActivityTeacher.mFragmentManager
                .beginTransaction()
                .addToBackStack(Constan.BEFORECLASSTEACHERFRAGMENT)
                .add(new ClassInfoDetailFragment(), Constan.CLASSINFODETAILFRAGMENT)
                .commit();*/

        ClassInfoDetailActivity.start2ClassInfoDetailActivity(getContext(), position
                , mClassInfoAdapter.getItem(position), mImageUrlArrayList);//跳转到ClassInfoDetailActivity
    }

    @Override
    public void onRefresh() {
        mSfClassInfo.postDelayed(new Runnable() {
            @Override
            public void run() {
                mStringArrayList.clear();
                mRefreshTime++;
                for (int i = 0; i < 18; i++) {
                    int imageIndex = i % mClassList.size();
                    mStringArrayList.add(mClassList.get(imageIndex) + "第" + mRefreshTime + "次版本");
                }
                mClassInfoAdapter.notifyDataSetChanged();
//                mSfClassInfo.cancelLongPress();
                mSfClassInfo.setRefreshing(false);//停止刷新
            }
        }, 1500);
    }

    @Override
    public void onLoad() {
        mSfClassInfo.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 12; i++) {
                    int imageIndex = i % mClassList.size();
                    mStringArrayList.add(mClassList.get(imageIndex));
                }
                mClassInfoAdapter.notifyDataSetChanged();
                mSfClassInfo.setLoading(false);
            }
        }, 2000);
    }
}
