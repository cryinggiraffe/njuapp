package com.nju.app.student.entities;

public class StudentSelectCourse {

    private String sId;

    private String cId;
    private String cName;

    private Integer isSelected;

    public StudentSelectCourse() {
    }

    public StudentSelectCourse(String sId, String cId, String cName, Integer isSelected) {
        this.sId = sId;
        this.cId = cId;
        this.cName = cName;
        this.isSelected = isSelected;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
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

    public Integer getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Integer isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public String toString() {
        return "StudentSelectCourse{" +
                "sId='" + sId + '\'' +
                ", cId='" + cId + '\'' +
                ", cName='" + cName + '\'' +
                ", isSelected='" + isSelected + '\'' +
                '}';
    }
}
