package com.zoran.animator.between_animator;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;

import com.zoran.animator.BaseApplication;
import com.zoran.animator.R;

/**
 * Created by zqs on 2018/11/7.
 */
public class Scale implements BetweenAnimation {

    /**
     <scale
     android:fromXScale="0"
     android:toXScale="1"
     android:fromYScale="0"
     android:toYScale="1"/>
     x,y轴从多大变化到多大倍, 倍数关系
     */
    @Override
    public void startXML(View view) {
        Animation animation = AnimationUtils.loadAnimation(BaseApplication.getContext(), R.anim.scale);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }

    /**
     public ScaleAnimation(float fromX, float toX, float fromY, float toY,
                            float pivotX, float pivotY)
     */
    @Override
    public void startCode(View view) {
        //    public ScaleAnimation(float fromX, float toX, float fromY, float toY,
        //            float pivotX, float pivotY)
        /**根据中心点扩大一倍*/
        Animation animation = new ScaleAnimation(1,2,1,2,view.getWidth()/2,view.getHeight()/2);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }
}
