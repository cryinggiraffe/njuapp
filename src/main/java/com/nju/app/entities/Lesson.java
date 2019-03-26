package com.nju.app.entities;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "lesson")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cId;
    private String lId;
    private String lName;

    //上课时间
    private Date lTime;
    //每节课的长度
    private Integer lLength;

    //每节课的实到人数
    private Integer actualNumber;
    //每节课的应到人数
    private Integer reachedNumber;

    //签到码
    private String signCode;

    //签到开始时间，即当前发布时间
    private Date start;

    //签到结束时间
    private Date end;

    public Lesson() {
    }


    public Lesson(String cId, String lId, String lName, Date lTime, Integer lLength, Integer actualNumber, Integer reachedNumber, String signCode, Date start, Date end) {
        this.cId = cId;
        this.lId = lId;
        this.lName = lName;
        this.lTime = lTime;
        this.lLength = lLength;
        this.actualNumber = actualNumber;
        this.reachedNumber = reachedNumber;
        this.signCode = signCode;
        this.start = start;
        this.end = end;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getlId() {
        return lId;
    }

    public void setlId(String lId) {
        this.lId = lId;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public Date getlTime() {
        return lTime;
    }

    public void setlTime(Date lTime) {
        this.lTime = lTime;
    }

    public Integer getlLength() {
        return lLength;
    }

    public void setlLength(Integer lLength) {
        this.lLength = lLength;
    }

    public Integer getActualNumber() {
        return actualNumber;
    }

    public void setActualNumber(Integer actualNumber) {
        this.actualNumber = actualNumber;
    }

    public Integer getReachedNumber() {
        return reachedNumber;
    }

    public void setReachedNumber(Integer reachedNumber) {
        this.reachedNumber = reachedNumber;
    }

    public String getSignCode() {
        return signCode;
    }

    public void setSignCode(String signCode) {
        this.signCode = signCode;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", cId='" + cId + '\'' +
                ", lId='" + lId + '\'' +
                ", lName='" + lName + '\'' +
                ", lTime=" + lTime +
                ", lLength=" + lLength +
                ", actualNumber=" + actualNumber +
                ", reachedNumber=" + reachedNumber +
                ", signCode='" + signCode + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
