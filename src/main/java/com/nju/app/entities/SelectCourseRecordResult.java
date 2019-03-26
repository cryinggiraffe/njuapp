package com.nju.app.entities;

import java.io.Serializable;

public class SelectCourseRecordResult implements Serializable{

    private String scId;

    private String cId;
    private String cName;

    private String sId;
    private String sName;

    private Integer score;

    public SelectCourseRecordResult() {
    }

    public SelectCourseRecordResult(String scId, String cId, String cName, String sId, String sName, Integer score) {
        this.scId = scId;
        this.cId = cId;
        this.cName = cName;
        this.sId = sId;
        this.sName = sName;
        this.score = score;
    }

    public String getScId() {
        return scId;
    }

    public void setScId(String scId) {
        this.scId = scId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "SelectCourseRecordResult{" +
                "scId='" + scId + '\'' +
                ", cId='" + cId + '\'' +
                ", sId='" + sId + '\'' +
                ", sName='" + sName + '\'' +
                ", score=" + score +
                '}';
    }
}
