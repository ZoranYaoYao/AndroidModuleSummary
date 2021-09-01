package com.jerry.rxjavademo.other;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zqs on 2019/5/14.
 */
public class switchMap_Test {

    public static void main(String[] args) {
        m();
    }

    /**
     * https://blog.csdn.net/l_o_s/article/details/79445188
     *
     * switchMap：
     * 异步线程中发射时，在map时，会取消已发射的任务
     *
     * 实验证明，有可能异步任务都不执行！！
     */
    public static void m() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);

//                Observable.fromIterable(list)
//                        .switchMap(new Function<Integer, ObservableSource<String>>() {
//                            @Override
//                            public ObservableSource<String> apply(Integer integer) throws Exception {
//                                return Observable.just("intger" + integer);
//                            }
//                        })
//                        .subscribe(new Consumer<String>() {
//                            @Override
//                            public void accept(String s) throws Exception {
//                                System.out.println("accept + " + s + ", thread:" + Thread.currentThread());
//                            }
//                        });

        System.out.println(111);
        Observable.fromIterable(list)
                .switchMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        return Observable.just("integer " + integer).subscribeOn(Schedulers.newThread());
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("accept + " + s + ", thread:" + Thread.currentThread());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println("error");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        System.out.println("done");
                    }
                });
    }
}
