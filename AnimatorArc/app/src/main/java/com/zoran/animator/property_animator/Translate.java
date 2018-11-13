package com.zoran.animator.property_animator;

import android.view.View;

/**
 * Created by zqs on 2018/11/7.
 */
public class Translate implements PropertyAnimation{


    /**
     * 设置view的平移动画
     * animate().translationX(300.0f)
     */
    @Override
    public void startCode(View view) {
        view.animate().translationX(300.0f).setDuration(1000).start();
    }
}
