package com.nju.app.entities;

import javax.persistence.*;

//成绩复议
@Entity
@Table(name = "course_review_record")
public class CourseReviewRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String crId;

    private String cId;

    private String sId;

    private String sName;

    //成绩复议
    private String review;

    //复议结果
    private String result;

    public CourseReviewRecord() {
    }

    public CourseReviewRecord(String crId, String cId, String sId, String sName, String review, String result) {
        this.crId = crId;
        this.cId = cId;
        this.sId = sId;
        this.sName = sName;
        this.review = review;
        this.result = result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCrId() {
        return crId;
    }

    public void setCrId(String crId) {
        this.crId = crId;
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

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "CourseReviewRecord{" +
                "id=" + id +
                ", crId='" + crId + '\'' +
                ", cId='" + cId + '\'' +
                ", sId='" + sId + '\'' +
                ", sName='" + sName + '\'' +
                ", review='" + review + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
