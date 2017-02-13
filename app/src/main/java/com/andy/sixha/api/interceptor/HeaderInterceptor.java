package com.andy.sixha.api.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author hehaodong
 * @Filename:com.andy.sixha.api.interceptor
 * @Description:
 * @Copyright: Copyright (c) 2014 Tuandai Inc. All Rights Reserved.
 * @date 2017-02-07 10:28
 */
public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        //过滤需要请求
        if (original.url().toString().contains("book/") ||
                original.url().toString().contains("book-list/") ||
                original.url().toString().contains("toc/") ||
                original.url().toString().contains("post/") ||
                original.url().toString().contains("user/")) {
            Request request = original.newBuilder()
//                    .addHeader("User-Agent", "ZhuiShuShenQi/3.40[preload=false;locale=zh_CN;clientidbase=android-nvidia]") // 不能转UTF-8
//                    .addHeader("X-User-Agent", "ZhuiShuShenQi/3.40[preload=false;locale=zh_CN;clientidbase=android-nvidia]")
//                    .addHeader("X-Device-Id", "")
//                    .addHeader("Host", "api.zhuishushenqi.com")
                    .addHeader("Connection", "Keep-Alive")
                    .addHeader("If-None-Match", "W/\"2a04-4nguJ+XAaA1yAeFHyxVImg\"")
                    .addHeader("If-Modified-Since", "Tue, 02 Aug 2016 03:20:06 UTC")
                    .build();
            return chain.proceed(request);
        }
        return chain.proceed(original);
    }
}
