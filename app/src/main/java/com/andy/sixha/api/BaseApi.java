package com.andy.sixha.api;

import android.util.Log;

import com.andy.sixha.BuildConfig;
import com.andy.sixha.api.interceptor.HeaderInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by lancelot on 2016/12/2.
 */

public class BaseApi {
    public final static String API_MARK_SIX_SERVER = "http://hkjc.leanapp.cn/";
    public final static String API_SERVER = "https://angrycode.leanapp.cn/api/github/trending/";

    private final static OkHttpClient mOkHttpClient;
    private static Retrofit sRetrofit;

    public BaseApi() {


    }

    static {
//        Cache cache = new Cache(new File("/data/data/net.angrycode.githubtrending/cache"), 1024 * 1024);
        //创建拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.i("RetrofitLog","retrofitBack = "+message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mOkHttpClient = new OkHttpClient.Builder()
//                .cache(cache)
//                .addNetworkInterceptor(new CacheInterceptor())
//                .addInterceptor(new DecInterceptor())
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new HeaderInterceptor())
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
//sdfs
    }

    protected static Retrofit getMarkSixRetrofit() {
        if (sRetrofit == null) {
            //构建Retrofit
            sRetrofit = new Retrofit.Builder()
                    //配置服务器路径
                    .baseUrl(API_MARK_SIX_SERVER + "/")
                    //配置转化库，默认是Gson
                    .addConverterFactory(GsonConverterFactory.create())
                    //配置回调库，采用RxJava
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    //设置OKHttpClient为网络客户端
                    .client(mOkHttpClient)
                    .build();
        }
        return sRetrofit;
    }
    protected static Retrofit getRetrofit() {
            //构建Retrofit
            return new Retrofit.Builder()
                    //配置服务器路径
                    .baseUrl(API_SERVER + "/")
                    //设置日期解析格式，这样可以直接解析Date类型
//                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    //配置转化库，默认是Gson
                    .addConverterFactory(GsonConverterFactory.create())
                    //配置回调库，采用RxJava
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    //设置OKHttpClient为网络客户端
                    .client(mOkHttpClient)
                    .build();
    }
}
