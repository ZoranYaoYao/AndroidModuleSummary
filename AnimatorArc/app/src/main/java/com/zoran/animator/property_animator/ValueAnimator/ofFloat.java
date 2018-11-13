package com.zoran.animator.property_animator.ValueAnimator;

import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.view.View;

import com.zoran.animator.BaseApplication;
import com.zoran.animator.R;

/**
 * Created by zqs on 2018/11/9.
 */
public class ofFloat implements Action {

    @Override
    public void startXML(final View view) {
        ValueAnimator valueAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(BaseApplication.getContext(), R.animator.valueanimator_offloat);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setAlpha((Float) animation.getAnimatedValue());
                view.requestLayout();
            }
        });
        valueAnimator.start();
    }

    /**
     * 针对Float类型的值, 进行设置动画
     * @param view
     */
    @Override
    public void startCode(final View view) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1f, 0f);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setAlpha((Float) animation.getAnimatedValue());
                view.requestLayout();
            }
        });
        valueAnimator.start();
    }
}
