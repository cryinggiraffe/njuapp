package com.nju.app.student.entities;

public class CourseScoreAndReview{

    private Integer grade;

    private String result;

    public CourseScoreAndReview() {
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "CourseScoreAndReview{" +
                "grade=" + grade +
                ", result='" + result + '\'' +
                '}';
    }
}
