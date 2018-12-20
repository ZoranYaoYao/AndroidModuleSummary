package com.zoran.animator.property_animator.ObjectAnimator;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * Created by zqs on 2018/11/13.
 */
public class OfInt implements Action {
    @Override
    public void startXML(View view) {

    }

    /**
     * 核心点：有get(), set()自动赋给对象的属性
     * 问题：那么ofFloat()的第二个参数还能传入什么属性值呢？
     * 答案：任意属性值。因为：
     */
    @Override
    public void startCode(View view) {
        ObjectAnimator animator = null;
        //        ObjectAnimator animator = ObjectAnimator.ofFloat(Object object, String property, float ....values);
        // ofFloat()作用有两个
        // 1. 创建动画实例
        // 2. 参数设置：参数说明如下
        // Object object：需要操作的对象
        // String property：需要操作的对象的属性
        // float ....values：动画初始值 & 结束值（不固定长度）
        // 若是两个参数a,b，则动画效果则是从属性的a值到b值
        // 若是三个参数a,b,c，则则动画效果则是从属性的a值到b值再到c值
        // 以此类推
        // 至于如何从初始值 过渡到 结束值，同样是由估值器决定，此处ObjectAnimator.ofFloat（）是有系统内置的浮点型估值器FloatEvaluator，同ValueAnimator讲解

        animator.setDuration(500);
        // 设置动画运行的时长

        animator.setStartDelay(500);
        // 设置动画延迟播放时间

        animator.setRepeatCount(0);
        // 设置动画重复播放次数 = 重放次数+1
        // 动画播放次数 = infinite时,动画无限重复

        animator.setRepeatMode(ValueAnimator.RESTART);
        // 设置重复播放动画模式
        // ValueAnimator.RESTART(默认):正序重放
        // ValueAnimator.REVERSE:倒序回放

        animator.start();
        // 启动动画
    }
}
