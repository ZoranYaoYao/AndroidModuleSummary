package com.jerry.rxjavademo.被观察者转换;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by zqs on 2019/4/24.
 */
public class Exchange {

    /**
     * Observable 转换成 Single
     * Single只能接受一个next事件或者一个异常
     */
    public static void Observable2Single() {
        Observable.just(3,2,1).firstOrError().subscribe(next, error);

        Observable.error(new Throwable("zsy")).firstOrError().subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.e("zqs", "Object = " + o);
            }
        },error);
    }

    /**
     * Observable 转换成 Completable
     * Completable作用是最终流入到onComplete() onError()事件中
     */
    public static void Observable2Completable() {
        /**会把next事件自动转换成complete事件！！*/
        Observable.just(3,2,1).error(new Throwable("Observable2Completable")).ignoreElements().subscribe(new Action() {
            @Override
            public void run() throws Exception {
                Log.e("zqs", "完成了任务");
            }
        }, error);

    }

    static Consumer next = new Consumer<Integer>() {
        @Override
        public void accept(Integer integer) throws Exception {
            Log.e("zqs", "onSubscribe integer =  " +integer);
        }
    };

    static Consumer error = new Consumer<Throwable>() {
        @Override
        public void accept(Throwable throwable) throws Exception {
            Log.e("zqs", "Throwable = " + throwable);
        }
    };

}
