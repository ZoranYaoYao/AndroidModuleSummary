package com.zoran.animator.property_animator;

import android.view.View;

/**
 * Created by zqs on 2018/11/7.
 */
public class Scale implements PropertyAnimation {


    /**
     * 扩大至2倍
     *  scaleX(2).scaleY(2)
     */
    @Override
    public void startCode(View view) {
        view.animate().scaleX(2).scaleY(2).setDuration(1000).start();
    }
}
