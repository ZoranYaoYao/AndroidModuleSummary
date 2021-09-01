package com.zqs.arouter.common

import android.content.Context
import android.util.Log
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.DegradeService

/**
 * Created by zqs on 2021/7/19.
 * 自定义全局降级策略
 */
// 实现DegradeService接口，并加上一个Path内容任意的注解即可
@Route(path = "/app/DegradeServiceImpl")
class DegradeServiceImpl: DegradeService {

    /**
     * 懒加载，功能类似
     */
    override fun init(context: Context?) {
        Log.e("zqs", "DegradeServiceImpl init()")
    }

    override fun onLost(context: Context?, postcard: Postcard?) {
        Log.e("zqs", "DegradeServiceImpl onLost()")
    }
}