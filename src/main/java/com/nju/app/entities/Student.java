package com.nju.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String sId;
    private String id_card;
    private String sName;
    private String sex;

    public Student() {
    }

    public Student(String sId, String id_card, String sName, String sex) {
        this.sId = sId;
        this.id_card = id_card;
        this.sName = sName;
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", sId='" + sId + '\'' +
                ", id_card='" + id_card + '\'' +
                ", sName='" + sName + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
