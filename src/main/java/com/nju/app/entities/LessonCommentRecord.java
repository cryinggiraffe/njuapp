package com.nju.app.entities;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "lesson_comment_record")
public class LessonCommentRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String lcId;

    private String sId;
    private String lId;

    private String sName;
    //课堂反馈
    private String comment;
    //反馈时间
    private Date cTime;

    public LessonCommentRecord() {
    }

    public LessonCommentRecord(String lcId, String sId, String lId, String sName, String comment, Date cTime) {
        this.lcId = lcId;
        this.sId = sId;
        this.lId = lId;
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

    public String getLcId() {
        return lcId;
    }

    public void setLcId(String lcId) {
        this.lcId = lcId;
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
        return "LessonCommentRecord{" +
                "id=" + id +
                ", lcId='" + lcId + '\'' +
                ", sId='" + sId + '\'' +
                ", lId='" + lId + '\'' +
                ", sName='" + sName + '\'' +
                ", comment='" + comment + '\'' +
                ", cTime=" + cTime +
                '}';
    }
}
