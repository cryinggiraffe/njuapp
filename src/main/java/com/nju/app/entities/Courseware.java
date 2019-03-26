package com.nju.app.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "courseware")
public class Courseware {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cId;

    private String cwId;
    private String cwName;
    private String url;

    private Integer viewCount;
    private Integer downCount;

    private Date uploadTime;

    public Courseware() {
    }

    public Courseware(String cId, String cwId, String cwName, String url, Integer viewCount, Integer downCount, Date uploadTime) {
        this.cId = cId;
        this.cwId = cwId;
        this.cwName = cwName;
        this.url = url;
        this.viewCount = viewCount;
        this.downCount = downCount;
        this.uploadTime = uploadTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCwId() {
        return cwId;
    }

    public void setCwId(String cwId) {
        this.cwId = cwId;
    }

    public String getCwName() {
        return cwName;
    }

    public void setCwName(String cwName) {
        this.cwName = cwName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getDownCount() {
        return downCount;
    }

    public void setDownCount(Integer downCount) {
        this.downCount = downCount;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    @Override
    public String toString() {
        return "Courseware{" +
                "id=" + id +
                ", cId='" + cId + '\'' +
                ", cwId='" + cwId + '\'' +
                ", cwName='" + cwName + '\'' +
                ", url='" + url + '\'' +
                ", viewCount=" + viewCount +
                ", downCount=" + downCount +
                ", uploadTime=" + uploadTime +
                '}';
    }
}
