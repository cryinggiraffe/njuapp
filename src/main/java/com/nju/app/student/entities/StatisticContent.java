package com.nju.app.student.entities;

import java.util.List;

public class StatisticContent {

    private String name;

    private String category;

    private List<Integer> data;

    public StatisticContent() {
    }

    public StatisticContent(String name, String category, List<Integer> data) {
        this.name = name;
        this.category = category;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "StatisticContent{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", data=" + data +
                '}';
    }
}
