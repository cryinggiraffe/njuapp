package com.nju.app.entities;

import java.io.Serializable;

public class CourseSummaryResult implements Serializable{

    private String csId;
    private String cId;
    private String tId;
    private String tName;
    private String summary;

    public CourseSummaryResult() {
    }

    public CourseSummaryResult(String csId, String cId, String tId, String tName, String summary) {
        this.csId = csId;
        this.cId = cId;
        this.tId = tId;
        this.tName = tName;
        this.summary = summary;
    }

    public String getCsId() {
        return csId;
    }

    public void setCsId(String csId) {
        this.csId = csId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "CourseSummaryResult{" +
                "csId='" + csId + '\'' +
                ", cId='" + cId + '\'' +
                ", tId='" + tId + '\'' +
                ", tName='" + tName + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }
}
