package com.nju.app.entities;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cId;
    private String cName;
    private Date start;
    private Date end;

    private String token;

    public Course() {
    }

    public Course(String cId, String cName, Date start, Date end, String token) {
        this.cId = cId;
        this.cName = cName;
        this.start = start;
        this.end = end;
        this.token = token;
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

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", cId='" + cId + '\'' +
                ", cName='" + cName + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", token='" + token + '\'' +
                '}';
    }
}
