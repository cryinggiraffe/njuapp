package com.nju.app.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String vId;
    private String vName;
    private String url;

    private Integer count;
    private String cId;

    private Date uploadTime;

    public Video() {
    }

    public Video(String vId, String vName, String url, Integer count, String cId, Date uploadTime) {
        this.vId = vId;
        this.vName = vName;
        this.url = url;
        this.count = count;
        this.cId = cId;
        this.uploadTime = uploadTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getvId() {
        return vId;
    }

    public void setvId(String vId) {
        this.vId = vId;
    }

    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", vId='" + vId + '\'' +
                ", vName='" + vName + '\'' +
                ", url='" + url + '\'' +
                ", count=" + count +
                ", cId='" + cId + '\'' +
                ", uploadTime=" + uploadTime +
                '}';
    }
}
