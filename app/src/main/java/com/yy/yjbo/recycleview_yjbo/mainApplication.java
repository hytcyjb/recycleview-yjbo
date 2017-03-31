package com.yy.yjbo.recycleview_yjbo;

import android.app.Application;

import com.yy.yjbo.recycleview_yjbo.util.LogUtils;

/**
 * Created by yjbo
 * Date : 2017/3/31 10:01.
 * QQ mail : 1457521527@qq.com
 */

public class mainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //debug版本打开log，发布版不打开；
        if (BuildConfig.DEBUG) {
//            new LogUtils(true);
//            LogUtils.d("--启动logger---");
        }
    }
}
