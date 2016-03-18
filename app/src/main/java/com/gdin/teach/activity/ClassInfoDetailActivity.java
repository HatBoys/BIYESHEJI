package com.gdin.teach.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;

import com.gdin.teach.Constan;
import com.gdin.teach.R;
import com.gdin.teach.fragment.ClassInfoDetailFragment;
import com.gdin.teach.util.CommomUtil;

/**
 * Created by 黄培彦 on 16/3/13.
 * Email: peiyanhuang@yeah.net
 * 类说明: 课程信息详情界面
 */
public class ClassInfoDetailActivity extends BaseActivity {

    private static int mCurrentposition;
    private static String mClassInfo;

    // TODO: 16/3/13 两个onCreate方法，有什么不同点
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
       /* setContentView(R.layout.activity_class_info_detail);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_class_info_detail_activity, new ClassInfoDetailFragment(), Constan.CLASSINFODETAILFRAGMENT)
                .commit();*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl_base, new ClassInfoDetailFragment(mCurrentposition, mClassInfo), Constan.CLASSINFODETAILFRAGMENT)
                .commit();

        mTlBase.setVisibility(View.VISIBLE);
        mTlBase.setNavigationIcon(R.mipmap.back);
        mTlBase.setTitle("");
        mTlBase.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommomUtil.toastMessage(getApplicationContext(), "back");
                finish();
            }
        });
    }

    public void setTitle(String title) {
        if (mTlBase != null) {
            setMyTitle(title);
        }
    }

    /**
     * 跳转到ClassInfoDetailActivity
     *  @param context
     * @param position
     * @param item
     */
    public static void start2ClassInfoDetailActivity(Context context, int position, Object item) {
        Intent mIntent = new Intent(context, ClassInfoDetailActivity.class);
        mCurrentposition = position;
        mClassInfo = (String) item;
        context.startActivity(mIntent);

    }
}
