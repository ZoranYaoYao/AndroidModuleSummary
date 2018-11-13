package com.zoran.animator.between_animator.interpolator;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import com.zoran.animator.BaseApplication;
import com.zoran.animator.R;
import com.zoran.animator.between_animator.BetweenAnimation;

public class TranslateUseIntepolator implements BetweenAnimation{


    @Override
    public void startXML(View view) {

    }

    /**
     * 插值器: 就是控制动画变化的速率的
     * 如何设置插值器
     *  animation.setInterpolator(new DecelerateAccelerateInterpolator());
     */
    public void startCode(View view) {
        TranslateAnimation animation = new TranslateAnimation(0, 400, 0, 0);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animation.setInterpolator(new DecelerateAccelerateInterpolator()); //Core.
        view.startAnimation(animation);
    }

}
