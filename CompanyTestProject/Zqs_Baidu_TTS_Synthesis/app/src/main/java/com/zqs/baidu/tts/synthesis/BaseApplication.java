package com.zqs.baidu.tts.synthesis;

import android.app.Application;
import android.content.Context;

/**
 * Created by zqs on 2018/5/2.
 */

public class BaseApplication  extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {
        return  mContext;
    }
}
