package com.zqs.arouter

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter


/**
 * Created by zqs on 2021/7/16.
 * refer:
 * https://github.com/alibaba/ARouter/issues/122
 *
 * 为每一个参数声明一个字段，并使用 @Autowired 标注
 * URL中不能传递Parcelable类型数据，通过ARouter api可以传递Parcelable对象
 *
 * 问题：
 * 在Kotlin文件中，需要同时使用@JvmField和@Autowired()
 */

@Route(path = "/app/FourActivity")
class FourActivity : Activity() {

    @JvmField
    @Autowired(name = "key1")
    public var key1: Long = 0L

    @JvmField
    @Autowired(name = "key3")
    public var key3 = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)

        // ARouter会自动对字段进行赋值，无需主动获取
        Toast.makeText(this, "$key1,  $key3", Toast.LENGTH_SHORT).show()
    }
}

