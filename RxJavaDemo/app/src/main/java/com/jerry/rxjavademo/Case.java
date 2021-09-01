package com.jerry.rxjavademo;


import android.util.Log;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by zqs on 2019/5/10.
 */
public class Case {

    public static void main(String[] args) {
//        Observable2Completable();
//        test();
        Single<Case> s = Single.error(new RuntimeException("非法车辆编号"));
        Type[] type = ((ParameterizedType)s.getClass().getGenericSuperclass()).getActualTypeArguments();
        for (Type item : type) {
            System.out.println(item);
        }

    }

    /**
     * next事件 转换成 Throwable事件 & doOnNext()不会再执行
     * 源码知道！
     */
    public static void test() {
        Observable.just(1).map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) throws Exception {
                /**
                 * next事件转换时可以 直接抛异常事件
                 */
                throw new RuntimeException("next事件 转变成异常");
//                return 2;
            }
        }).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer o) throws Exception {
                System.out.println("doOnNext");
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("accept OnNext");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("accept Throwable");
            }
        });
    }

    /**
     * Observable 转换成 Completable
     * 1. just(3,2,1) 生产的是3，2，1，oncomplet()事件，所以一定会执行doOnNext()方法 和subscribe()中的conplete的Action
     * 2. ObservableOnSubscribe 需要显示调用emitter.onComplete();才会生产一个完成事件！
     */
    public static void Observable2Completable() {
        /**会把next事件自动转换成complete事件！！*/
        Observable.just(2,3).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("执行了 doOnNext，integer = " + integer);
            }
        }).ignoreElements().subscribe(new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("完成了任务");
            }
        }, error);

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                System.out.println("ObservableOnSubscribe subscribe");
                emitter.onNext(1);
                emitter.onComplete(); //注释之后，不会调用"完成了任务"
            }
        }).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("执行了 doOnNext，integer = " + integer);
            }
        }).ignoreElements().subscribe(new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("完成了任务");
            }
        }, error);

    }

    static Consumer error = new Consumer<Throwable>() {
        @Override
        public void accept(Throwable throwable) throws Exception {
            System.out.println("Throwable = " + throwable);
        }
    };
}
