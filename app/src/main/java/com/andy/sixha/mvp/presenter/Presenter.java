package com.andy.sixha.mvp.presenter;

import android.app.Activity;


import com.andy.sixha.mvp.view.BaseView;

import rx.subscriptions.CompositeSubscription;

public abstract class Presenter<T extends BaseView> {
    T view;

    interface ErrorCode {
        int ERROR_REQUEST_DATA = 400;
        int ERROR_OBSERVER_DATA = 404;
    }

    protected CompositeSubscription compositeSubscription = new CompositeSubscription();

    public Presenter(T view) {
        this.view = view;
    }

    public Activity getActivity() {
        return view.getActivity();
    }

    public void resume() {
    }

    public void pause() {

    }

    public void destroy() {
        compositeSubscription.unsubscribe();
        view = null;
    }
}