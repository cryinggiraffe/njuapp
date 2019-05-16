package com.nju.app.student.controller;

import com.google.gson.Gson;
import com.nju.app.entities.ChoiceQuestion;
import com.nju.app.entities.Homework;
import com.nju.app.entities.Result;
import com.nju.app.entities.SimpleQuestion;
import com.nju.app.student.entities.ChoiceQuestionStatistic;
import com.nju.app.student.entities.QuestionContent;
import com.nju.app.student.service.StudentHomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class StudentHomeworkController {

    @Autowired
    StudentHomeworkService studentHomeworkService;

    @ResponseBody
    @RequestMapping("/question/findHomework")
    public List<Homework> getHomeworkByCId(@RequestParam("cId") String cId){
        return studentHomeworkService.getHomeworkByCId(cId);
    }

    @ResponseBody
    @RequestMapping("/question/findQuestion")
    public QuestionContent getChoiceQuestionByHId(@RequestParam("hId") String hId){
        String type = studentHomeworkService.getHomeworkTypeByHId(hId);
        List<ChoiceQuestion> choiceQuestions = new LinkedList<>();
        //CHANGE
        SimpleQuestion simpleQuestion = new SimpleQuestion();
        QuestionContent questionContent = new QuestionContent();

        if (type.equals("cq")){
            choiceQuestions = studentHomeworkService.getChoiceQuestionByHId(hId);
        }else {
            simpleQuestion = studentHomeworkService.getSimpleQuestionByHId(hId);
        }
        questionContent.setType(type);
        questionContent.setChoiceQuestions(choiceQuestions);
        questionContent.setSimpleQuestion(simpleQuestion);
        return questionContent;
    }

    @ResponseBody
    @RequestMapping("/question/submitSimpleQuestion")
    public Result answerSimpleQuestion(@RequestParam("hId") String hId,
                                       @RequestParam("sId") String sId,
                                       @RequestParam("sqId")String sqId,
                                       @RequestParam("sqResult")String sqResult){
        if (studentHomeworkService.isSimpleSubmit(hId, sId)){
            return new Result(false, "已经提交！");
        }

        try {
            studentHomeworkService.submitSimpleRe(hId, sId, sqId, sqResult);
        } catch (Exception e){
            return new Result(false, "未知错误");
        }

        return new Result(true, "提交成功");


    }

    @ResponseBody
    @RequestMapping("/question/submitChoiceQuestion")
    public Result seleceChoiceQuestion(@RequestParam("hId") String hId,
                                        @RequestParam("sId") String sId,
                                        @RequestParam("myMap") String result){

        if (studentHomeworkService.isSubmit(hId, sId)) {
            return new Result(false, "已经提交！");
        }

        Gson gson = new Gson();
        Map<String, String> resultMap = new LinkedHashMap<>();
        resultMap = gson.fromJson(result, resultMap.getClass());
        System.out.println(resultMap);

            try {
                for(String cqId : resultMap.keySet()){
					for(int i = 0; i < resultMap.get(cqId).length(); i++) {
						studentHomeworkService.seleceChoiceQuestion(hId, sId, cqId, resultMap.get(cqId).substring(i, i+1));
					}                  
                }
            } catch (Exception e) {
                return new Result(false, "未知错误");
            }

            return new Result(true, "提交成功");


    }
	
    @ResponseBody
    @RequestMapping("/question/findChoiceQuestionStatistic")
    public ChoiceQuestionStatistic getChoiceQuestionStatistic(@RequestParam("hId") String hId) {
        return studentHomeworkService.getChoiceQuestionStatistic(hId);
    }
}
