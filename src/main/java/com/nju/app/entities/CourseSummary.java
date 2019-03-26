package com.nju.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "course_summary")
public class CourseSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String csId;

    private String cId;
    private String tId;

    private String summary;

    public CourseSummary() {
    }

    public CourseSummary(String csId, String cId, String tId, String summary) {
        this.csId = csId;
        this.cId = cId;
        this.tId = tId;
        this.summary = summary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCsId() {
        return csId;
    }

    public void setCsId(String csId) {
        this.csId = csId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "CourseSummary{" +
                "id=" + id +
                ", csId='" + csId + '\'' +
                ", cId='" + cId + '\'' +
                ", tId='" + tId + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }
}
