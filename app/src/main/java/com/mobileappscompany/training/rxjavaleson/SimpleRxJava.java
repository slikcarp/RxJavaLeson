package com.mobileappscompany.training.rxjavaleson;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by User on 3/2/2017.
 */

public class SimpleRxJava {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world");

        Observable<Integer> observable = Observable.just(1,2,3,4,5,6,7,8,9,10);
        Observable<Integer> observable1 = Observable.just(11,12,13,14,15);

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted() ");
                System.out.println(Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError():" + e.toString());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext():" + integer.toString() + " Thread name:" + Thread.currentThread().getName());
            }
        };

//        observable.subscribe(observer);

//        observable.filter(new Func1<Integer, Boolean>() {
//            @Override
//            public Boolean call(Integer integer) {
//                return integer % 2 == 1;
//            }
//        }).subscribe(observer);
//
//        observable.subscribe(observer);

//        Observable.concat(observable, observable1)
//                .subscribe(observer);

        observable.filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        System.out.println(Thread.currentThread().getName());
                        return integer % 3 == 0;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.immediate())//On android AndroidSchedulers.mainThread() should be used.
                .subscribe(observer);

        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName());
    }
}
