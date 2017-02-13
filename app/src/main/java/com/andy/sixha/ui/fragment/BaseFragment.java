package com.andy.sixha.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andy.sixha.mvp.presenter.Presenter;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author hehaodong
 * @Filename:com.andy.sixha.ui.activity.fragment
 * @Description:
 * @Copyright: Copyright (c) 2014 Tuandai Inc. All Rights Reserved.
 * @date 2016-11-25 17:09
 */
public abstract class BaseFragment<T> extends Fragment{
    public abstract int getLayoutResource();
    public abstract Presenter getPresenter();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int resLayoutId = getLayoutResource();
        if (resLayoutId != 0) {
            View view = inflater.inflate(resLayoutId, container, false);
            ButterKnife.bind(this, view);
            return view;
        }
        return null;
    }

    @Override
    public void onDestroy() {
        if(getPresenter() != null){
            getPresenter().destroy();
        }
        super.onDestroy();
    }
}
