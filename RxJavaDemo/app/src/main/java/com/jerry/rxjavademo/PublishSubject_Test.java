package com.jerry.rxjavademo;

import android.util.Log;

import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by zqs on 2019/4/25.
 */
public class PublishSubject_Test {

    public static void main(String[] args) {
        PublishSubject_Test.test1();
    }

    /**
     * PublishSubject 延迟触发， 只有调用onNext()之后才会触发
     */
    public static void test1() {
        PublishSubject<String> publishSubject = PublishSubject.create();
        publishSubject.ofType(String.class).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
//                Log.e("zqs", "publishSubject accept");
                System.out.println("publishSubject accept = " + o);
            }
        });

        publishSubject.onNext("Hello");
        publishSubject.onNext("Hello22");

    }
}
