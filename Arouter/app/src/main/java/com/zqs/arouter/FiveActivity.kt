package com.zqs.arouter

import android.app.Activity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route

/**
 * Created by zqs on 2021/7/18.
 *
 * 处理跳转结果
 *
 * 【作用】
 * 用于跳转界面获得结果之后的处理
 */
@Route(path = "/app/FiveActivity")
class FiveActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_5)
    }
}