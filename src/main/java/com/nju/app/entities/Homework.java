package com.nju.app.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "homework")
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String hId;
    private String cId;

    private String hTitle;
    private Date releasetime;

    public Homework() {
    }

    public Homework(String hId, String cId, String hTitle, Date releasetime) {
        this.hId = hId;
        this.cId = cId;
        this.hTitle = hTitle;
        this.releasetime = releasetime;
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

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String gethTitle() {
        return hTitle;
    }

    public void sethTitle(String hTitle) {
        this.hTitle = hTitle;
    }

    public Date getReleasetime() {
        return releasetime;
    }

    public void setReleasetime(Date releasetime) {
        this.releasetime = releasetime;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "id=" + id +
                ", hId='" + hId + '\'' +
                ", cId='" + cId + '\'' +
                ", hTitle='" + hTitle + '\'' +
                ", releasetime=" + releasetime +
                '}';
    }
}
