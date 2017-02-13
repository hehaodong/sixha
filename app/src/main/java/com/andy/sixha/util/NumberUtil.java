package com.andy.sixha.util;

import com.andy.sixha.Common.AppConstant;

/**
 * @author hehaodong
 * @Filename:com.andy.sixha.util
 * @Description: 数字类
 * @Copyright: Copyright (c) 2014 Tuandai Inc. All Rights Reserved.
 * @date 2016-11-18 16:38
 */
public class NumberUtil {
    public static NumberUtil instance;

    public  static NumberUtil getInstance(){
        if(instance == null){
            instance = new NumberUtil();
        }
        return instance;
    }
    /**
     * 单双判断
     * @param number
     * @return
     */
     private  int isWin(int number){
         //49无输赢
         if(number == 49){
             return 0;
             //开双
         }else if(number%2==0){
             return AppConstant.BUY_EVEN;
         }else {//开单
             return AppConstant.BUY_ODD;
         }
     }
    /**
     * 计算多少钱
     * @param buyType 买单还是买双
     * @param buyAoumt 购买金额
     * @return
     */
    public double earnAmount(int buyType,int openNumber,int buyAoumt){
        int winType = isWin(openNumber);
        if(buyType == winType){
            return buyAoumt * 0.9;
        }else if(buyType == 0){
            return 0;
        }else {
            return 0-buyAoumt;
        }
    }

}
