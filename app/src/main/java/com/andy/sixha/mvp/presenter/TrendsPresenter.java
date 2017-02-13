package com.andy.sixha.mvp.presenter;

import android.util.Log;


import com.andy.sixha.R;
import com.andy.sixha.api.TrendingApi;
import com.andy.sixha.bean.TrendingRepo;
import com.andy.sixha.mvp.view.TrendingView;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lancelot on 2016/12/3.
 */

public class TrendsPresenter extends Presenter<TrendingView> {
    public TrendsPresenter(TrendingView view) {
        super(view);
    }

    /**
     * @param lang
     */
    public void getTrending(String lang, boolean cache) {
        view.onRequestLoading();
        Observable<List<TrendingRepo>> observable = TrendingApi.getRepos(lang);
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
                                view.onGetTrendingsFinish(repositories);
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
