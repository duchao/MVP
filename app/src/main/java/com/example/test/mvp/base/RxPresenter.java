package com.example.test.mvp.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by duchao on 2017/3/12.
 */

public class RxPresenter<T extends IBaseView> implements IBasePresenter<T> {
    protected T mView;

    protected CompositeSubscription mComPositeSubscription;

    @Override
    public void attachView(T view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
        unSubscription();
    }

    protected void addSubscription(Subscription subscription) {
        if (mComPositeSubscription == null) {
            mComPositeSubscription = new CompositeSubscription();
        }
        mComPositeSubscription.add(subscription);
    }

    protected void unSubscription() {
        if (mComPositeSubscription != null) {
            mComPositeSubscription.unsubscribe();
        }
    }
}
