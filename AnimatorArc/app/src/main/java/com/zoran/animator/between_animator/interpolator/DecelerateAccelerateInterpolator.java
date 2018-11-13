package com.zoran.animator.between_animator.interpolator;

import android.view.animation.Interpolator;

/**
 * Created by zqs on 2018/11/7.
 */
public class DecelerateAccelerateInterpolator implements Interpolator{

    /**
     *  result 最后的值是0f~1f, 表示当前值占总值(endValue-startValue)的百分比
     */
    @Override
    public float getInterpolation(float input) {
        float result;
        if (input <= 0.5) {
            result = (float) (Math.sin(Math.PI * input)) /2;
        } else {
            result = (float) (2 - Math.sin(Math.PI * input)) /2;
        }

        return result;
    }
}
