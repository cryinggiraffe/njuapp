package com.nju.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "choice_question")
public class ChoiceQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cId;

    private String cqId;
    //选择题目内容
    private String cqContent;
    //选项
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    //答案
    private String cqCorrectAnswer;

    public ChoiceQuestion() {
    }

    public ChoiceQuestion(String cId, String cqId, String cqContent, String optionA, String optionB, String optionC, String optionD, String cqCorrectAnswer) {
        this.cId = cId;
        this.cqId = cqId;
        this.cqContent = cqContent;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.cqCorrectAnswer = cqCorrectAnswer;
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

    public String getCqId() {
        return cqId;
    }

    public void setCqId(String cqId) {
        this.cqId = cqId;
    }

    public String getCqContent() {
        return cqContent;
    }

    public void setCqContent(String cqContent) {
        this.cqContent = cqContent;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getCqCorrectAnswer() {
        return cqCorrectAnswer;
    }

    public void setCqCorrectAnswer(String cqCorrectAnswer) {
        this.cqCorrectAnswer = cqCorrectAnswer;
    }


    @Override
    public String toString() {
        return "ChoiceQuestion{" +
                "id=" + id +
                ", cId='" + cId + '\'' +
                ", cqId='" + cqId + '\'' +
                ", cqContent='" + cqContent + '\'' +
                ", optionA='" + optionA + '\'' +
                ", optionB='" + optionB + '\'' +
                ", optionC='" + optionC + '\'' +
                ", optionD='" + optionD + '\'' +
                ", cqCorrectAnswer='" + cqCorrectAnswer + '\'' +
                '}';
    }
}
