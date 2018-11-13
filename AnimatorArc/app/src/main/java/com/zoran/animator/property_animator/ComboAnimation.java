package com.zoran.animator.property_animator;

import android.view.View;

/**
 * Created by zqs on 2018/11/7.
 */
public class ComboAnimation implements PropertyAnimation {

    /**
     * 移动200ps, 再变大2倍
     * view.animate().setStartDelay(1000)
     */
    @Override
    public void startCode(View view) {
        view.animate().translationX(200f).setDuration(1000).start();
        view.animate().scaleX(2f).scaleY(2f).setStartDelay(1000).setDuration(1000).start();
    }
}
