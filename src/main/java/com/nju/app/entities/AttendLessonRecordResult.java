package com.nju.app.entities;

public class AttendLessonRecordResult {

    private String lId;

    private String sId;
    private String sName;

    private String alTime;

    public AttendLessonRecordResult() {
    }

    public AttendLessonRecordResult(String lId, String sId, String sName, String alTime) {
        this.lId = lId;
        this.sId = sId;
        this.sName = sName;
        this.alTime = alTime;
    }

    public String getlId() {
        return lId;
    }

    public void setlId(String lId) {
        this.lId = lId;
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

    public String getAlTime() {
        return alTime;
    }

    public void setAlTime(String alTime) {
        this.alTime = alTime;
    }

    @Override
    public String toString() {
        return "AttendLessonRecordResult{" +
                "lId='" + lId + '\'' +
                ", sId='" + sId + '\'' +
                ", sName='" + sName + '\'' +
                ", alTime='" + alTime + '\'' +
                '}';
    }
}
