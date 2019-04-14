package com.jerry.rxjavademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jerry.rxjavademo.operator.concat.Z4_Rxjava_Concat;
import com.jerry.rxjavademo.operator.condition.Z7_Rxjava_Condition;
import com.jerry.rxjavademo.operator.create.Z2_Rxjava_operator;
import com.jerry.rxjavademo.operator.feture.Z5_Rxjava_Future;
import com.jerry.rxjavademo.operator.filte.Z6_Rxjava_Filte;
import com.jerry.rxjavademo.operator.map.Z3_Rxjava_Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onError(new Throwable("error !!!!!!!1"));
            }
        })
                .subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e("a", "a");
            }
        });


        //1. 常见Rxjava的相关类
//        Z1_RxJavaCreate.create();
//        Z1_RxJavaCreate.create2();

//        Z2();

//        Z3();

//        Z4();

//        Z5();

//        Z6();

//        Z7();
    }

    /**
     * 2. 创建操作符
     */
    public void Z2() {
//        Z2_Rxjava_operator.create();
//        Z2_Rxjava_operator.just();
//        Z2_Rxjava_operator.fromIterable();
//        Z2_Rxjava_operator.defer();
//        Z2_Rxjava_operator.timer();
//        Z2_Rxjava_operator.interval();
//        Z2_Rxjava_operator.intervalRange();
//        Z2_Rxjava_operator.range();
        Z2_Rxjava_operator.rangeLong();
    }

    public void Z3() {
//        Z3_Rxjava_Map.map();
//        Z3_Rxjava_Map.flatMap();
//        Z3_Rxjava_Map.concatMap();
        Z3_Rxjava_Map.buffer();
    }

    public void Z4() {
//        Z4_Rxjava_Concat.concat_concatArray();
//        Z4_Rxjava_Concat.merge_mergeArray();
//        Z4_Rxjava_Concat.concatDelayError_mergeDelayError();
//        Z4_Rxjava_Concat.zip();
//        Z4_Rxjava_Concat.combineLatest_combineLatestDelayError();
//        Z4_Rxjava_Concat.reduce();
//        Z4_Rxjava_Concat.collect();
//        Z4_Rxjava_Concat.startWith_startWithArray();
        Z4_Rxjava_Concat.count();
    }

    private void Z5() {
//        Z5_Rxjava_Future.subcribeOn_obseverOn();
//        Z5_Rxjava_Future.delay();
//        Z5_Rxjava_Future.doList();
//        Z5_Rxjava_Future.onErrorReturn();
//        Z5_Rxjava_Future.onErrorResumeNext();
//        Z5_Rxjava_Future.onExceptionResumeNext();
        Z5_Rxjava_Future.retry();
//        Z5_Rxjava_Future.repeat();
//        Z5_Rxjava_Future.repeatWhen();
    }

    private void Z6() {
//       Z6_Rxjava_Filte.Condition_Filter();
//       Z6_Rxjava_Filte.Number_Filter();
//       Z6_Rxjava_Filte.Time_Filter();
       Z6_Rxjava_Filte.Index_Filter();
    }

    private void Z7() {
//        Z7_Rxjava_Condition.all();
//        Z7_Rxjava_Condition.takeWhile();
//        Z7_Rxjava_Condition.skipWhile();
//        Z7_Rxjava_Condition.takeUntil();
//        Z7_Rxjava_Condition.sequenceEqual();
//        Z7_Rxjava_Condition.contains();
//        Z7_Rxjava_Condition.isEmpty();
//        Z7_Rxjava_Condition.amb();
        Z7_Rxjava_Condition.defaultIfEmpty();
    }

}
