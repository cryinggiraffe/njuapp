package com.nju.app.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "attend_lesson_record")
public class AttendLessonRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String alId;

    private String sId;
    private String lId;

    //是否到课
    private Integer isAttended;

    //到课时间
    //private String alTime

    public AttendLessonRecord() {
    }

    public AttendLessonRecord(String alId, String sId, String lId, Integer isAttended) {
        this.alId = alId;
        this.sId = sId;
        this.lId = lId;
        this.isAttended = isAttended;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlId() {
        return alId;
    }

    public void setAlId(String alId) {
        this.alId = alId;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getlId() {
        return lId;
    }

    public void setlId(String lId) {
        this.lId = lId;
    }

    public Integer getIsAttended() {
        return isAttended;
    }

    public void setIsAttended(Integer isAttended) {
        this.isAttended = isAttended;
    }


    @Override
    public String toString() {
        return "AttendLessonRecord{" +
                "id=" + id +
                ", alId='" + alId + '\'' +
                ", sId='" + sId + '\'' +
                ", lId='" + lId + '\'' +
                ", isAttended=" + isAttended +
                '}';
    }
}
