package com.gdin.teach.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gdin.teach.R;
import com.gdin.teach.activity.ClassInfoDetailActivity;
import com.gdin.teach.adapter.ClassInfoAdapter;
import com.gdin.teach.bean.StudentClassInfoBean;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 黄培彦 on 16/3/11.
 * Email: peiyanhuang@yeah.net
 * 类说明: 课前课程信息
 */
public class BeforeClassTeacherFragment extends BaseFragment implements AdapterView.OnItemClickListener {
//, SwipeRefreshLayout.OnRefreshListener, UpLoadSwipeRefreshLayout.OnLoadListener

    @Bind(R.id.lv_info_class)
    ListView mLvInfoClass;
    //    @Bind(R.id.sf_class_info)
//    UpLoadSwipeRefreshLayout mSfClassInfo;
    private ClassInfoAdapter mClassInfoAdapter;
    private int mRefreshTime;
    private ArrayList<StudentClassInfoBean> mInfoBeanArrayList;

    public BeforeClassTeacherFragment() {
    }


    @Override
    public View initView() {
        return mActivity.getLayoutInflater().inflate(R.layout.fragment_before_class_teacher, null);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();

    }

    private void initData() {

        mInfoBeanArrayList = new ArrayList<StudentClassInfoBean>();

        String[] classInfoImageUrl = getResources().getStringArray(R.array.classInfoImageUrl);
        String[] classInfoClassName = getResources().getStringArray(R.array.classInfoClassName);
        String[] classInfoClassPosition = getResources().getStringArray(R.array.classInfoClassPosition);
        String[] classInfoClassTeacher = getResources().getStringArray(R.array.classInfoClassTeacher);
        String[] classInfoClassTime = getResources().getStringArray(R.array.classInfoClassTime);
        String[] classInfoClassYuYueNum = getResources().getStringArray(R.array.classInfoClassYuYueNum);
        String[] classInfoClassYingDaoNum = getResources().getStringArray(R.array.classInfoClassYingDaoNum);

        for (int i = 0; i < classInfoClassName.length; i++) {
            StudentClassInfoBean mInfoBean = new StudentClassInfoBean();

            mInfoBean.setImageUrl(classInfoImageUrl[i]);
            mInfoBean.setClassName(classInfoClassName[i]);
            mInfoBean.setClassPosition(classInfoClassPosition[i]);
            mInfoBean.setClassTeacher(classInfoClassTeacher[i]);
            mInfoBean.setClassTime(classInfoClassTime[i]);
            mInfoBean.setYuYueNum(Integer.parseInt(classInfoClassYuYueNum[i]));
            mInfoBean.setYinDaoNum(Integer.parseInt(classInfoClassYingDaoNum[i]));
            mInfoBeanArrayList.add(mInfoBean);
        }
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
//        mSfClassInfo.setListView(mLvInfoClass);
        mLvInfoClass.setAdapter(mClassInfoAdapter);
//        mSfClassInfo.setOnRefreshListener(this);
//        mSfClassInfo.setOnLoadListener(this);
        mLvInfoClass.setOnItemClickListener(this);

    }

    /**
     * 虚拟数据
     */
    private void initListViewData() {
        mClassInfoAdapter = new ClassInfoAdapter(getContext(), mInfoBeanArrayList);
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
                , mClassInfoAdapter.getItem(position));//跳转到ClassInfoDetailActivity
    }

  /*  @Override
    public void onRefresh() {
        mSfClassInfo.postDelayed(new Runnable() {
            @Override
            public void run() {
                mInfoBeanArrayList.clear();
                mRefreshTime++;
                for (int i = 0; i < 18; i++) {
                    int imageIndex = i % mInfoBeanArrayList.size();
//                    mInfoBeanArrayList.add(mInfoBeanArrayList.get(imageIndex).getClassName() + "第" + mRefreshTime + "次版本");
                    StudentClassInfoBean mInfoBean = new StudentClassInfoBean();
                    mInfoBean.setClassName(mInfoBeanArrayList.get(imageIndex).getClassName() + "第" + mRefreshTime + "次版本");
                    mInfoBean.setImageUrl(mInfoBeanArrayList.get(imageIndex).());
                    mInfoBeanArrayList.add();
                }
                mClassInfoAdapter.notifyDataSetChanged();
//                mSfClassInfo.cancelLongPress();
                mSfClassInfo.setRefreshing(false);//停止刷新
            }
        }, 1500);
    }*/

   /* @Override
    public void onLoad() {
        mSfClassInfo.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 12; i++) {
                    int imageIndex = i % mInfoBeanArrayList.size();
                    mStringArrayList.add(mInfoBeanArrayList.get(imageIndex));
                }
                mClassInfoAdapter.notifyDataSetChanged();
                mSfClassInfo.setLoading(false);
            }
        }, 2000);
    }*/
}
