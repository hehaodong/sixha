package com.andy.sixha.mvp.presenter;

import android.util.Log;

import com.andy.sixha.api.MarkSixApi;
import com.andy.sixha.bean.MarkSix;
import com.andy.sixha.mvp.view.GetLatestView;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author hehaodong
 * @Filename:com.andy.sixha.mvp.presenter
 * @Description:
 * @Copyright: Copyright (c) 2014 Tuandai Inc. All Rights Reserved.
 * @date 2016-12-13 11:26
 */
public class GetLatestPresent extends Presenter<GetLatestView> {
    public GetLatestPresent(GetLatestView view) {
        super(view);
    }
    public void getLatestMark(){
        view.onRequestLoading();
        Observable<MarkSix> observable = MarkSixApi.getLatestMark();
        compositeSubscription.add(
                observable.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(markSix -> {
                            if (compositeSubscription.isUnsubscribed()) {
                                return;
                            }
                            if (view == null || view.getActivity() == null || view.getActivity().isFinishing()) {
                                return;
                            }
                            if (markSix != null) {
                                view.onGetLatestFinish(markSix);
                            } else {
                                view.onRequestError(ErrorCode.ERROR_REQUEST_DATA, "ÍøÂç´íÎó");
                            }
                        }, error -> {
                            Log.d("markSix", error.getMessage());
                            view.onRequestError(ErrorCode.ERROR_OBSERVER_DATA, error.getMessage());
                        }, () -> view.onRequestFinished())
        );
    }
}
