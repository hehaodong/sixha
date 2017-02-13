package com.andy.sixha.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hehaodong
 * @Filename:com.andy.sixha.bean
 * @Description:
 * @Copyright: Copyright (c) 2014 Tuandai Inc. All Rights Reserved.
 * @date 2016-12-06 10:27
 */
public class MarkSix {
    private String smallMark;
    private String specialMark;
    private String stage;
    private String one_code;
    private List<String> six_code = new ArrayList<String>();
    public String getSmallMark() {
        return smallMark;
    }

    public void setSmallMark(String smallMark) {
        this.smallMark = smallMark;
    }

    public String getSpecialMark() {
        return specialMark;
    }

    public void setSpecialMark(String specialMark) {
        this.specialMark = specialMark;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getOne_code() {
        return one_code;
    }

    public void setOne_code(String one_code) {
        this.one_code = one_code;
    }

    public List<String> getSix_code() {
        return six_code;
    }

    public void setSix_code(List<String> six_code) {
        this.six_code = six_code;
    }

    @Override
    public String toString() {
        return "MarkSix{" +
                "smallMark='" + smallMark + '\'' +
                ", specialMark='" + specialMark + '\'' +
                ", stage='" + stage + '\'' +
                ", one_code='" + one_code + '\'' +
                ", six_code=" + six_code +
                '}';
    }
}
