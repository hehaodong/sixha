package com.andy.sixha.mvp.presenter;

import android.util.Log;

import com.andy.sixha.api.MarkSixApi;
import com.andy.sixha.api.TrendingApi;
import com.andy.sixha.bean.MarkSix;
import com.andy.sixha.bean.TrendingRepo;
import com.andy.sixha.mvp.view.GetHistoryView;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author hehaodong
 * @Filename:com.andy.sixha.mvp.presenter
 * @Description:
 * @Copyright: Copyright (c) 2014 Tuandai Inc. All Rights Reserved.
 * @date 2017-01-03 10:58
 */
public class HistoryPresenter extends  Presenter<GetHistoryView> {
    public HistoryPresenter(GetHistoryView view) {
        super(view);
    }
    public void getHistory(String year) {
        view.onRequestLoading();
        Observable<List<MarkSix>> observable = MarkSixApi.getHistory(year);
        compositeSubscription.add(
                observable.observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(repositories -> {
                            if (compositeSubscription.isUnsubscribed()) {
                                return;
                            }
                            if (view == null || view.getActivity() == null || view.getActivity().isFinishing()) {
                                return;
                            }
                            if (repositories != null && repositories.size() > 0) {
                                view.onGetHistory(repositories);
                            } else {
                                view.onRequestError(ErrorCode.ERROR_REQUEST_DATA, "网络出错");
                            }
                        }, error -> {
                            Log.d("repositories", error.getMessage());
                            view.onRequestError(ErrorCode.ERROR_OBSERVER_DATA, error.getMessage());
                        }, () -> view.onRequestFinished())
        );
    }
}
