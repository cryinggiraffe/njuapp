package com.nju.app.entities;

import javax.persistence.*;
import java.sql.Date;

//课程反馈
@Entity
@Table(name = "course_comment_record")
public class CourseCommentRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ccId;

    private String cId;

    private String sId;

    private String sName;

    private String comment;

    private Date cTime;

    public CourseCommentRecord() {
    }

    public CourseCommentRecord(String ccId, String cId, String sId, String sName, String comment, Date cTime) {
        this.ccId = ccId;
        this.cId = cId;
        this.sId = sId;
        this.sName = sName;
        this.comment = comment;
        this.cTime = cTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCcId() {
        return ccId;
    }

    public void setCcId(String ccId) {
        this.ccId = ccId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    @Override
    public String toString() {
        return "CourseCommentRecord{" +
                "id=" + id +
                ", ccId='" + ccId + '\'' +
                ", cId='" + cId + '\'' +
                ", sId='" + sId + '\'' +
                ", sName='" + sName + '\'' +
                ", comment='" + comment + '\'' +
                ", cTime=" + cTime +
                '}';
    }
}
