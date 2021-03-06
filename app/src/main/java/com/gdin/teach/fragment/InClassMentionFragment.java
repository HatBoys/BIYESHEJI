package com.gdin.teach.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.gdin.teach.Constan;
import com.gdin.teach.R;
import com.gdin.teach.adapter.InClassMentionAdapter;
import com.gdin.teach.bean.StudentClassInfoBean;
import com.gdin.teach.bean.StudentInfoBean;
import com.gdin.teach.bean.TakeNotesMottosBean;
import com.gdin.teach.util.CommomUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.recyclerview.animators.ScaleInLeftAnimator;

/**
 * Created by 黄培彦 on 16/3/19.
 * Email: peiyanhuang@yeah.net
 * 类说明: 课堂点名界面
 */
public class InClassMentionFragment extends BaseFragment implements InClassMentionAdapter.OnItemClickLitener {

    @Bind(R.id.rl_in_class_mention)
    RecyclerView mRlInClassMention;
    @Bind(R.id.tv_mention_num)
    TextView mTvMentionNum;
    @Bind(R.id.ll_in_class)
    LinearLayout mLlInClass;
    @Bind(R.id.tv_in_class_null_data)
    TextView mTvInClassNullData;
    private ArrayList<String> mUrlList;
    private LinearLayoutManager mLinearLayoutManager;
    private InClassMentionAdapter mAdapter;
    private String mKillUrl = "";
    private int mKillPosition = -1;
    private ArrayList<String> mKillUrlList;
    private ArrayList<Integer> mKillPositionList;
    private int mUrlKillList;
    private int mPositionKillList;
    private int mAllNum;
    private AlertDialog mAlertDialog;
    private WindowManager.LayoutParams mParams;
    private Window mWindow;
    private PopupWindow mPopupWindow;
    private File mFile;
    private ArrayList<StudentInfoBean> mBeanArrayList;

    public InClassMentionFragment() {
    }

    @SuppressLint("ValidFragment")
    public InClassMentionFragment(ArrayList<String> urlArrayList) {
        mUrlList = urlArrayList;
        mAllNum = urlArrayList.size();
    }

    @Override
    public View initView() {
        return mActivity.getLayoutInflater().inflate(R.layout.fragment_in_class_mention, null);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mKillUrlList = new ArrayList<String>();
        mKillPositionList = new ArrayList<Integer>();
        mFile = new File(String.valueOf(getActivity().getDir(Constan.MENTIONSAVEDFILE
                , Context.MODE_PRIVATE)));

        mWindow = getActivity().getWindow();
        mParams = mWindow.getAttributes();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        initData();
        mTvMentionNum.setText(Constan.MENTIONNUMMESSAGE + mKillUrlList.size() + "/" + mAllNum);

        mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//        new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL)//瀑布流
//        new GridLayoutManager(getActivity(), 3)

//        mRlInClassMention.addItemDecoration(new DividerItemDecoration(getActivity(),
//                DividerItemDecoration.HORIZONTAL_LIST));//ListView 格式的Divider

//        mRlInClassMention.addItemDecoration(new DividerGridItemDecoration(getContext()));
        mRlInClassMention.setLayoutManager(mLinearLayoutManager);//RecycleView的用法
        mRlInClassMention.setItemAnimator(new ScaleInLeftAnimator());
        mRlInClassMention.setHasFixedSize(true);
        mAdapter = new InClassMentionAdapter(getActivity(), mBeanArrayList);
        mAdapter.setOnItemClickLitener(this);
        mRlInClassMention.setAdapter(mAdapter);
        return rootView;
    }

    private void initData() {
        mBeanArrayList = new ArrayList<StudentInfoBean>();

        String[] imageUrl = getResources().getStringArray(R.array.teacherImageUrl);
        String[] studentName = getResources().getStringArray(R.array.studentMentionName);

        for (int i = 0; i < imageUrl.length; i++) {
            StudentInfoBean mStudentInfoBean = new StudentInfoBean();
            mStudentInfoBean.setImageUrl(imageUrl[i]);
            mStudentInfoBean.setName(studentName[i]);
            mBeanArrayList.add(mStudentInfoBean);
        }
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
        mKillUrl = mUrlList.get(position);
        mKillPosition = position;

        mUrlList.remove(position);
        mAdapter.notifyItemRemoved(position);

        mKillUrlList.add(mKillUrl);
        mKillPositionList.add(mKillPosition);

        mTvMentionNum.setText(Constan.MENTIONNUMMESSAGE + mKillUrlList.size() + "/" + mAllNum);

        if (mUrlList.size() == 0) {
            CommomUtil.toastMessage(getContext().getApplicationContext(), "最后一个数据");
            mTvInClassNullData.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemLongClick(View view, int position) {

    }

    @OnClick({R.id.bt_in_class_cancle, R.id.bt_inclass_upload})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_in_class_cancle:
                if (mUrlList.size() == 0) {
                    CommomUtil.toastMessage(getContext().getApplicationContext(), "即将恢复最后一个删除的数据");
                    mTvInClassNullData.setVisibility(View.GONE);
                }
                solveCancle();

                break;

            case R.id.bt_inclass_upload:
                initDialog();
                break;
        }
    }

    private void solveCancle() {
        mUrlKillList = mKillUrlList.size();
        mPositionKillList = mKillPositionList.size();

        if (mUrlKillList > 0 && mPositionKillList > 0 && mPositionKillList == mUrlKillList) {
            mUrlList.add(mKillPositionList.get(mPositionKillList - 1), mKillUrlList.get(mUrlKillList - 1));
            mAdapter.notifyItemInserted(mKillPositionList.get(mPositionKillList - 1));

            mKillUrlList.remove(mUrlKillList - 1);
            mKillPositionList.remove(mPositionKillList - 1);
        }
        if (mUrlKillList == 0 && mPositionKillList == 0) {//已经撤回所有数据
            CommomUtil.toastMessage(getActivity().getApplicationContext(), Constan.ALLREBACK);
        }

        if (mUrlKillList != mPositionKillList) {//操作异常
            CommomUtil.toastMessage(getActivity().getApplicationContext(), Constan.DOERROR);
        }

        mTvMentionNum.setText(Constan.MENTIONNUMMESSAGE + mKillUrlList.size() + "/" + mAllNum);


    }

    /**
     * 实现一个Dialog
     */
    private void initDialog() {
//        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
//        抽取出Dialog的创建共性，作为公共方法
//        final AlertDialog mAlertDialog = mBuilder.create();
        if (mUrlList.size() == 0) {
            mTvInClassNullData.setVisibility(View.VISIBLE);
        }
        View mView = getActivity().getLayoutInflater().inflate(R.layout.dialog_in_class_submit, null);
        TextView mTextView = (TextView) mView.findViewById(R.id.tv_dialog_content);
        Button mLeftButton = (Button) mView.findViewById(R.id.bt_dialog_left);
        Button mRightButton = (Button) mView.findViewById(R.id.bt_dialog_right);
        mTextView.setText(Constan.SUBMIT);
        mLeftButton.setText(Constan.CANCLE);
        mRightButton.setText(Constan.SURE);

       /* mAlertDialog = CommomUtil.showCustomDialog(getContext());

        mAlertDialog.setView(mView);*/

        mPopupWindow = CommomUtil.showPopupWindow(mView);
        mParams.alpha = 0.2f;//设置背景颜色
        mWindow.setAttributes(mParams);

        mPopupWindow.showAtLocation(mLlInClass, Gravity.CENTER, 0, 0);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mParams.alpha = 1f;
                mWindow.setAttributes(mParams);
            }
        });

        mLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                if (mUrlList.size() == 0) {
                    mTvInClassNullData.setVisibility(View.VISIBLE);
                }
            }
        });

        mRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream mFileOutputStream = getActivity().openFileOutput(Constan.MENTIONSAVEDFILE, Context.MODE_PRIVATE);
                    for (int i = 0; i < mUrlList.size(); i++) {
                        mFileOutputStream.write(mUrlList.get(i).getBytes());
                    }
                    mFileOutputStream.close();

                    CommomUtil.toastMessage(getActivity().getApplicationContext(), Constan.SUBMITSUCCESS);//保存到本地数据
                    mPopupWindow.dismiss();
                    if (mUrlList.size() > 0) {
                        mTvInClassNullData.setVisibility(View.GONE);
                    } else {
                        mTvInClassNullData.setVisibility(View.VISIBLE);
                    }

                    /*for (int i = 0; i < mAllNum; i++) {
                        mUrlKillList = mKillUrlList.size();
                        mPositionKillList = mKillPositionList.size();//此处解决提交数据后，重新刷新所有学生名单

                        if (mUrlKillList > 0 && mPositionKillList > 0 && mPositionKillList == mUrlKillList) {
                            mUrlList.add(mKillPositionList.get(mPositionKillList - 1), mKillUrlList.get(mUrlKillList - 1));
                            mAdapter.notifyItemInserted(mKillPositionList.get(mPositionKillList - 1));

                            mKillUrlList.remove(mUrlKillList - 1);
                            mKillPositionList.remove(mPositionKillList - 1);
                        }
                    }*/

                    mTvMentionNum.setText(Constan.MENTIONNUMMESSAGE + mKillUrlList.size() + "/" + mAllNum);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
//        mAlertDialog.show();

    }
}
