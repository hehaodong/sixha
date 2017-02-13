package com.andy.sixha.util;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * @author hehaodong
 * @Filename:com.andy.sixha.util
 * @Description:
 * @Copyright: Copyright (c) 2014 Tuandai Inc. All Rights Reserved.
 * @date 2016-12-13 11:31
 */
public class ToastUtil {
    private static Toast mToast;
    /**
     * @param context
     * @param msg
     */
    public static void show(Context context, String msg) {
        try {
            if (mToast != null){
                mToast.setText(msg);
            }else{
                mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            }
            mToast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void show(Context context, @StringRes int msgRes) {
        try {
            if (mToast != null){
                mToast.setText(msgRes);
            }else{
                mToast = Toast.makeText(context, msgRes, Toast.LENGTH_SHORT);
            }
            mToast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
