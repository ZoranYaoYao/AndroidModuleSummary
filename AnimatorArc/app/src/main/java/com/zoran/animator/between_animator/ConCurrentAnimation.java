package com.zoran.animator.between_animator;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.zoran.animator.BaseApplication;
import com.zoran.animator.R;

/**
 * Created by zqs on 2018/11/7.
 */
public class ConCurrentAnimation implements BetweenAnimation {
    @Override
    public void startXML(View view) {
        Animation animation = AnimationUtils.loadAnimation(BaseApplication.getContext(), R.anim.concurrent_anim);
        view.startAnimation(animation);
    }

    @Override
    public void startCode(View view) {
        Animation tranlate = new TranslateAnimation(0,200, 0, 0);
        tranlate.setDuration(1000);
        Animation scale = new ScaleAnimation(1,2,1,2);
        scale.setDuration(1000);

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(tranlate);
        animationSet.addAnimation(scale);
        animationSet.setFillAfter(true);
        view.startAnimation(animationSet);
    }
}
