package com.zoran.animator.between_animator;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;

import com.zoran.animator.BaseApplication;
import com.zoran.animator.R;

/**
 * Created by zqs on 2018/11/7.
 */
public class Rotate implements BetweenAnimation{

    /**
     <rotate
     android:fromDegrees="0"
     android:toDegrees="90"/>
     顺时针转动角度
     */
    @Override
    public void startXML(View view) {
        Animation animation = AnimationUtils.loadAnimation(BaseApplication.getContext(), R.anim.rotate);
        animation.setDuration(1000);
        animation.setFillAfter(true); //动画完成后,时候保持最后的效果
        view.startAnimation(animation);
    }

    /**
     mFromDegrees = fromDegrees;
     mToDegrees = toDegrees;
     mPivotX = 0.0f;  旋转X轴点
     mPivotY = 0.0f;  旋转Y轴点
     */
    @Override
    public void startCode(View view) {
        //根据中心点旋转
        Animation animation = new RotateAnimation(0, 90, view.getWidth()/2, view.getHeight()/2);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }
}
