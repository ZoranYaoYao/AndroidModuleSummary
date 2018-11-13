package com.zoran.animator;

import android.app.Application;
import android.content.Context;

/**
 * Created by zqs on 2018/11/6.
 */
public class BaseApplication extends Application{

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
