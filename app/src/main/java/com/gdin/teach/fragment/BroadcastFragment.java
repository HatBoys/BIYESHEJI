package com.gdin.teach.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gdin.teach.Constan;
import com.gdin.teach.MyApplication;
import com.gdin.teach.R;
import com.gdin.teach.adapter.BroadcastAdapter;
import com.gdin.teach.bean.BroadCastBean;
import com.gdin.teach.util.CommomUtil;
import com.gdin.teach.view.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.recyclerview.animators.ScaleInLeftAnimator;

/**
 * Created by 黄培彦 on 16/3/25.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class BroadcastFragment extends BaseFragment {
    @Bind(R.id.et_broadcast_title)
    EditText mEtBroadcastTitle;
    @Bind(R.id.et_broadcast_content)
    EditText mEtBroadcastContent;
    @Bind(R.id.rl_broadcast)
    LinearLayout mRlBroadcast;
    @Bind(R.id.bt_saved_broadcast_file)
    Button mBtSavedBroadcastFile;
    @Bind(R.id.rv_broadcast)
    RecyclerView mRvBroadcast;
    @Bind(R.id.tv_add_broadcast)
    TextView mTvAddBroadcast;

    private WindowManager.LayoutParams mParams;
    private Window mWindow;
    private PopupWindow mPopupWindow;
    private String mTitle;
    private String mContent;
    private int mTimesSaved;
    private BroadCastBean mBroadCastBean;
    private List<BroadCastBean> mBroadCastBeanList;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWindow = getActivity().getWindow();
        mParams = mWindow.getAttributes();
        mBroadCastBeanList = new ArrayList<BroadCastBean>();
    }

    @Override
    public View initView() {
        View mView = getActivity().getLayoutInflater().inflate(R.layout.fragment_broadcast, null);
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
        if (MyApplication.mSharedPreferences.getBoolean(Constan.HADSAVEDFILES, false)) {
            hideBroadcasr();
            initRecycleViewData();
            initRecycleView();
        } else {
            showEditBroadcast();
        }

    }

    private void initRecycleViewData() {
        mBroadCastBeanList.clear();
        for (int i = 1; i < 1992; i++) {
            if (!MyApplication.mSharedPreferences.contains(Constan.SAVEDBROADCASTTITLE + i)) {
                mTimesSaved = i - 1;
                return;
            }
            String title = MyApplication.mSharedPreferences.getString(Constan.SAVEDBROADCASTTITLE + i, "");
            String content = MyApplication.mSharedPreferences.getString(Constan.SAVEDBROADCASTCONTENT + i, "");
            mBroadCastBean = new BroadCastBean();
            mBroadCastBean.setTitle(title);
            mBroadCastBean.setContent(content);
            mBroadCastBeanList.add(mBroadCastBean);
        }
//        CommomUtil.toastMessage(getContext(), mBroadCastBeanList.get(0).getContent());
    }

    private void initRecycleView() {

        mRvBroadcast.setVisibility(View.VISIBLE);
        BroadcastAdapter mAdapter = new BroadcastAdapter(mBroadCastBeanList, getContext());
        mRvBroadcast.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));//RecycleView的用法
        mRvBroadcast.setItemAnimator(new ScaleInLeftAnimator());
//        mRvBroadcast.addItemDecoration(new DividerGridItemDecoration(getContext()));
        mRvBroadcast.setHasFixedSize(true);
        mRvBroadcast.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BroadcastAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }

    private void hideBroadcasr() {
        mEtBroadcastContent.setVisibility(View.GONE);
        mEtBroadcastTitle.setVisibility(View.GONE);
        mBtSavedBroadcastFile.setVisibility(View.GONE);
        mRvBroadcast.setVisibility(View.VISIBLE);
        mTvAddBroadcast.setVisibility(View.VISIBLE);
    }

    /**
     * 显示编辑公告
     */
    private void showEditBroadcast() {
        mRvBroadcast.setVisibility(View.GONE);
        mTvAddBroadcast.setVisibility(View.GONE);
        mEtBroadcastContent.setVisibility(View.VISIBLE);
        mEtBroadcastTitle.setVisibility(View.VISIBLE);
        mBtSavedBroadcastFile.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    private void initSavedDialog() {
        View mView = getActivity().getLayoutInflater().inflate(R.layout.dialog_in_class_submit, null);
        TextView mTextView = (TextView) mView.findViewById(R.id.tv_dialog_content);
        Button mLeftButton = (Button) mView.findViewById(R.id.bt_dialog_left);
        Button mRightButton = (Button) mView.findViewById(R.id.bt_dialog_right);
        mTextView.setText(Constan.SAVEDORNOT);
        mLeftButton.setText(Constan.CANCLE);
        mRightButton.setText(Constan.SURE);

        mPopupWindow = CommomUtil.showPopupWindow(mView);
        mParams.alpha = 0.2f;//设置背景颜色
        mWindow.setAttributes(mParams);

        mPopupWindow.showAtLocation(mRlBroadcast, Gravity.CENTER, 0, 0);

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
                //cancle
                mPopupWindow.dismiss();
            }
        });

        mRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++mTimesSaved;
                MyApplication.mSharedPreferences.edit().putBoolean(Constan.HADSAVEDFILES, true).commit();
                MyApplication.mSharedPreferences.edit().putString(Constan.SAVEDBROADCASTTITLE + mTimesSaved, mTitle).commit();
                MyApplication.mSharedPreferences.edit().putString(Constan.SAVEDBROADCASTCONTENT + mTimesSaved, mContent).commit();
                mPopupWindow.dismiss();
                CommomUtil.toastMessage(getContext(), Constan.SUBMITSUCCESS);
                CommomUtil.toastMessage(getContext(), mTimesSaved + "");
                hideBroadcasr();
                initRecycleViewData();
                initRecycleView();
            }
        });

    }

    @OnClick({R.id.bt_saved_broadcast_file, R.id.tv_add_broadcast})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_saved_broadcast_file:
                mTitle = mEtBroadcastTitle.getText().toString().trim();
                mContent = mEtBroadcastContent.getText().toString().trim();
                if ("".equals(mTitle) || "".equals(mContent)) {
                    CommomUtil.toastApplicationMessage(getContext(), Constan.BROADCASTTITLECONTENT);
                    return;
                }
                initSavedDialog();//初始化保存dialog
                break;
            case R.id.tv_add_broadcast:
                mEtBroadcastContent.setText("");
                mEtBroadcastTitle.setText("");
                showEditBroadcast();
                break;
        }
    }
}
