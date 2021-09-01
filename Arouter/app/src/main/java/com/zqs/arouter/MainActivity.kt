package com.zqs.arouter

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.TextView
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.parcel.Parcelize


/**
 * ====
 * refer:
 * https://github.com/alibaba/ARouter
 *
 * 参考官网中文介绍进行文档整理的
 * 功能1：
 * 支持组件之间的跳转，带参数的跳转 [SecondActivity] [ThirdActivity]
 *
 * 功能2：
 * 插件Arouter Helper不支持Kotlin语言
 *
 * 功能3：
 * 声明拦截器(拦截跳转过程，面向切面编程)[TestInterceptor]
 *
 * 功能4：
 * 处理跳转结果[FiveActivity]
 *
 * 功能5：
 * 自定义全局降级策略 [DegradeServiceImpl]
 *
 * 功能6：
 * 预处理服务 [PretreatmentServiceImpl]
 *
 * 功能7：
 * 使用绿色通道(跳过所有的拦截器)
 * ARouter.getInstance().build("/home/main").greenChannel().navigation();
 *
 * =====
 * 官方信息：
 * 1. 路由中的分组概念
 * 可以通过 @Route 注解主动指定分组，否则使用路径中第一段字符串()作为分组
 *
 * =====
 * 解决：Duplicate class android.support.v4
 * https://blog.csdn.net/am256341/article/details/105347194
 *
 * 问题1.
 * 错误: ARouter::Compiler An exception is encountered, [null]
 * 【解决】
 * iswsc的回答，就是说了Arouter这个组件库，在整个Project中，其他Module也要必须要配置！！
 * https://github.com/alibaba/ARouter/issues/417
 *
 * 问题2
 * ARouter::There is no route match the path [/app/SecondActivity], in group [app][ ]
 * 【解决】
 * Kotlin里面的打包使用的是kapt工具打包，所以要使用Kapt插件进行打包，在build.gradle中annotationProcessor不其中作用，导致找不到路径！！
 * https://www.jianshu.com/p/63f86dc8c459
 */
class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv_arouter = findViewById<TextView>(R.id.tv_arouter)
        tv_arouter.setOnClickListener {
            // 1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
            ARouter.getInstance().build("/app/SecondActivity").navigation(this)
        }

        val tv_arouter_1 = findViewById<TextView>(R.id.tv_arouter_1)
        tv_arouter_1.setOnClickListener {
            // 1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
            ARouter.getInstance().build("/app/ThirdActivity")
                    .withLong("key1", 666L)
                    .withString("key3", "888")
                    .withParcelable("key4", Test("Jack", "Rose"))
                    .navigation(this)
        }

        val tv_arouter_2 = findViewById<TextView>(R.id.tv_arouter_2)
        tv_arouter_2.setOnClickListener {
            // 1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
            ARouter.getInstance().build("/app/FourActivity")
                    .withLong("key1", 666L)
                    .withString("key3", "888")
                    .withParcelable("key4", Test("Jack", "Rose"))
                    .navigation(this)
        }


        val tv_arouter_5 = findViewById<TextView>(R.id.tv_arouter_5)
        tv_arouter_5.setOnClickListener {
            // 使用两个参数的navigation方法，可以获取单次跳转的结果
            ARouter.getInstance().build("/app/FiveActivity").navigation(this, object: NavigationCallback {
                override fun onFound(postcard: Postcard?) {
                    Log.e("zqs", "navigation onFound()")
                }

                override fun onLost(postcard: Postcard?) {
                    Log.e("zqs", "navigation onLost()")
                }

                override fun onArrival(postcard: Postcard?) {
                    Log.e("zqs", "navigation onArrival()")
                }

                override fun onInterrupt(postcard: Postcard?) {
                    Log.e("zqs", "navigation onInterrupt()")
                }
            })
        }

        val tv_arouter_6 = findViewById<TextView>(R.id.tv_arouter_6)
        tv_arouter_6.setOnClickListener {
            // 1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
            ARouter.getInstance().build("/abcxxx/xxx").navigation(this)
        }

    }
}

@Parcelize
class Test(val name: String, val name1: String): Parcelable