package com.jerry.rxjavademo.operator.map;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by zqs on 2019/4/12.
 * https://www.jianshu.com/p/904c14d253ba
 * 实战:https://www.jianshu.com/p/5f5d61f04f96
 *
 * 变换操作符
 *      map()
 *      flatMap()
 *      concatMap()
 *      buffer()
 */
public class Z3_Rxjava_Map {
    private static final String TAG = "Z3_Rxjava_Map";

    /**
     * map（）
     *
     * 作用
     * 对被观察者发送的每1个事件参数 都通过指定的函数处理，从而变换成另外一种事件参数
     */
    public static void map() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);

            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return "变换操作数据之前是 Integer iteger = " + integer + ", 之后是字符串";
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("Consumer accept " + s);
            }
        });
    }

    /**
     * flatMap()
     *
     * 作用：将被观察者发送的事件序列进行 拆分 & 单独转换，再合并成一个新的事件序列，最后再进行发送
     * 注：新合并生成的事件序列顺序是无序的，即 与旧序列发送事件的顺序无关
     */
    public static void flatMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i=1; i<=3; i++) {
                    list.add("我是事件 " + integer + "拆分后的子事件" + i);
                }
                return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("Consumer accept " + s);
            }
        });
    }

    /**
     * concatMap()
     *
     * 作用：类似FlatMap（）操作符
     *
     * 与FlatMap（）的 区别在于：拆分 & 重新合并生成的事件序列 的顺序 = 被观察者旧序列生产的顺序!
     */
    public static void concatMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i=1; i<=3; i++) {
                    list.add("我是事件 " + integer + "拆分后的子事件" + i);
                }
                return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("Consumer accept " + s);
            }
        });
    }

    /**
     * buffer（）
     *
     * 作用
     * 定期从 被观察者（Obervable）需要发送的事件中 获取一定数量的事件 & 放到缓存区中，最终发送
     */
    public static void buffer() {
        Observable.just(1,2,3,4,5)
                .buffer(3,1)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(List<Integer> integers) throws Exception {
                        System.out.println("Consumer accept 缓存区里的事件数量 =" + integers.size());
                        for (Integer value : integers) {
                            System.out.println("Consumer accept 事件 = "+ value);
                        }
                    }
                });
    }
}
