package com.zqs.源码

/**
 * Created by zqs on 2021/9/1.
 *
 * https://www.jianshu.com/p/c5f3b8d4a746
 * https://www.jianshu.com/p/bc4c34c6a06c
 * https://www.jianshu.com/p/6021f3f61fa6
 *
 * ARouter 最大的技术亮点就是：【依赖注入】
 * 1、通过注解器和定义的注解@Router @AutoWire 编译时生成中间类的保存注解里面的参数配置
 * 2、ARouter设计了好的框架通过传递运行时activity参数，能快速获取到注解里面的配置进行!!
 *
 * TODO:
 * 1、Arouter的面试题
 *
 * =========================
 * 【模式】
 * Facade pattern 外观模式
 * _ARouter类的下划线，Arouter是对外暴露api的类
 *
 * =========================
 * 源码分析：
 * https://www.jianshu.com/p/bc4c34c6a06c
 *
 * =========================
 * 【问题】
 * 问题1：
 * Arouter里面实现的拦截器是怎么实现的？拦截器原理
 * 【】
 * 通过递归调用 + CountDownLatch进行拦截器顺序执行 和 超时处理(timeOut)!
 *
 * 问题2：
 * Arouter架构是怎么实现调用到最后的startActivity()方法的？
 * 通过注解@Route解析路径String 跟类 class.java进行映射执行，创建一个Intent(this，明确类.class.java)进行跳转
 *
 * 问题3：
 * Autowired是如何做到的？
 * 【原理】
 * 通过传递activity.getClassName()进行绑定Arouter注解器生成的FourActivity$$ARouter$$Autowired中间生成类！！
 *
 * 是我们自己调用的inject()注入方法，类似butterKnife写法
 * ARouter.getInstance().inject(this)，可以找到注解器生成的AutowireServiceImpl 调用inject()方法进行绑定！
 *
 * 问题4：
 * Route注解是做了哪些操作？
 * @Route注解主要是生成中间类文件保存注解里面的参数配置！！
 *
 * 问题5：
 * Arouter找不到Activity跳转时，是怎么做到降级处理的？？
 *【】
 * 通过在LogisticsCenter的completion()方法中，如果找不到RouteMeta注册信息，则报NoRouteFoundException，进行跳转前报错！
 */
class Test {
}