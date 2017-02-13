package com.andy.sixha.api.bean;

import android.text.TextUtils;

/**
 * @author: liaoshengjian
 * @Filename:
 * @Description:    服务器约定返回的错误，统一解析
 * @Copyright: Copyright (c) 2016 Tuandai Inc. All rights reserved.
 * @date: 2016/12/2 09:57
 */
public class HttpResultException extends RuntimeException {

    public static final int ERROR_DEFAULT = 400;

    /**
     * 错误码
     */
    private int code;

    /**
     * 错误信息
     */
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpResultException(String message) {
        super(message);
    }

    public HttpResultException(int resultCode, String message) {
        this(getHttpExceptionMessage(resultCode, message));
        this.code = resultCode;
        this.message = message;
    }


    /**
     * 错误码解析
     * @param code
     * @return
     */
    private static String getHttpExceptionMessage(int code, String message){
        String msg;
        switch (code) {
            case ERROR_DEFAULT:
                msg = "请不要乱操作";
                break;
            default:
                msg = !TextUtils.isEmpty(message) ? message : "未知错误";
        }
        return msg;
    }


}
