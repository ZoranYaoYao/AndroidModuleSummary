package com.zoran.animator.property_animator.AnimatorSet;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by zqs on 2018/11/14.
 *
 * 动画集合播放
 */
public class AnimatorSetTest {

    public static void startCode(View view) {
        ObjectAnimator translation = ObjectAnimator.ofFloat(view, "translationX",200, 500, 200);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(view, "rotation",0f, 360f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 1f,0f, 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(translation).with(rotate).before(alpha);
        animatorSet.setDuration(5000);
        animatorSet.start();
    }
}
