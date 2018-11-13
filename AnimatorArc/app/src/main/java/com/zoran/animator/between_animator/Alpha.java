package com.zoran.animator.between_animator;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.zoran.animator.BaseApplication;
import com.zoran.animator.R;

/**
 * Created by zqs on 2018/11/7.
 */
public class Alpha implements BetweenAnimation {


    /**
     <alpha
     android:fromAlpha="0"
     android:toAlpha="1"/>
     */
    @Override
    public void startXML(View view) {
        Animation animation = AnimationUtils.loadAnimation(BaseApplication.getContext(), R.anim.alpha);
        animation.setFillAfter(true);
        animation.setDuration(1000);
        view.startAnimation(animation);
    }

    /**
     * public AlphaAnimation(float fromAlpha, float toAlpha)
     */
    @Override
    public void startCode(View view) {
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setFillAfter(true);
        animation.setDuration(1000);
        view.startAnimation(animation);
    }
}
