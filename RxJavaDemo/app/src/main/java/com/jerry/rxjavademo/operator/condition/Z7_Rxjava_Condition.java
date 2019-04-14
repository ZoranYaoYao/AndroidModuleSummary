package com.jerry.rxjavademo.operator.condition;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Created by zqs on 2019/4/14.
 * https://www.jianshu.com/p/954426f90325
 *
 * 条件/布尔操作符
 *
 * 1. 作用
 * 通过设置函数，判断被观察者（Observable）发送的事件是否符合条件
 *
 * 2.类型
 * all()
 * takeWhile() / skipWhile()
 * takeUntil() / skipUntil()
 * sequenceEqual()
 * contains()
 * isEmpty()
 * amb()
 *
 *
 * 问题:  takeWhile() / skipWhile() 与 takeUntil() / skipUntil() 区别
 * takeWhile() 是当predicate 返回为true时,才会继续分发事件
 * takeUntil() 是当predicate 返回为false时,才会继续分发事件
 */
public class Z7_Rxjava_Condition {

    private static final String TAG = "Z7_Rxjava_Condition";

    /**
     * all（）
     *
     * 作用
     * 判断发送的每项数据是否都满足 设置的函数条件
     */
    public static void all() {
        Observable.just(1,2,3,4,5)
                .all(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer <= 10;
                    }
                }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                Log.d(TAG,"result is "+ aBoolean);
            }
        });
    }

    /**
     * takeWhile（）
     *
     * 作用
     * 判断发送的每项数据是否满足 设置函数条件
     */
    public static void takeWhile() {
        Observable.interval(1, TimeUnit.SECONDS)
                .takeWhile(new Predicate<Long>() {
                    @Override
                    public boolean test(Long aLong) throws Exception {
                        return aLong < 3;
                    }
                }).subscribe(longObserve);
    }

    /**
     * skipWhile（）
     *
     * 作用
     * 判断发送的每项数据是否满足 设置函数条件
     */
    public static void skipWhile() {
        Observable.interval(1,TimeUnit.SECONDS)
                .skipWhile(new Predicate<Long>() {
                    @Override
                    public boolean test(Long aLong) throws Exception {
                        return aLong < 5;
                    }
                }).subscribe(longObserve);
    }


    /**
     * 于 takeWhile() 條件關係相反
     */
    public static void takeUntil() {
        Observable.interval(1, TimeUnit.SECONDS)
                .takeUntil(new Predicate<Long>() {
                    @Override
                    public boolean test(Long aLong) throws Exception {
                        // 返回true时，就停止发送事件
                        // 当发送的数据满足>3时，就停止发送Observable的数据
                        return aLong > 3;
                    }
                }).subscribe(longObserve);

        // （原始）第1个Observable：每隔1s发送1个数据 = 从0开始，每次递增1
        Observable.interval(1, TimeUnit.SECONDS)
                // 第2个Observable：延迟5s后开始发送1个Long型数据
                .takeUntil(Observable.timer(5, TimeUnit.SECONDS))
                .subscribe(longObserve);
    }

    public static void skipUntil() {
        Observable.interval(1, TimeUnit.SECONDS)
                .skipUntil(Observable.timer(5, TimeUnit.SECONDS))
                .subscribe(longObserve);
    }

    public static void sequenceEqual() {
        Observable.sequenceEqual(Observable.just(4,5,6), Observable.just(4,5,6))
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Log.d(TAG,"2个Observable是否相同："+ aBoolean);
                        // 输出返回结果
                    }
                });
    }

    public static void contains() {
        Observable.just(1,2,3,4,5)
                .contains(4)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Log.d(TAG,"result is "+ aBoolean);
                        // 输出返回结果
                    }
                });
    }

    public static void isEmpty() {
        Observable.just(1,2,3,4,5)
                .isEmpty()
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Log.d(TAG,"result is "+ aBoolean);
                        // 输出返回结果
                    }
                });
    }

    /**
     * amb（）
     *
     * 作用
     * 当需要发送多个 Observable时，只发送 先发送数据的Observable的数据，而其余 Observable则被丢弃。
     */
    public static void amb() {
        List<ObservableSource<Integer>> list = new ArrayList<>();
        list.add(Observable.just(1,2,3).delay(1,TimeUnit.SECONDS));
        list.add(Observable.just(4,5,6));

        Observable.amb(list)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "接收到了事件 "+integer);
                    }
                });
    }

    /**
     * defaultIfEmpty（）
     *
     * 作用
     * 在不发送任何有效事件（ Next事件）、仅发送了 Complete 事件的前提下，发送一个默认值
     */
    public static void defaultIfEmpty() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                // 不发送任何有效事件
                //  e.onNext(1);
                //  e.onNext(2);

                // 仅发送Complete事件
                e.onComplete();
            }
        }).defaultIfEmpty(10)
                .subscribe(intObserve);
    }


    static Observer longObserve = new Observer<Long>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(Long aLong) {
            Log.d(TAG,"发送了事件 "+ aLong);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };

    static Observer intObserve =new Observer<Integer>() {
        @Override
        public void onSubscribe(Disposable d) {
            Log.d(TAG, "开始采用subscribe连接");
        }

        @Override
        public void onNext(Integer value) {
            Log.d(TAG, "接收到了事件"+ value  );
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG, "对Error事件作出响应");
        }

        @Override
        public void onComplete() {
            Log.d(TAG, "对Complete事件作出响应");
        }
    };


}
