package com.zqs.arouter

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route

/**
 * Created by zqs on 2021/7/16.
 *
 * 跳转带参数
 */
@Route(path = "/app/ThirdActivity")
class ThirdActivity  : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val a = intent
        val key1 = a.getLongExtra("key1" , 0L)
        val key3 = a.getStringExtra("key3")
        val key4 = a.getParcelableExtra<Test>("key4" )

        Toast.makeText(this, "key1= $key1, key3= $key3, key4= $key4", Toast.LENGTH_SHORT).show()
    }

}