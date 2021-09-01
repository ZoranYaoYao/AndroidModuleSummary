package com.jerry.rxjavademo.operator.create;

import android.util.Log;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.processors.PublishProcessor;

/**
 * Created by zqs on 2019/4/12.
 * https://www.jianshu.com/p/e19f8ed863b1
 *
 * 常见创建操作符分类
 * 1. 基本创建符
 *      create()
 * 2. 快速创建符
 *      just()
 *      fromArray()
 *      fromIterable()
 *      other -> empty(), error(), never()
 * 3. 延迟创建
 *      defer()
 *      timer()
 *      interval()
 *      intervalRange()
 *      range()
 *      rangeLong()
 *
 */
public class Z2_Rxjava_operator {

    private static final String TAG = "Z2_Rxjava_operator";

    /**
     * 1. 基本创建符 create()
     */
    public static void create() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG, "onNext integer = " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
            }
        });
    }

    /**
     * 2. 快速创建符
     *      just()
     */
    public static void just() {
        Observable.just(1,2,3).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG, "onNext integer = " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
            }
        });
    }

    /**
     * 2. 快速创建符
     *      fromArray()
     */
    public static void fromArray() {
        Integer[] array = {1,2,3};
        Observable.fromArray(array).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG, "onNext integer = " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
            }
        });

    }


    /**
     * 2. 快速创建符
     *      fromIterable()
     */
    public static void fromIterable() {
        List<Integer> list = new ArrayList<>();
        list.add(1);list.add(2);list.add(3);
        Observable.fromIterable(list).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG, "onNext integer = " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
            }
        });
    }

    /**
     * 2. 快速创建符
     *      empty() : 不会发送onNext()事件， 会发送onComplete()事件
     *      error()
     *      never()
     */
    public static void other() {
        Observable observable = Observable.empty();

        Observable observable1 = Observable.error(new IllegalArgumentException("illegal argument"));

        Observable observable2 = Observable.never();
    }

    //==================================================================================================================

    /**
     * 3. 延迟创建
     *
     * 好处
     * 动态创建被观察者对象（Observable） & 获取最新的Observable对象数据
     *
     * defer
     */
    static int i = 0;
    public static void defer() {
        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> call() throws Exception {
                return Observable.just(i);
            }
        });
        i = 15;

        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG, "onNext integer = " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
            }
        });
    }

    /**
     * 3.延迟创建
     *
     *  timer()
     *
     * 作用
     * 快速创建1个被观察者对象（Observable）
     * 发送事件的特点：延迟指定时间后，发送1个数值0（Long类型）
     */
    public static void timer() {
        Observable.timer(2, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Long integer) {
                Log.e(TAG, "onNext long = " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
            }
        });
    }

    /**
     * 3.延迟创建
     *
     * interval()
     *
     * 作用
     * 快速创建1个被观察者对象（Observable）
     * 发送事件的特点：每隔指定时间 就发送 事件
     */
    public static void interval() {
        Observable.interval(3,1,TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Long integer) {
                Log.e(TAG, "onNext long = " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
            }
        });
    }

    /**
     * 3.延迟创建
     *
     * intervalRange（）
     *
     * 作用
     * 快速创建1个被观察者对象（Observable）
     * 发送事件的特点：每隔指定时间 就发送 事件，可指定发送的数据的数量
     */
    public static void intervalRange() {
        Observable.intervalRange(3, 10, 2, 1, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Long integer) {
                Log.e(TAG, "onNext long = " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
            }
        });
    }

    /**
     * 3.延迟创建
     *      range()
     *
     * 作用
     * a. 发送的事件序列 = 从0开始、无限递增1的的整数序列
     * b. 作用类似于intervalRange（），但区别在于：无延迟发送事件
     */
    public static void range() {
        Observable.range(3, 10).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG, "onNext integer = " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
            }
        });
    }


    /**
     * 3.延迟创建
     *
     * rangeLong（）
     *
     * 作用：类似于range（），区别在于该方法支持数据类型 = Long
     */
    public static void rangeLong() {
        Observable.rangeLong(3,10).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e(TAG, "onSubscribe");
            }

            @Override
            public void onNext(Long integer) {
                Log.e(TAG, "onNext long = " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
            }
        });
    }
}
