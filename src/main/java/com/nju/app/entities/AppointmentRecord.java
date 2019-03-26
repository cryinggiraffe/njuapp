package com.nju.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "appointment_record")
//预约记录
public class AppointmentRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String arId;
    private String aId;
    private String sId;

    private Integer isAppointed;

    public AppointmentRecord() {
    }

    public AppointmentRecord(String arId, String aId, String sId, Integer isAppointed) {
        this.arId = arId;
        this.aId = aId;
        this.sId = sId;
        this.isAppointed = isAppointed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArId() {
        return arId;
    }

    public void setArId(String arId) {
        this.arId = arId;
    }

    public String getaId() {
        return aId;
    }

    public void setaId(String aId) {
        this.aId = aId;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public Integer getIsAppointed() {
        return isAppointed;
    }

    public void setIsAppointed(Integer isAppointed) {
        this.isAppointed = isAppointed;
    }

    @Override
    public String toString() {
        return "AppointmentRecord{" +
                "id=" + id +
                ", arId='" + arId + '\'' +
                ", aId='" + aId + '\'' +
                ", sId='" + sId + '\'' +
                ", isAppointed=" + isAppointed +
                '}';
    }
}
