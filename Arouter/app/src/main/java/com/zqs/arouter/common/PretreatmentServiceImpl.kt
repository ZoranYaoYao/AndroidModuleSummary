package com.zqs.arouter.common

import android.content.Context
import android.util.Log
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.PretreatmentService

/**
 * Created by zqs on 2021/7/19.
 * 预处理服务
 *
 * 在处理跳转之前的逻辑代码切面功能类
 */
// 实现 PretreatmentService 接口，并加上一个Path内容任意的注解即可
@Route(path = "/app/PretreatmentServiceImpl")
class PretreatmentServiceImpl: PretreatmentService {
    override fun init(context: Context?) {
        Log.e("zqs", "PretreatmentServiceImpl init()")

    }

    override fun onPretreatment(context: Context?, postcard: Postcard?): Boolean {
        Log.e("zqs", "PretreatmentServiceImpl onPretreatment()")

        // 跳转前预处理，如果需要自行处理跳转，该方法返回 false 即可
        return true
    }
}