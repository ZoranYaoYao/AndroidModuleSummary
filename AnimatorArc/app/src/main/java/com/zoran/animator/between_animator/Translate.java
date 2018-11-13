package com.zoran.animator.between_animator;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import com.zoran.animator.BaseApplication;
import com.zoran.animator.R;

/**
 * 补间动画-平移动画
 *  1. 动画移动完成后, 不会在动画移动的位置, 需要设置animation.setFillAfter(true); 保持在动画结束位置
 *
 *  2. 补间动画的原理是设置了View的内容的偏移
 *      因为translateAnimation动画只是移动了内容，view本身其实还在原地，所以点击事件也跟着在原地
 *      https://www.cnblogs.com/gangmiangongjue/p/4680031.html
 */
public class Translate implements BetweenAnimation{


    /**
     * 1. XML方式
     *      通过AnimationUtils加装的方式给View设置通话
     *
        <translate
         android:fromXDelta="100%"
         android:toXDelta="200%"
         android:duration="1000"/>
         fromXDelta: X轴方向开始位置，可以是%，也可以是具体的像素
         toXDelta:   X轴方向结束位置，可以是%，也可以是具体的像素
         fromYDelta: Y轴方向开始位置，可以是%，也可以是具体的像素
         toYDelta:    Y轴方向结束位置，可以是%，也可以是具体的像素
     */
    public void startXML(View view) {
        Animation animation = AnimationUtils.loadAnimation(BaseApplication.getContext(), R.anim.translate);
        view.startAnimation(animation); //Core. 用view进行启动动画
    }

    /**
     * 2. Code方式
     *      通过创建一个TranslateAnimation对象,赋值给view, 进行动画
     */
    public void startCode(View view) {
        //    public TranslateAnimation(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta) {
        TranslateAnimation animation = new TranslateAnimation(0, 1080-view.getWidth(), 0, 0);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }

    /**
     * 3.
     * 由于平移动画,没有改变view的真正位置, 所以通过layout()重新定位
     * https://www.cnblogs.com/swenze/p/3643445.html
     *
     * 通过实验可知, fromXDelta 是指根据当前位置偏移多少距离,进行动画
     * eg: fromXDelta= 0; 动画运行一次left=200; 下一次运行也是从200的位置开始而不是从0的手机坐标位置开始
     */
    public static int count =1;
    public void startCodeFixClick(final View view) {
        TranslateAnimation animation = new TranslateAnimation(0,200,0,0);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.clearAnimation(); //Core.
                int left = 200*(count++);
                int top = view.getTop();
                int right = left+view.getWidth();
                int bottom = top +view.getHeight();
                view.layout(left,top,right,bottom);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animation);
    }
}
