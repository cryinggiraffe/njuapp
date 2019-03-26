package com.nju.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "select_course_record")
public class SelectCourseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String scId;

    private String sId;
    private String cId;

    private Integer score;
    private Integer isSelected;

    public SelectCourseRecord() {
    }

    public SelectCourseRecord(String scId, String sId, String cId, Integer score, Integer isSelected) {
        this.scId = scId;
        this.sId = sId;
        this.cId = cId;
        this.score = score;
        this.isSelected = isSelected;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScId() {
        return scId;
    }

    public void setScId(String scId) {
        this.scId = scId;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Integer isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public String toString() {
        return "SelectCourseRecord{" +
                "id=" + id +
                ", scId='" + scId + '\'' +
                ", sId='" + sId + '\'' +
                ", cId='" + cId + '\'' +
                ", score=" + score +
                ", isSelected=" + isSelected +
                '}';
    }
}
