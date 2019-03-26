package com.nju.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "simple_question")
public class SimpleQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //哪门课的题目
    private String cId;

    private String sqId;
    //简答题目内容
    private String sqContent;
    //答案
    private String sqCorrectAnswer;

    public SimpleQuestion() {
    }

    public SimpleQuestion(String cId, String sqId, String sqContent, String sqCorrectAnswer) {
        this.cId = cId;
        this.sqId = sqId;
        this.sqContent = sqContent;
        this.sqCorrectAnswer = sqCorrectAnswer;
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

    public String getSqId() {
        return sqId;
    }

    public void setSqId(String sqId) {
        this.sqId = sqId;
    }

    public String getSqContent() {
        return sqContent;
    }

    public void setSqContent(String sqContent) {
        this.sqContent = sqContent;
    }

    public String getSqCorrectAnswer() {
        return sqCorrectAnswer;
    }

    public void setSqCorrectAnswer(String sqCorrectAnswer) {
        this.sqCorrectAnswer = sqCorrectAnswer;
    }

    @Override
    public String toString() {
        return "SimpleQuestion{" +
                "id=" + id +
                ", cId='" + cId + '\'' +
                ", sqId='" + sqId + '\'' +
                ", sqContent='" + sqContent + '\'' +
                ", sqCorrectAnswer='" + sqCorrectAnswer + '\'' +
                '}';
    }
}
