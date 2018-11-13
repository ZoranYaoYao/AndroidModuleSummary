package com.zoran.animator.property_animator;

import android.view.View;

/**
 * Created by zqs on 2018/11/7.
 */
public class Rotate implements PropertyAnimation {


    /**
     * 围绕着中心点旋转45度
     *  rotation(45f)
     */
    @Override
    public void startCode(View view) {
        view.animate().rotation(45f).setDuration(1000).start();
//        view.animate().rotationX(180f).setDuration(1000).start(); //Core. 视图中心,围绕着X轴旋转180度
//        view.animate().rotationY(180f).setDuration(1000).start(); //Core. 视图中心,围绕着Y轴旋转180度
    }
}
