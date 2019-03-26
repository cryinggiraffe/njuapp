package com.nju.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //和user的username相同
    private String tId;
    private String id_card;
    private String tName;
    private String sex;

    public Teacher() {
    }

    public Teacher(String tId, String id_card, String tName, String sex) {
        this.tId = tId;
        this.id_card = id_card;
        this.tName = tName;
        this.sex = sex;
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

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", tId='" + tId + '\'' +
                ", id_card='" + id_card + '\'' +
                ", tName='" + tName + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
