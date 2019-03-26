package com.nju.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "class_schedule")
public class ClassSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String csId;
    private String csName;
    private String url;

    public ClassSchedule() {
    }

    public ClassSchedule(String csId, String csName, String url) {
        this.csId = csId;
        this.csName = csName;
        this.url = url;
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

    public String getCsName() {
        return csName;
    }

    public void setCsName(String csName) {
        this.csName = csName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ClassSchedule{" +
                "id=" + id +
                ", csId='" + csId + '\'' +
                ", csName='" + csName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
