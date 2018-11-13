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
 * 组合顺序播放动画
 */
public class ComboAnimation implements BetweenAnimation{

    /**
     *         android:startOffset="500"
     */
    @Override
    public void startXML(View view) {
        Animation animation = AnimationUtils.loadAnimation(BaseApplication.getContext(), R.anim.combo_anim);
        view.startAnimation(animation);
    }

    @Override
    public void startCode(View view) {
        Animation tranlate = new TranslateAnimation(0,200, 0, 0);
        tranlate.setDuration(500);
        Animation scale = new ScaleAnimation(1,2,1,2);
        scale.setStartOffset(500); //Core. 设置开始动画时间的偏移量
        scale.setDuration(500);

        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(tranlate);
        animationSet.addAnimation(scale);
        animationSet.setFillAfter(true);
        view.startAnimation(animationSet);
    }
}
