package com.nju.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "teach_course_record")
public class TeachCourseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tId;
    private String cId;

    public TeachCourseRecord() {
    }

    public TeachCourseRecord(String tId, String cId) {
        this.tId = tId;
        this.cId = cId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    @Override
    public String toString() {
        return "TeachCourseRecord{" +
                "id=" + id +
                ", tId='" + tId + '\'' +
                ", cId='" + cId + '\'' +
                '}';
    }
}
