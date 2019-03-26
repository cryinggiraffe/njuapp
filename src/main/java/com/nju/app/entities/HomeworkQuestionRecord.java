package com.nju.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "homework_question_record")
public class HomeworkQuestionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String hId;
    private String cqId;
    private String sqId;

    public HomeworkQuestionRecord() {
    }

    public HomeworkQuestionRecord(String hId, String cqId) {
        this.hId = hId;
        this.cqId = cqId;
    }

    public HomeworkQuestionRecord(String hId, String cqId, String sqId) {
        this.hId = hId;
        this.cqId = cqId;
        this.sqId = sqId;
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

    public String getCqId() {
        return cqId;
    }

    public void setCqId(String cqId) {
        this.cqId = cqId;
    }

    public String getSqId() {
        return sqId;
    }

    public void setSqId(String sqId) {
        this.sqId = sqId;
    }

    @Override
    public String toString() {
        return "HomeworkQuestionRecord{" +
                "id=" + id +
                ", hId='" + hId + '\'' +
                ", cqId='" + cqId + '\'' +
                ", sqId='" + sqId + '\'' +
                '}';
    }
}
