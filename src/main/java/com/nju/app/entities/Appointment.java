package com.nju.app.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String aId;
    private String tId;

    private Date start;
    private Date end;

    private Integer isBooked;

    public Appointment() {
    }

    public Appointment(String aId, String tId, Date start, Date end, Integer isBooked) {
        this.aId = aId;
        this.tId = tId;
        this.start = start;
        this.end = end;
        this.isBooked = isBooked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getaId() {
        return aId;
    }

    public void setaId(String aId) {
        this.aId = aId;
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
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

    public Integer getIsBooked() {
        return isBooked;
    }

    public void setIsBooked(Integer isBooked) {
        this.isBooked = isBooked;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", aId='" + aId + '\'' +
                ", tId='" + tId + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", isBooked=" + isBooked +
                '}';
    }
}
