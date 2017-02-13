package com.andy.sixha;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * @author hehaodong
 * @Filename:com.andy.sixha
 * @Description:
 * @Copyright: Copyright (c) 2014 Tuandai Inc. All Rights Reserved.
 * @date 2016-11-25 10:00
 */
public class MyApplication extends Application {
    private static MyApplication sInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        //��ʼ��bugly
        sInstance = this;
        CrashReport.initCrashReport(getApplicationContext());
    }
    public static MyApplication getInstance() {
        return sInstance;
    }
}
