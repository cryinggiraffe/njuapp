package com.nju.app.student.entities;

import java.util.List;

public class ChoiceQuestionStatistic {

    private List<String> cquestion;


    private List<StatisticContent> statisticContents;

    public ChoiceQuestionStatistic() {
    }

    public ChoiceQuestionStatistic(List<String> cquestion, List<StatisticContent> statisticContents) {
        this.cquestion = cquestion;
        this.statisticContents = statisticContents;
    }

    public List<String> getCquestion() {
        return cquestion;
    }

    public void setCquestion(List<String> cquestion) {
        this.cquestion = cquestion;
    }

    public List<StatisticContent> getStatisticContents() {
        return statisticContents;
    }

    public void setStatisticContents(List<StatisticContent> statisticContents) {
        this.statisticContents = statisticContents;
    }

    @Override
    public String toString() {
        return "ChoiceQuestionStatistic{" +
                "cquestion=" + cquestion +
                ", statisticContents=" + statisticContents +
                '}';
    }
}
