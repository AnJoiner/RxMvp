package com.coder.tasker;

import android.app.Application;

/**
 * @auther: AnJoiner
 * @datetime: 2019/5/14
 */
public class BaseApplication extends Application {

    public static BaseApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static BaseApplication getInstance() {
        return mApplication;
    }
}
