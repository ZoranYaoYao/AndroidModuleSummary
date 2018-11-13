package com.zoran.animator.property_animator.ValueAnimator;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;

import com.zoran.animator.BaseApplication;
import com.zoran.animator.R;

/**
 * Created by zqs on 2018/11/9.
 */
public class OfInt implements Action {
    @Override
    public void startXML(final View view) {
        ValueAnimator animator = (ValueAnimator) AnimatorInflater.loadAnimator(BaseApplication.getContext(), R.animator.valueanimator_ofint);
        animator.setTarget(view);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.e("zqs", "animation current value = " + animation.getAnimatedValue());
                view.getLayoutParams().width = (int) animation.getAnimatedValue();
                view.requestLayout();
            }
        });
        animator.start();
    }

    /**
     * ofInt
     *  主要是 手动修改监听器返回的时间对应值 addUpdateListener()
     *
     *  推荐使用代码方式实习动画
     */
    @Override
    public void startCode(final View view) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(view.getWidth(), 400);
        valueAnimator.setDuration(1000);
        /**Core. 多了一个手动更改监听器*/
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.e("zqs", "animation current value = " + animation.getAnimatedValue());
                view.getLayoutParams().width = (int) animation.getAnimatedValue();
                view.requestLayout();
            }
        });

        valueAnimator.start();
    }
}
