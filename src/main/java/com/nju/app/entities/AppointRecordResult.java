package com.nju.app.entities;

public class AppointRecordResult {

    private String aId;
    private String sId;
    private String sName;

    public AppointRecordResult() {
    }

    public AppointRecordResult(String aId, String sId, String sName) {
        this.aId = aId;
        this.sId = sId;
        this.sName = sName;
    }

    public String getaId() {
        return aId;
    }

    public void setaId(String aId) {
        this.aId = aId;
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

    @Override
    public String toString() {
        return "AppointRecordResult{" +
                "aId='" + aId + '\'' +
                ", sId='" + sId + '\'' +
                ", sName='" + sName + '\'' +
                '}';
    }
}
