package com.andy.sixha.thirdpart;

import android.text.TextUtils;
import android.util.Log;

import com.andy.sixha.Common.AppConfig;
import com.orhanobut.logger.Logger;

/**
 * @author hehaodong
 * @Filename:com.andy.sixha.thirdpart
 * @Description:
 * @Copyright: Copyright (c) 2014 Tuandai Inc. All Rights Reserved.
 * @date 2016-11-25 16:01
 */
public class LoggerUtil {
    public static final String TAG = "LogUtils";

    public static final void d(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        if (AppConfig.IS_DEBUG_ABLE){
            Logger.t(TAG).d( message);
        }
    }

    /**
     * JSON格式的打印
     * @param json
     */
    public static final void dJson(String json) {
        if (TextUtils.isEmpty(json)) {
            return;
        }
        if (AppConfig.IS_DEBUG_ABLE){
            Logger.t(TAG).json(json);
        }
    }
    /**
     * JSON格式的打印
     * @param json
     */
    public static final void dJson(String tag,String json) {
        if (TextUtils.isEmpty(json)) {
            return;
        }
        if (AppConfig.IS_DEBUG_ABLE){
            Logger.t(tag).json(json);
        }
    }
    public static final void e(Exception e) {
        if (AppConfig.IS_DEBUG_ABLE){
            Logger.t(TAG).e(e.getMessage());
        }
    }
    public static final void e(Throwable e) {
        if (AppConfig.IS_DEBUG_ABLE){
            Logger.t(TAG).e(e.getMessage());
        }
    }
}
