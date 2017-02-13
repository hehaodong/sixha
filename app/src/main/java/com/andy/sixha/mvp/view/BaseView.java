package com.andy.sixha.mvp.view;

import android.app.Activity;

public interface BaseView {
    Activity getActivity();

    /**
     * 请求中
     */
    void onRequestLoading();

    /**
     * 请求结束
     */
    void onRequestFinished();

    /**
     * 请求出错
     * @param code
     * @param message
     */
    void onRequestError(int code, String message);
//    /**
//     * 显示加载框
//     */
//    void showProgressDialog(String tips);
//
//    /**
//     * 隐藏加载框
//     */
//    void dismissProgressDialog();
//
//    /**
//     * 显示情感图
//     */
//    void showReloadView(String subscriberId);
//
//    /**
//     * 隐藏情感图
//     */
//    void dismissReloadView();
//
//    /**
//     * 点击重新加载事件
//     */
//    void clickReloadData();
}