package com.jerry.rxjavademo.operator.filte;


import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Created by zqs on 2019/4/13.
 * https://www.jianshu.com/p/c3a930a03855
 *
 * 作用
 * 过滤 / 筛选 被观察者（Observable）发送的事件 & 观察者 （Observer）接收的事件
 *
 * 一.根据 指定条件过滤事件
 * filter(Predicate) / ofType(Class) / skip() skipLast()
 * distinct() distinctUntilChanged()
 *
 * 二.根据 指定事件数量 过滤事件
 * take() takeLast()
 *
 * 三.根据 指定时间 过滤事件
 * throttleFirst() throttleLast() / sample()
 * throttleWithout()  debounce()
 *
 * 四.根据 位置 过滤事件
 * firstElement() / lastElement()
 * elementAt(index) / elemtentAt(index, defaultValue) / elementAtOrError()
 *
 *
 * 问题点:throttleWithTimeout debounce 与 throttleLast() sample() 区别
 * throttleLast() sample() 是严格按照自然时间点来控制事件的
 * throttleWithTimeout() debounce() 是按照每个事件发送的时间来计算开始时间的!!
 */
public class Z6_Rxjava_Filte {

    private static final String TAG = "Z6_Rxjava_Filte";

    public static void Condition_Filter() {
        //1. filter
//        Observable.just(1,2,3,4,5)
//                .filter(new Predicate<Integer>() {
//                    @Override
//                    public boolean test(Integer integer) throws Exception {
//                        // 根据test()的返回值 对被观察者发送的事件进行过滤 & 筛选
//                        // a. 返回true，则继续发送
//                        // b. 返回false，则不发送（即过滤）
//                        return integer > 3;
//                    }
//                }).subscribe(observer);
//
//        //2.ofType(Class)
//        Observable.just(1,"ZQS",3, "ZSY",5)
//                .ofType(Integer.class)
//                .subscribe(observer);

        //3. skip() / skipLast()
//        Observable.just(1,2,3,4,5)
//                .skip(1)
//                .skipLast(2)
//                .subscribe(observer);
//
//        Observable.intervalRange(0, 5, 0,1, TimeUnit.SECONDS)
//                .skip(1, TimeUnit.SECONDS)
//                .skipLast(1, TimeUnit.SECONDS)
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        Log.e(TAG, "过滤后得到的事件是："+ aLong  );
//                    }
//                });

        //4. distinct() distinctUntilChanged()
        Observable.just(1,2,3,4,1,2)
                .distinct()
                .subscribe(observer);

        Observable.just(1,2,3,4,1,2,3,3,4,4)
                .distinctUntilChanged()
                .subscribe(observer);

    }

    public static void Number_Filter() {
        Observable.just(1,2,3,4,5)
                .take(2)
                .subscribe(observer);

        Observable.just(1,2,3,4,5)
                .takeLast(3)
                .subscribe(observer);
    }

    public static void Time_Filter() {
        //1. throttleFirst()
        //在某段时间内，只发送该段时间内第1次事件
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                // 隔段事件发送时间
//                e.onNext(1);
//                Thread.sleep(500);
//
//                e.onNext(2);
//                Thread.sleep(400);
//
//                e.onNext(3);
//                Thread.sleep(300);
//
//                e.onNext(4);
//                Thread.sleep(300);
//
//                e.onNext(5);
//                Thread.sleep(300);
//
//                e.onNext(6);
//                Thread.sleep(400);
//
//                e.onNext(7);
//                Thread.sleep(300);
//                e.onNext(8);
//
//                Thread.sleep(300);
//                e.onNext(9);
//
//                Thread.sleep(300);
//                e.onComplete();
//            }
//        }).throttleFirst(1, TimeUnit.SECONDS)
//                .subscribe(observer);

        //throttleLast()
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                // 隔段事件发送时间
//                e.onNext(1);
//                Thread.sleep(500);
//
//                e.onNext(2);
//                Thread.sleep(400);
//
//                e.onNext(3);
//                Thread.sleep(300);
//
//                e.onNext(4);
//                Thread.sleep(300);
//
//                e.onNext(5);
//                Thread.sleep(300);
//
//                e.onNext(6);
//                Thread.sleep(400);
//
//                e.onNext(7);
//                Thread.sleep(300);
//                e.onNext(8);
//
//                Thread.sleep(300);
//                e.onNext(9);
//
//                Thread.sleep(300);
//                e.onComplete();
//            }
//        }).throttleLast(1, TimeUnit.SECONDS)
//                .subscribe(observer);

        /**
         * Sample（）
         *
         * 作用
         * 在某段时间内，只发送该段时间内最新（最后）1次事件
         * 与 throttleLast（） 操作符类似
         */

        //4.throttleWithTimeout debounce
        /**
         * throttleWithTimeout debounce 与 throttleLast() sample() 区别
         * throttleLast() sample() 是严格按照自然时间点来控制事件的
         * throttleWithTimeout() debounce() 是按照每个事件发送的时间来计算开始时间的!!
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                    e.onNext(1);
                    e.onNext(2);
                    e.onNext(3);
                    Thread.sleep(1500);
                    e.onNext(4);
                    Thread.sleep(800);
                    e.onNext(5);
                    e.onNext(6);
                    Thread.sleep(800);
                     e.onComplete();
            }
        })
//                .throttleWithTimeout(1, TimeUnit.SECONDS) //输出3,6, oncomplet 事件
                .debounce(1, TimeUnit.SECONDS) //输出3，6，！
//                .throttleLast(1, TimeUnit.SECONDS)  //输出3,4,6, oncomplet 事件
                .subscribe(observer);
    }

    public static void Index_Filter() {
//        Observable.just(1,2,3,4,5)
//                .firstElement()
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        Log.d(TAG,"获取到的第一个事件是： "+ integer);
//                    }
//                });
//
//        Observable.just(1,2,3,4,5)
//                .lastElement()
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        Log.d(TAG,"获取到的最后1个事件是： "+ integer);
//                    }
//                });

        Observable.just(1,2,3,4,5)
                .elementAt(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG,"获取到的事件元素是： "+ integer);
                    }
                });

        Observable.just(1,2,3,4,5)
                .elementAt(6, 10)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG,"获取到的事件元素是： "+ integer);
                    }
                });

        Observable.just(1,2,3,4,5)
                .elementAtOrError(6)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG,"获取到的事件元素是： "+ integer);
                    }
                });
    }





    static Observer<Integer> observer = new Observer<Integer>() {

        @Override
        public void onSubscribe(Disposable d) {
            System.out.println("开始采用subscribe连接");
        }

        @Override
        public void onNext(Integer value) {

            System.out.println("过滤后得到的事件是："+ value);
        }

        @Override
        public void onError(Throwable e) {

            System.out.println("对Error事件作出响应");
        }

        @Override
        public void onComplete() {
            System.out.println("对Complete事件作出响应");
        }
    };
}
