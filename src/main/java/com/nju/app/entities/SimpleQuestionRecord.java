package com.nju.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "simple_question_record")
public class SimpleQuestionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //homework id
    private String hId;

    //学生id
    private String sId;

    //简答题题目id
    private String sqId;

    //学生回答内容
    private String sqResult;

    public SimpleQuestionRecord() {
    }

    public SimpleQuestionRecord(String hId, String sId, String sqId, String sqResult) {
        this.hId = hId;
        this.sId = sId;
        this.sqId = sqId;
        this.sqResult = sqResult;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String gethId() {
        return hId;
    }

    public void sethId(String hId) {
        this.hId = hId;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getSqId() {
        return sqId;
    }

    public void setSqId(String sqId) {
        this.sqId = sqId;
    }

    public String getSqResult() {
        return sqResult;
    }

    public void setSqResult(String sqResult) {
        this.sqResult = sqResult;
    }

    @Override
    public String toString() {
        return "SimpleQuestionRecord{" +
                "id=" + id +
                ", hId='" + hId + '\'' +
                ", sId='" + sId + '\'' +
                ", sqId='" + sqId + '\'' +
                ", sqResult='" + sqResult + '\'' +
                '}';
    }
}
