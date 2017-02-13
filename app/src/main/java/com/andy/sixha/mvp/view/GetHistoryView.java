package com.andy.sixha.mvp.view;

import android.app.Activity;

import com.andy.sixha.bean.MarkSix;

import java.util.List;

/**
 * @author hehaodong
 * @Filename:com.andy.sixha.mvp.view
 * @Description:
 * @Copyright: Copyright (c) 2014 Tuandai Inc. All Rights Reserved.
 * @date 2017-01-03 10:55
 */
public interface GetHistoryView extends BaseView {
    void onGetHistory(List<MarkSix> markSixList);
}
