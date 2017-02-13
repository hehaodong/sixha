package com.andy.sixha.api;

import com.andy.sixha.bean.MarkSix;
import com.andy.sixha.bean.TrendingRepo;

import java.util.List;

import rx.Observable;

/**
 * @author hehaodong
 * @Filename:com.andy.sixha.api
 * @Description:
 * @Copyright: Copyright (c) 2014 Tuandai Inc. All Rights Reserved.
 * @date 2016-12-12 16:13
 */
public class MarkSixApi extends BaseApi {
    public MarkSixApi(){};
    protected static final MarkSixApiService service = getMarkSixRetrofit().create(MarkSixApiService.class);

    /**
     *
     * @return
     */
    public static Observable<MarkSix> getLatestMark() {
        Observable<MarkSix> observable = service.getLatestMark();
        return observable;
    }
    public static Observable<List<MarkSix>> getHistory(String year) {
        Observable<List<MarkSix>> observable = service.getHistory(year);
        return observable;
    }

}
