package com.jerry.rxjavademo.backPress;

/**
 * Created by zqs on 2019/4/14.
 * https://www.jianshu.com/p/ceb48ed8719d
 *
 * 背压速度控制
 * 1. 通过控制观察者接受的个数
 *    专业术语: 响应式拉取
 *      Subscription request(count)
 *
 * 2. 控制被观察者发送事件的速度
 *    专业术语: 反向控制
 *
 * 二. 背压策略模式 BackpressStrategy
 * 1. BackpressStrategy.ERROR
 * 2. BackpressStrategy.MISSING
 * 3. BackpressStrategy.BUFFER
 * 4. BackpressStrategy.DROP
 * 5. BackpressStrategy.LATEST
 */
public class B {
}
