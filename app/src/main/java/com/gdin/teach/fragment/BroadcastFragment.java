package com.gdin.teach.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.TextView;
import android.widget.Toast;

import com.gdin.teach.Constan;
import com.gdin.teach.MyApplication;
import com.gdin.teach.R;
import com.gdin.teach.activity.BroadcastActivity;
import com.gdin.teach.adapter.BroadcastAdapter;
import com.gdin.teach.bean.BroadCastBean;
import com.gdin.teach.util.CommomUtil;
import com.umeng.socialize.Config;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.recyclerview.animators.ScaleInLeftAnimator;

import static com.umeng.socialize.bean.SHARE_MEDIA.QQ;
import static com.umeng.socialize.bean.SHARE_MEDIA.QZONE;
import static com.umeng.socialize.bean.SHARE_MEDIA.WEIXIN;
import static com.umeng.socialize.bean.SHARE_MEDIA.WEIXIN_CIRCLE;

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
    @Bind(R.id.bt_cancle_broadcast_file)
    Button mBtCancleBroadcastFile;

    private WindowManager.LayoutParams mParams;
    private Window mWindow;
    private PopupWindow mPopupWindow;
    private String mTitle;
    private String mContent;
    private int mTimesSaved;
    private BroadCastBean mBroadCastBean;
    private List<BroadCastBean> mBroadCastBeanList;
    private UMShareAPI mShareAPI;
    private SHARE_MEDIA mSHARE_media;
    private BroadcastAdapter mAdapter;


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
            hideEditBroadcast();
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
        mAdapter = new BroadcastAdapter(mBroadCastBeanList, getContext());
        mRvBroadcast.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext()));//RecycleView的用法
        mRvBroadcast.setItemAnimator(new ScaleInLeftAnimator());
//        mRvBroadcast.addItemDecoration(new DividerGridItemDecoration(getContext()));
        mRvBroadcast.setHasFixedSize(true);
        mRvBroadcast.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BroadcastAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showBroadcastShare(mBroadCastBeanList.get(position).getTitle(), mBroadCastBeanList
                        .get(position).getContent());
            }

            @Override
            public void onItemLongClick(View view, int position) {
                //长按删除
                initDeleteDialog(position);
            }
        });
    }

    private void initDeleteDialog(final int position) {
        View mView = getActivity().getLayoutInflater().inflate(R.layout.dialog_in_class_submit, null);
        TextView mTextView = (TextView) mView.findViewById(R.id.tv_dialog_content);
        Button mLeftButton = (Button) mView.findViewById(R.id.bt_dialog_left);
        Button mRightButton = (Button) mView.findViewById(R.id.bt_dialog_right);
        mTextView.setText(Constan.DELECTORNOT);
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
                mBroadCastBeanList.remove(position);
                if (mBroadCastBeanList.size() == 0) {
                    MyApplication.mSharedPreferences.edit().remove(Constan.HADSAVEDFILES).commit();
                    showEditBroadcast();
                }
                MyApplication.mSharedPreferences.edit().remove(Constan.SAVEDBROADCASTTITLE + (position + 1)).commit();
                MyApplication.mSharedPreferences.edit().remove(Constan.SAVEDBROADCASTCONTENT + (position + 1)).commit();
                --mTimesSaved;
                if (mAdapter != null) {
                    mAdapter.notifyItemRemoved(position);
                    mPopupWindow.dismiss();
                }
            }
        });
    }

    private void showBroadcastShare(String title, String content) {
        showEditBroadcast();
        mBtCancleBroadcastFile.setText(Constan.BROADCASTCANCLESHARE);
        mBtSavedBroadcastFile.setText(Constan.BROADCASTSHARE);
        mEtBroadcastTitle.setText(title);
        mEtBroadcastContent.setFocusable(false);
        mEtBroadcastContent.setText(content);
        mEtBroadcastTitle.setFocusable(false);
    }

    private void hideEditBroadcast() {
        mEtBroadcastContent.setVisibility(View.GONE);
        mEtBroadcastTitle.setVisibility(View.GONE);
        mBtSavedBroadcastFile.setVisibility(View.GONE);
        mBtCancleBroadcastFile.setVisibility(View.GONE);
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
        mBtCancleBroadcastFile.setVisibility(View.VISIBLE);
        mBtCancleBroadcastFile.setText(Constan.BROADCASTCANCLESAVED);
        mBtSavedBroadcastFile.setText(Constan.BROADCASTSAVED);
        mEtBroadcastTitle.setFocusable(true);
        mEtBroadcastTitle.setFocusable(true);

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
                hideEditBroadcast();
                initRecycleViewData();
                initRecycleView();
            }
        });

    }

    @OnClick({R.id.bt_saved_broadcast_file, R.id.tv_add_broadcast, R.id.bt_cancle_broadcast_file})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_saved_broadcast_file:
                if (Constan.BROADCASTSAVED.equals(mBtSavedBroadcastFile.getText())) {
                    mTitle = mEtBroadcastTitle.getText().toString().trim();
                    mContent = mEtBroadcastContent.getText().toString().trim();
                    if ("".equals(mTitle) || "".equals(mContent)) {
                        CommomUtil.toastApplicationMessage(getContext(), Constan.BROADCASTTITLECONTENT);
                        return;
                    }
                    initSavedDialog();//初始化保存dialog
                } else {
                    //share
                    initShare();
                }
                break;
            case R.id.tv_add_broadcast:
                mEtBroadcastContent.setText("");
                mEtBroadcastTitle.setText("");
                showEditBroadcast();
                break;
            case R.id.bt_cancle_broadcast_file:
                hideEditBroadcast();
                break;
        }
    }

    /**
     * 分享
     */
    private void initShare() {
        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setTitle("分享");
        dialog.setMessage("正在跳转中，客官请稍等");
        Config.dialog = dialog;


        mShareAPI = UMShareAPI.get(getActivity().getApplicationContext());

        final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
                {
                        WEIXIN, WEIXIN_CIRCLE, QQ, QZONE
                };
        UMImage image = new UMImage(getContext(),
                BitmapFactory.decodeResource(getResources(), R.drawable.school_icon));

        new ShareAction(getActivity()).setDisplayList(displaylist)
                .withMedia(image)
                .setListenerList(mUMShareListener)
                .setShareboardclickCallback(shareBoardlistener)
                .open();
    }

    private UMShareListener mUMShareListener = new UMShareListener() {

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            Toast.makeText(getActivity(), " 分享成功", Toast.LENGTH_SHORT).show();
//            BroadcastActivity mBroadcastActivity = (BroadcastActivity) getActivity();
//            mBroadcastActivity.setFocuseToolBar();
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            Toast.makeText(getActivity(), " 分享失败", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {
            Toast.makeText(getActivity(), " 分享取消", Toast.LENGTH_SHORT).show();

        }
    };

    private ShareBoardlistener shareBoardlistener = new ShareBoardlistener() {
        @Override
        public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
            Bitmap mBitmap = CommomUtil.takeScreenShot(getActivity());
            UMImage mImage = new UMImage(getActivity().getApplicationContext(), mBitmap);

            switch (share_media) {
                case WEIXIN:
                    mSHARE_media = WEIXIN;
                    break;
                case WEIXIN_CIRCLE:
                    mSHARE_media = WEIXIN_CIRCLE;
                    break;
                case QQ:
                    mSHARE_media = QQ;
                    break;
                case QZONE:
                    mSHARE_media = QZONE;
                    break;
            }

            new ShareAction(getActivity())
                    .setPlatform(mSHARE_media)
                    .setCallback(mUMShareListener)
                    .withText(mEtBroadcastContent.getText().toString().trim())
                    .withMedia(mImage)
                    .withTargetUrl(Constan.SHAREURL)
                    .withTitle(mEtBroadcastTitle.getText().toString().trim())
                    .share();
        }

    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mShareAPI.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(getActivity()).onActivityResult(requestCode, resultCode, data);
    }


}
