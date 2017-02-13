package com.andy.sixha.api.bean;

/**
 * @author: liaoshengjian
 * @Filename:
 * @Description:    约定请求返回json格式
 * @Copyright: Copyright (c) 2016 Tuandai Inc. All rights reserved.
 * @date: 2016/12/1 20:52
 */
public class HttpResult<T> {

    /**
     * 返回状态code
     */
    private int code;

    /**
     * 返回提示
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
