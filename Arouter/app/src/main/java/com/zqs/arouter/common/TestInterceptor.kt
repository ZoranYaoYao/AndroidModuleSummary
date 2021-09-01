package com.zqs.arouter.common

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor

/**
 * Created by zqs on 2021/7/18.
 *
 * 声明拦截器(拦截跳转过程，面向切面编程)
 *
 * 1、拦截器使用方式
 * 路由拦截器通过注解自动注入，不用初始化，路由跳转时就会调用
 */
// 比较经典的应用就是在跳转过程中处理登陆事件，这样就不需要在目标页重复做登陆检查
// 拦截器会在跳转之间执行，多个拦截器会按优先级顺序依次执行
@Interceptor(priority = 8, name = "测试用拦截器")
class TestInterceptor: IInterceptor {
    override fun init(context: Context?) {
        // 拦截器的初始化，会在sdk初始化的时候调用该方法，仅会调用一次
        Log.e("zqs", "TestInterceptor init()")
    }

    override fun process(postcard: Postcard?, callback: InterceptorCallback?) {
        Log.e("zqs", "TestInterceptor process()")

        callback?.onContinue(postcard);  // 处理完成，交还控制权

        // callback.onInterrupt(new RuntimeException("我觉得有点异常"));      // 觉得有问题，中断路由流程
        // 以上两种至少需要调用其中一种，否则不会继续路由
    }


}