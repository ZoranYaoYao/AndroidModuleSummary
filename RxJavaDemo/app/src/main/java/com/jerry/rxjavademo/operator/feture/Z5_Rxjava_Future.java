package com.jerry.rxjavademo.operator.feture;


import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zqs on 2019/4/13.
 * https://www.jianshu.com/p/b0c3669affdb
 * <p>
 * 辅助被观察者（Observable） 在发送事件时实现一些功能性需求
 * <p>
 * subcribe()
 * subcribeOn() / observeOn()
 * delay()
 * do() 系列回调
 * <p>
 * 错误处理机制:
 * onErrorReturn()
 * onErrorResumeNext() / onErrorResumeException
 * retry() / retry(long times) / retry(predicate) / retry(BiPredicate) / retryUtil(BooleanSupplier) / retryWhen()
 * <p>
 * repeat() /repeatWhen()
 */
public class Z5_Rxjava_Future {
    private static final String TAG = "Z5_Rxjava_Future";

    /**
     * subscribe（）
     * <p>
     * 作用
     * 订阅，即连接观察者 & 被观察者
     */
    public static void subscribe() {
        Observable.just(1, 2).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, "subscribe");
            }
        });
    }

    /**
     * Schedulers.immediate()
     * AndroidSchedulers.mainThread()
     * Schedulers.newThread()
     * Schedulers.io()
     * Schedulers.computation()
     */
    public static void subcribeOn_obseverOn() {
        //模拟被观察者在网络请求, 观察者在主线程
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.e(TAG, "subscribe 当前线程:" + Thread.currentThread().getName());
                emitter.onNext("网络返回的json");
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.e(TAG, "accept 当前线程:" + Thread.currentThread().getName());
                    }
                });

    }

    public static void delay() {
        Observable.just(1, 2).delay(3, TimeUnit.SECONDS)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "integer = " + integer);
                    }
                });
    }

    /**
     * 04-13 16:37:34.900 27787-27787/com.jerry.rxjavademo E/Z5_Rxjava_Future: doOnSubscribe:
     * 04-13 16:37:34.901 27787-27787/com.jerry.rxjavademo E/Z5_Rxjava_Future: doOnEach:1
     * doOnNext: 1
     * 04-13 16:37:34.901 27787-27787/com.jerry.rxjavademo D/Z5_Rxjava_Future: 接收到了事件1
     * 04-13 16:37:34.901 27787-27787/com.jerry.rxjavademo E/Z5_Rxjava_Future: doAfterNext: 1
     * doOnEach:2
     * doOnNext: 2
     * 04-13 16:37:34.901 27787-27787/com.jerry.rxjavademo D/Z5_Rxjava_Future: 接收到了事件2
     * 04-13 16:37:34.902 27787-27787/com.jerry.rxjavademo E/Z5_Rxjava_Future: doAfterNext: 2
     * doOnEach:null
     * 04-13 16:37:34.902 27787-27787/com.jerry.rxjavademo D/Z5_Rxjava_Future: doOnError: 发生错误了
     * 对Error事件作出响应
     * 04-13 16:37:34.902 27787-27787/com.jerry.rxjavademo E/Z5_Rxjava_Future: doAfterTerminate:
     * doFinally:
     */
    public static void doList() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new Throwable("发生错误了"));
                emitter.onComplete();
            }
        })
                //1.  观察者订阅时调用
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.e(TAG, "doOnSubscribe: ");
                    }
                })
                // 2. 当Observable每发送1次数据事件就会调用1次
                .doOnEach(new Consumer<Notification<Integer>>() {
                    @Override
                    public void accept(Notification<Integer> integerNotification) throws Exception {
                        Log.e(TAG, "doOnEach:" + integerNotification.getValue());
                    }
                })
                // 3. 执行Next事件前调用
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "doOnNext: " + integer);
                    }
                })
                // 4. 执行Next事件后调用
                .doAfterNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "doAfterNext: " + integer);
                    }
                })
                // 6. Observable正常发送事件完毕后调用
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG, "doOnComplete: ");
                    }
                })
                // 5. Observable发送错误事件时调用
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "doOnError: " + throwable.getMessage());
                    }
                })
                // 7. Observable发送事件完毕后调用，无论正常发送完毕 / 异常终止
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG, "doAfterTerminate: ");
                    }
                })
                // 8. 最后执行
                .doFinally(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG, "doFinally: ");
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "接收到了事件" + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });
    }

    /**
     * onErrorReturn（）
     * <p>
     * 作用
     * 遇到错误时，发送1个特殊事件 & 正常终止
     */
    public static void onErrorReturn() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new Throwable("发生错误"));
            }
        }).onErrorReturn(new Function<Throwable, Integer>() {
            @Override
            public Integer apply(Throwable throwable) throws Exception {
                // 捕捉错误异常
                Log.e(TAG, "在onErrorReturn处理了错误: " + throwable.toString());
                return 666;
                // 发生错误事件后，发送一个"666"事件，最终正常结束
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "接收到了事件" + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }
        });
    }

    /**
     * onErrorResumeNext（）
     * <p>
     * 作用
     * 遇到错误时，发送1个新的Observable
     */
    public static void onErrorResumeNext() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new Throwable("发生了错误"));
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> apply(Throwable throwable) throws Exception {

                // 1. 捕捉错误异常
                Log.e(TAG, "在onErrorReturn处理了错误: " + throwable.toString());

                // 2. 发生错误事件后，发送一个新的被观察者 & 发送事件序列
                return Observable.just(11, 12);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "接收到了事件" + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }
        });
    }

    /**
     * onExceptionResumeNext（）
     * <p>
     * 作用
     * 遇到错误时，发送1个新的Observable
     */
    public static void onExceptionResumeNext() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new Exception("exception 错误"));
            }
        }).onExceptionResumeNext(new Observable<Integer>() {
            @Override
            protected void subscribeActual(Observer<? super Integer> observer) {
                observer.onNext(11);
                observer.onNext(12);
                observer.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "接收到了事件" + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "对Error事件作出响应");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "对Complete事件作出响应");
            }
        });
    }

    public static void retry() {
        //1 retry()
        // 作用：出现错误时，让被观察者重新发送数据
        // 注：若一直错误，则一直重新发送
        //        Observable.create(new ObservableOnSubscribe<Integer>() {
        //            @Override
        //            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
        //                emitter.onNext(1);
        //                emitter.onNext(2);
        //                emitter.onError(new Throwable("Throwable error"));
        //                emitter.onNext(3);
        //            }
        //        })
        //                .retry()
        //                .subscribe(new Observer<Integer>() {
        //                    @Override
        //                    public void onSubscribe(Disposable d) {
        //
        //                    }
        //
        //                    @Override
        //                    public void onNext(Integer integer) {
        //                        Log.d(TAG, "接收到了事件"+ integer  );
        //                    }
        //
        //                    @Override
        //                    public void onError(Throwable e) {
        //                        Log.d(TAG, "对Error事件作出响应");
        //                    }
        //
        //                    @Override
        //                    public void onComplete() {
        //                        Log.d(TAG, "对Complete事件作出响应");
        //                    }
        //                });

        //2 retry(long times)
        // retry(3) 代表原来一次+重试3次 = 4次, 最后会响应onerror事件
        //        Observable.create(new ObservableOnSubscribe<Integer>() {
        //            @Override
        //            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
        //                emitter.onNext(1);
        //                emitter.onNext(2);
        //                emitter.onError(new Throwable("Throwable error"));
        //                emitter.onNext(3);
        //            }
        //        })
        //                .retry(3)
        //                .subscribe(new Observer<Integer>() {
        //                    @Override
        //                    public void onSubscribe(Disposable d) {
        //
        //                    }
        //
        //                    @Override
        //                    public void onNext(Integer integer) {
        //                        Log.d(TAG, "接收到了事件"+ integer  );
        //                    }
        //
        //                    @Override
        //                    public void onError(Throwable e) {
        //                        Log.d(TAG, "对Error事件作出响应");
        //                    }
        //
        //                    @Override
        //                    public void onComplete() {
        //                        Log.d(TAG, "对Complete事件作出响应");
        //                    }
        //                });

        //        Observable.create(new ObservableOnSubscribe<Integer>() {
        //            @Override
        //            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
        //                emitter.onNext(1);
        //                emitter.onNext(2);
        //                emitter.onError(new Throwable("Throwable error"));
        //                emitter.onNext(3);
        //            }
        //        })
        //                // 拦截错误后，判断是否需要重新发送请求
        //                .retry(new Predicate<Throwable>() {
        //                    @Override
        //                    public boolean test(Throwable throwable) throws Exception {
        //
        //                        //返回false = 不重新重新发送数据 & 调用观察者的onError结束
        ////                        return false;
        //                        ////返回true = 重新发送请求（若持续遇到错误，就持续重新发送）
        //                        return true;
        //                    }
        //                })
        //                .subscribe(new Observer<Integer>() {
        //                    @Override
        //                    public void onSubscribe(Disposable d) {
        //
        //                    }
        //
        //                    @Override
        //                    public void onNext(Integer integer) {
        //                        Log.d(TAG, "接收到了事件"+ integer  );
        //                    }
        //
        //                    @Override
        //                    public void onError(Throwable e) {
        //                        Log.d(TAG, "对Error事件作出响应");
        //                    }
        //
        //                    @Override
        //                    public void onComplete() {
        //                        Log.d(TAG, "对Complete事件作出响应");
        //                    }
        //                });

        // 4. retry（new BiPredicate<Integer, Throwable>
        // 作用：出现错误后，判断是否需要重新发送数据（若需要重新发送 & 持续遇到错误，则持续重试
        // 参数 =  判断逻辑（传入当前重试次数 & 异常错误信息）
        //                Observable.create(new ObservableOnSubscribe<Integer>() {
        //                    @Override
        //                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
        //                        emitter.onNext(1);
        //                        emitter.onNext(2);
        //                        emitter.onError(new Throwable("Throwable error"));
        //                        emitter.onNext(3);
        //                    }
        //                })
        //                        .retry(new BiPredicate<Integer, Throwable>() {
        //                            @Override
        //                            public boolean test(Integer integer, Throwable throwable) throws Exception {
        //                                // 捕获异常
        //                                Log.e(TAG, "异常错误 =  "+throwable.toString());
        //
        //                                // 获取当前重试次数
        //                                Log.e(TAG, "当前重试次数 =  "+integer);
        //
        //                                //返回false = 不重新重新发送数据 & 调用观察者的onError结束
        ////                                return false;
        //                                //返回true = 重新发送请求（若持续遇到错误，就持续重新发送）
        //                                return true;
        //                            }
        //                        })
        //                        .subscribe(new Observer<Integer>() {
        //                            @Override
        //                            public void onSubscribe(Disposable d) {
        //
        //                            }
        //
        //                            @Override
        //                            public void onNext(Integer integer) {
        //                                Log.d(TAG, "接收到了事件"+ integer  );
        //                            }
        //
        //                            @Override
        //                            public void onError(Throwable e) {
        //                                Log.d(TAG, "对Error事件作出响应");
        //                            }
        //
        //                            @Override
        //                            public void onComplete() {
        //                                Log.d(TAG, "对Complete事件作出响应");
        //                            }
        //                        });

        //5 retryUtil() 与 retry(predicate) 功能类似,但返回值相同,功能相反!
        //                        Observable.create(new ObservableOnSubscribe<Integer>() {
        //                            @Override
        //                            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
        //                                emitter.onNext(1);
        //                                emitter.onNext(2);
        //                                emitter.onError(new Throwable("Throwable error"));
        //                                emitter.onNext(3);
        //                            }
        //                        })
        //                                .retryUntil(new BooleanSupplier() {
        //                                    @Override
        //                                    public boolean getAsBoolean() throws Exception {
        //                                        // return false 会一直尝试
        ////                                        return false;
        //                                        // return true; 不会重试发送
        //                                        return true;
        //                                    }
        //                                })
        //                                .subscribe(new Observer<Integer>() {
        //                                    @Override
        //                                    public void onSubscribe(Disposable d) {
        //
        //                                    }
        //
        //                                    @Override
        //                                    public void onNext(Integer integer) {
        //                                        Log.d(TAG, "接收到了事件"+ integer  );
        //                                    }
        //
        //                                    @Override
        //                                    public void onError(Throwable e) {
        //                                        Log.d(TAG, "对Error事件作出响应");
        //                                    }
        //
        //                                    @Override
        //                                    public void onComplete() {
        //                                        Log.d(TAG, "对Complete事件作出响应");
        //                                    }
        //                                });

        //6. retryWhen
        /**
         * retryWhen（）
         * 作用
         * 遇到错误时，将发生的错误传递给一个新的被观察者（Observable），并决定是否需要重新订阅原始被观察者（Observable）& 发送事件
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new Throwable("Throwable error"));
                emitter.onNext(3);
            }
        })
                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(Throwable throwable) throws Exception {
                                // 1. 若返回的Observable发送的事件 = Error事件，则原始的Observable不重新发送事件
                                // 该异常错误信息可在观察者中的onError（）中获得
                                return Observable.error(new Throwable("retryWhen 终止啦"));

                                // 2. 若返回的Observable发送的事件 = Next事件，则原始的Observable重新发送事件（若持续遇到错误，则持续重试）
                                //                                                         return Observable.just(33333);
                            }
                        });
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "接收到了事件" + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }
                });
    }

    /**
     * repeat（）
     * 作用
     * 无条件地、重复发送 被观察者事件
     */
    public static void repeat() {
        Observable.just(1, 2, 3)

                .repeat(3)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }

                });
    }

    /**
     * repeatWhen（）
     * 作用
     * 有条件地、重复发送 被观察者事件
     */
    public static void repeatWhen() {
        Observable.just(1, 2, 4).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
            @Override
            // 在Function函数中，必须对输入的 Observable<Object>进行处理，这里我们使用的是flatMap操作符接收上游的数据
            public ObservableSource<?> apply(@NonNull Observable<Object> objectObservable) throws Exception {
                // 将原始 Observable 停止发送事件的标识（Complete（） /  Error（））转换成1个 Object 类型数据传递给1个新被观察者（Observable）
                // 以此决定是否重新订阅 & 发送原来的 Observable
                // 此处有2种情况：
                // 1. 若新被观察者（Observable）返回1个Complete（） /  Error（）事件，则不重新订阅 & 发送原来的 Observable
                // 2. 若新被观察者（Observable）返回其余事件，则重新订阅 & 发送原来的 Observable
                return objectObservable.flatMap(new Function<Object, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull Object throwable) throws Exception {

                        // 情况1：若新被观察者（Observable）返回1个Complete（） /  Error（）事件，则不重新订阅 & 发送原来的 Observable
                        //                        return Observable.empty();
                        // Observable.empty() = 发送Complete事件，但不会回调观察者的onComplete（）

                        //                         return Observable.error(new Throwable("不再重新订阅事件"));
                        // 返回Error事件 = 回调onError（）事件，并接收传过去的错误信息。

                        // 情况2：若新被观察者（Observable）返回其余事件，则重新订阅 & 发送原来的 Observable
                        return Observable.just(1);
                        // 仅仅是作为1个触发重新订阅被观察者的通知，发送的是什么数据并不重要，只要不是Complete（） /  Error（）事件
                    }
                });

            }
        })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "开始采用subscribe连接");
                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.d(TAG, "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "对Error事件作出响应：" + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "对Complete事件作出响应");
                    }

                });
    }
}
