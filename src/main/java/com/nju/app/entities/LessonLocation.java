package com.nju.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "lesson_location")
public class LessonLocation
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cId;
    private String lId;
    private String lName;

    private String latitude;
    private String longitude;

    public LessonLocation() {
    }

    public LessonLocation(String cId, String lId, String lName, String latitude, String longitude) {
        this.cId = cId;
        this.lId = lId;
        this.lName = lName;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getlId() {
        return lId;
    }

    public void setlId(String lId) {
        this.lId = lId;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "LessonLocation{" +
                "id=" + id +
                ", cId='" + cId + '\'' +
                ", lId='" + lId + '\'' +
                ", lName='" + lName + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
