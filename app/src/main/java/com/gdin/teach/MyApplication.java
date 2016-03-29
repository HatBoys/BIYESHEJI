package com.gdin.teach;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.avos.avoscloud.AVOSCloud;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;

/**
 * Created by 黄培彦 on 16/3/10.
 * Email: peiyanhuang@yeah.net
 * 类说明: Application的全局作用
 */
public class MyApplication extends Application {

    public static Context mApplicationContext;
    public static SharedPreferences mSharedPreferences;
    public static MyApplication mInstance;
    private static RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        PlatformConfig.setWeixin("wx932a7b654e1c2b7e", "9a892f298e5f2995afbfab21a9d42e9e");
        PlatformConfig.setQQZone("1105277230", "y7dndjXnjB8Xb76j");

        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this, "PWBmt8TjaRxGWkSTsTSNUtq5-gzGzoHsz", "85RnHou4nMOVn63rQrqNAtzO");

        mApplicationContext = getApplicationContext();
        mRequestQueue = getRequestQueue();
        mSharedPreferences = mApplicationContext.getSharedPreferences(Constan.MYSHAREPREFERENCE, MODE_PRIVATE);
    }

    //异步获取单实例
    public static synchronized MyApplication getInstance() {
        if (mInstance == null) {
            mInstance = new MyApplication();
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            //getApplication()方法返回一个当前进程的全局应用上下文，这就意味着
            //它的使用情景为：你需要一个生命周期独立于当前上下文的全局上下文，
            //即就是它的存活时间绑定在进程中而不是当前某个组建。
            mRequestQueue = Volley.newRequestQueue(mApplicationContext);
        }
        return mRequestQueue;
    }

//    public <T> void addToRequestQueue(Request<T> req) {
//        getRequestQueue().add(req);
//    }

    /**
     * 添加一个网络请求队列并设置一个标签
     *
     * @param request
     * @param tag
     */

    public static void addRequest(Request<?> request, String tag) {
        request.setTag(tag);
        addRequest(request);
    }

    /**
     * 添加一个网络请求队列
     *
     * @param request
     */
    public static void addRequest(Request<?> request) {
        getInstance().getRequestQueue().add(request);
    }

    /**
     * 通过标签取消网络请求队列
     *
     * @param tag
     */
    public static void cancelAllRequests(String tag) {
        if (mRequestQueue == null) {
            getInstance().getRequestQueue().cancelAll(tag);
        }
    }


}
