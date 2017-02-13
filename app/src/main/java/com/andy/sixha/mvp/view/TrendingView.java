package com.andy.sixha.mvp.view;


import com.andy.sixha.bean.TrendingRepo;

import java.util.List;

/**
 * Created by lancelot on 2016/12/3.
 */

public interface TrendingView extends BaseView {

    void onGetTrendingsFinish(List<TrendingRepo> repos);

}
