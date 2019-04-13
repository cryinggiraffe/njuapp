package com.nju.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "choice_question_record")
public class ChoiceQuestionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //homework id
    private String hId;

    //学生id
    private String sId;

    //题目id
    private String cqId;

    //学生选择选项
    private String cqSelect;

    public ChoiceQuestionRecord() {
    }

    public ChoiceQuestionRecord(String hId, String sId, String cqId, String cqSelect) {
        this.hId = hId;
        this.sId = sId;
        this.cqId = cqId;
        this.cqSelect = cqSelect;
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

    public String getCqId() {
        return cqId;
    }

    public void setCqId(String cqId) {
        this.cqId = cqId;
    }

    public String getCqSelect() {
        return cqSelect;
    }

    public void setCqSelect(String cqSelect) {
        this.cqSelect = cqSelect;
    }

    @Override
    public String toString() {
        return "ChoiceQuestionRecord{" +
                "id=" + id +
                ", hId='" + hId + '\'' +
                ", sId='" + sId + '\'' +
                ", cqId='" + cqId + '\'' +
                ", cqSelect='" + cqSelect + '\'' +
                '}';
    }
}
