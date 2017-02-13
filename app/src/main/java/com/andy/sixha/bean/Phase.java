package com.andy.sixha.bean;

import java.io.Serializable;

/**
 * @author hehaodong
 * @Filename:com.andy.sixha.bean
 * @Description:
 * @Copyright: Copyright (c) 2014 Tuandai Inc. All Rights Reserved.
 * @date 2016-11-18 11:20
 */
public class Phase implements Serializable {
    /**
     * 小码
     */
    private String smallCode;
    /**
     * 大码
     */
    private int bigCode;
    /**
     * 购买金额
     */
    private int buyAmount;

    /**
     * 购买类型
     */
    private int buyType;
    private boolean isOpen;
    /**
     * 期数
     */
    private String phase;
    public Phase(){

    }

    public Phase(String smallCode, int bigCode, int buyAmount, int buyType, boolean isOpen,String phase) {
        this.smallCode = smallCode;
        this.bigCode = bigCode;
        this.buyAmount = buyAmount;
        this.buyType = buyType;
        this.isOpen = isOpen;
        this.phase = phase;
    }

    public String getSmallCode() {
        return smallCode;
    }

    public void setSmallCode(String smallCode) {
        this.smallCode = smallCode;
    }

    public int getBigCode() {
        return bigCode;
    }

    public void setBigCode(int bigCode) {
        this.bigCode = bigCode;
    }

    public int getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(int buyAmount) {
        this.buyAmount = buyAmount;
    }

    public int getBuyType() {
        return buyType;
    }

    public void setBuyType(int buyType) {
        this.buyType = buyType;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean open) {
        isOpen = open;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }
}
