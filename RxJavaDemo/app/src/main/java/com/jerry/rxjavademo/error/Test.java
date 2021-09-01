package com.jerry.rxjavademo.error;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * Created by zqs on 2019/5/14.
 */
public class Test {

    public static void main(String[] args) {
        onErrorReturn_Test();
    }

    /**
     * https://blog.csdn.net/nicolelili1/article/details/52152155
     * onErrorReturn:
     * 遇见错误时，在onNext(s)中返回的是onErrorReturn的值，随后并正常调用onCompleted返回
     */
    public static void onErrorReturn_Test() {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                for (int i = 0; i < 5; i++) {
                    if(i == 3) {
                        emitter.onError(new Throwable("ERROR"));
                    }else{
                        emitter.onNext(i + "");
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {

                    }
                }
                emitter.onComplete();
            }
        });

        Observer<String> mySubscriber =new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext................."+s);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println( "onError.....................");
            }

            @Override
            public void onComplete() {
                System.out.println("onCompleted.................");
            }
        };

        observable
                .onErrorReturn(new Function<Throwable, String>() {
                    @Override
                    public String apply(Throwable throwable) throws Exception {
                        return "this is an error observable";
                    }
                })
                .subscribe(mySubscriber);
    }
}
