package com.andy.sixha.api;

import android.content.Context;


import com.andy.sixha.bean.TrendingRepo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


public class TrendingApi extends BaseApi {
    public TrendingApi() {
    }

    private interface TrendingService {
        @GET("{lang}")
        Observable<List<TrendingRepo>> getTrendingRepos(@Path("lang") String lang);
    }

    protected static final TrendingService service = getRetrofit().create(TrendingService.class);

    public static Observable<List<TrendingRepo>> getRepos(String lang) {
        Observable<List<TrendingRepo>> observable = service.getTrendingRepos(lang);
        return observable;
    }

    /**
     * 获取缓存数据
     *
     * @param lang
     * @returnxxxx
     */
//    public static Observable<List<TrendingRepo>> getReposFromeCache(Context context, String lang) {
//        Observable<List<TrendingRepo>> observable = Observable.create(subscriber -> {
//            String json = JsonCacheUtils.readFile(context, lang);
//            List<TrendingRepo> list = GsonBuilder.buildArray(json, TrendingRepo.class);
//            subscriber.onNext(list);
//            subscriber.onCompleted();
//        });
//        return observable;
//    }
}
