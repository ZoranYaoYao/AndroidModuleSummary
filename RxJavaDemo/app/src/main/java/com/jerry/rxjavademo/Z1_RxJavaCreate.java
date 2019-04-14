package com.jerry.rxjavademo;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by zqs on 2019/4/12.
 * https://www.jianshu.com/p/a406b94f3188
 */
public class Z1_RxJavaCreate {

    public static void create() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("zqs", "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.e("zqs", "onNext integer = " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("zqs", "onError");
            }

            @Override
            public void onComplete() {
                Log.e("zqs", "onComplete");
            }
        };

        observable.subscribe(observer);
    }

    public static void create2() {
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
                Log.e("zqs2", "onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                Log.e("zqs2", "onNext integer = " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("zqs2", "onError");
            }

            @Override
            public void onComplete() {
                Log.e("zqs2", "onComplete");
            }
        });
    }

}
