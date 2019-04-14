package com.jerry.rxjavademo.operator.concat;


import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * Created by zqs on 2019/4/12.
 * https://www.jianshu.com/p/c2a7c03da16d
 *
 * 组合/合并操作符
 *  组合的是Observable对象!
 *
 *  concat() / concatArray()
 *  merge() / mergeArray()
 *  concatDelayError() / mergeDelayError()
 *  zip()
 *  combineLatest() / combineLatestDelayError()
 *  reduce()
 *  collect()
 *  startWith() / startWithArray()
 *  count()
 *
 */
public class Z4_Rxjava_Concat {
    private static final String TAG = "Z4_Rxjava_Concat";

    /**
     * concat（） / concatArray（）
     *
     * 作用
     * 组合多个被观察者一起发送数据，合并后 按发送顺序串行执行
     * 二者区别：组合被观察者的数量，即concat（）组合被观察者数量≤4个，而concatArray（）则可＞4个
     */
    public static void concat_concatArray() {
        Observable.concat(Observable.just(1,2,3),Observable.just(4,5,6), Observable.<Integer>empty(),  Observable.<Integer>empty())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "Integer = " + integer);
                    }
                });

        Observable.concatArray(Observable.just(1,2,3),Observable.just(4,5,6), Observable.just(7,8,9), Observable.just(10,11,12), Observable.just(13,14,15)).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, "Integer = " + integer);
            }
        });
    }

    /**
     * merge（） / mergeArray（）
     *
     * 作用
     *  组合多个被观察者一起发送数据，合并后 按时间线并行执行
     * 事件是无序的!
     */
    public static void merge_mergeArray() {
        Observable.merge(Observable.intervalRange(0,3,2,1, TimeUnit.SECONDS), Observable.intervalRange(3,3,1,1,TimeUnit.SECONDS))
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.e(TAG, "接受到事件 : " + aLong);
                    }
                });
    }

    /**
     * 若希望onError事件推迟到其他被观察者发送事件结束后才触发
     * 即需要使用对应的concatDelayError() 或 mergeDelayError()操作符
     */
    public static void concatDelayError_mergeDelayError() {
        Observable.concatArray(Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onError(new NullPointerException());
                emitter.onComplete();
            }
        }), Observable.just(5,6,7))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "接收到了事件"+ integer);
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

        Observable.concatArrayDelayError(Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onError(new NullPointerException());
                emitter.onComplete();
            }
        }), Observable.just(5,6,7))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "接收到了事件"+ integer);
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
     * 将2个观察者事件合并成一个事件
     */
    public static void zip() {
        Observable observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
                              @Override
                              public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                                  emitter.onNext(1);
                                  emitter.onNext(2);
                                  emitter.onNext(3);

//                                  emitter.onComplete();
                              }
                          });
        Observable observable2 =  Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("A");
                emitter.onNext("B");
                emitter.onNext("C");
                emitter.onNext("D");

//                emitter.onComplete();
            }
        });
        Observable.zip(observable1, observable2, new BiFunction<Integer,String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String value) {
                Log.d(TAG, "最终接收到的事件 =  " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        });
    }


    /**
     * combineLatest（）
     *
     * 作用
     * 当两个Observables中的任何一个发送了数据后，
     * 将先发送了数据的Observables 的最新（最后）一个数据 与 另外一个Observable发送的每个数据结合，最终基于该函数的结果发送数据
     */
    public static void combineLatest_combineLatestDelayError() {
       Observable.combineLatest(Observable.just(1L, 2L, 3L), Observable.intervalRange(0, 3, 1, 1, TimeUnit.SECONDS),
               new BiFunction<Long, Long, Long>() {
                   @Override
                   public Long apply(Long o1, Long o2) throws Exception {
                       Log.e(TAG, "合并的数据是： "+ o1 + " "+ o2);
                       return o1 + o2;
                   }
               })
               .subscribe(new Consumer<Long>() {
                   @Override
                   public void accept(Long aLong) throws Exception {
                       Log.e(TAG, "合并的结果是： "+ aLong);
                   }
               });
               
    }

    /**
     * reduce（）
     * 作用
     * 把被观察者需要发送的事件聚合成1个事件 & 发送
     * 聚合的逻辑根据需求撰写，但本质都是前2个数据聚合，然后与后1个数据继续进行聚合，依次类推
     */
    public static void reduce() {
        Observable.just(1,2,3,4)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        Log.e(TAG, "本次计算的数据是： "+integer +" 乘 "+ integer2);
                        return integer * integer2;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e(TAG, integer + "");
            }
        });
    }

    /**
     * collect（）
     *
     * 作用
     * 将被观察者Observable发送的数据事件收集到一个数据结构里
     */
    public static void collect() {
        Observable.just(1,2,3,4,5,6)
                .collect(new Callable<ArrayList<Integer>>() {
                    @Override
                    public ArrayList<Integer> call() throws Exception {
                        return new ArrayList<>();

                    }
                }, new BiConsumer<ArrayList<Integer>, Integer>() {
                    @Override
                    public void accept(ArrayList<Integer> list, Integer integer) throws Exception {
                        list.add(integer);
                    }
                }).subscribe(new Consumer<ArrayList<Integer>>() {
            @Override
            public void accept(ArrayList<Integer> integers) throws Exception {
                Log.e(TAG, "本次发送的数据是： "+ integers);
            }
        });
    }

    /**
     * startWith（） / startWithArray（）
     *
     * 作用
     * 在一个被观察者发送事件前，追加发送一些数据 / 一个新的被观察者
     * 具体使用
     */
    public static void startWith_startWithArray() {
        Observable.just(4,5,6)
                .startWith(0)
                .startWithArray(1,2,3)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.e(TAG, "接受数据" + integer);
                    }
                });
    }

    /**
     * count（）
     *
     * 作用
     * 统计被观察者发送事件的数量
     */
    public static void count() {
        Observable.just(1,2,3,4,5)
                .count()
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.e(TAG, "发送的事件数量 =  "+aLong);
                    }
                });
    }
}
