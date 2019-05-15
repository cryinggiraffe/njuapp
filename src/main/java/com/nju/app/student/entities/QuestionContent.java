package com.nju.app.student.entities;

import com.nju.app.entities.ChoiceQuestion;
import com.nju.app.entities.SimpleQuestion;

import java.util.List;

public class QuestionContent {

    //问题类型
    private String type;

    //选择题
    private List<ChoiceQuestion> choiceQuestions;

    //问答题
    private SimpleQuestion simpleQuestion;

    public QuestionContent() {
    }

    public QuestionContent(String type, List<ChoiceQuestion> choiceQuestions, SimpleQuestion simpleQuestion) {
        this.type = type;
        this.choiceQuestions = choiceQuestions;
        this.simpleQuestion = simpleQuestion;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ChoiceQuestion> getChoiceQuestions() {
        return choiceQuestions;
    }

    public void setChoiceQuestions(List<ChoiceQuestion> choiceQuestions) {
        this.choiceQuestions = choiceQuestions;
    }

    public SimpleQuestion getSimpleQuestion() {
        return simpleQuestion;
    }

    public void setSimpleQuestion(SimpleQuestion simpleQuestion) {
        this.simpleQuestion = simpleQuestion;
    }

    @Override
    public String toString() {
        return "QuestionContent{" +
                "type='" + type + '\'' +
                ", choiceQuestions=" + choiceQuestions +
                ", simpleQuestion=" + simpleQuestion +
                '}';
    }
}
