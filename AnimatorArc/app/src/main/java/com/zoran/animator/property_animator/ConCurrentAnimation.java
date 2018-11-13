package com.zoran.animator.property_animator;

import android.view.View;

/**
 * Created by zqs on 2018/11/7.
 */
public class ConCurrentAnimation implements PropertyAnimation {

    /**
     * 移动200ps同时变大2倍
     * view.animate().setStartDelay(1000)
     */
    @Override
    public void startCode(View view) {
        view.animate().translationX(200f).setDuration(1000).start();
        view.animate().scaleX(2f).scaleY(2f).setDuration(1000).start();
    }
}
