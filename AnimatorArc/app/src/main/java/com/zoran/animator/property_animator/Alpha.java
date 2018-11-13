package com.zoran.animator.property_animator;

import android.view.View;

/**
 * Created by zqs on 2018/11/7.
 */
public class Alpha implements PropertyAnimation {

    /**
     * 将view透明度设置为0f
     */
    @Override
    public void startCode(View view) {
        view.animate().alpha(0f).setDuration(3000).start();
    }
}
