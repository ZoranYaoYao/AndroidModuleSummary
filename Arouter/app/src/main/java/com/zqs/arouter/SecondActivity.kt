package com.zqs.arouter

import android.app.Activity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route

/**
 * Created by zqs on 2021/7/16.
 *
 * 直接跳转案例
 */
@Route(path = "/app/SecondActivity")
class SecondActivity  : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }

}