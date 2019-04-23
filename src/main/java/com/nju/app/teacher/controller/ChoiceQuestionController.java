package com.nju.app.teacher.controller;

import com.nju.app.dao.ChoiceQuestionDao;
import com.nju.app.entities.ChoiceQuestion;
import com.nju.app.entities.Result;
import com.nju.app.utils.GetUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ChoiceQuestionController {

    @GetMapping("/choicequestion")
    public String index(){
        return "teacher/choicequestion";
    }

    @Autowired
    ChoiceQuestionDao choiceQuestionDao;

    @ResponseBody
    @RequestMapping("/choicequestion/findAllChoiceQuestions")
    public List<ChoiceQuestion> findAllChoiceQuestions(@RequestParam("cId") String cId){
        return choiceQuestionDao.findByCId(cId);
    }

    @ResponseBody
    @RequestMapping("/choicequestion/findByCqId")
    public ChoiceQuestion findByCqId(@RequestParam("cqId") String cqId){

        return choiceQuestionDao.findByCqId(cqId);

    }

    @ResponseBody
    @RequestMapping("/choicequestion/create")
    public Result create(@RequestParam("cId") String cId,
                         @RequestParam("cqContent") String cqContent,
                         @RequestParam("optionA") String optionA,
                         @RequestParam("optionB") String optionB,
                         @RequestParam("optionC") String optionC,
                         @RequestParam("optionD") String optionD,
                         @RequestParam("cqCorrectAnswer") String cqCorrectAnswer){

        ChoiceQuestion choiceQuestion = new ChoiceQuestion();

        String cqId = cId + GetUUID.getUUID();
        choiceQuestion.setcId(cId);
        choiceQuestion.setCqId(cqId);
        choiceQuestion.setCqContent(cqContent);
        choiceQuestion.setOptionA(optionA);
        choiceQuestion.setOptionB(optionB);
        choiceQuestion.setOptionC(optionC);
        choiceQuestion.setOptionD(optionD);
        choiceQuestion.setCqCorrectAnswer(cqCorrectAnswer);

        try {
            choiceQuestionDao.saveAndFlush(choiceQuestion);
            return new Result(true, "创建成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }


    @ResponseBody
    @RequestMapping("/choicequestion/update")
    public Result update(@RequestParam("cqId") String cqId,
                         @RequestParam("cqContent") String cqContent,
                         @RequestParam("optionA") String optionA,
                         @RequestParam("optionB") String optionB,
                         @RequestParam("optionC") String optionC,
                         @RequestParam("optionD") String optionD,
                         @RequestParam("cqCorrectAnswer") String cqCorrectAnswer){

        ChoiceQuestion choiceQuestion = choiceQuestionDao.findByCqId(cqId);

        choiceQuestion.setCqContent(cqContent);
        choiceQuestion.setOptionA(optionA);
        choiceQuestion.setOptionB(optionB);
        choiceQuestion.setOptionC(optionC);
        choiceQuestion.setOptionD(optionD);
        choiceQuestion.setCqCorrectAnswer(cqCorrectAnswer);

        try {
            choiceQuestionDao.saveAndFlush(choiceQuestion);
            return new Result(true, "更新数据成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }


    @ResponseBody
    @RequestMapping("/choicequestion/delete")
    public Result delete(@RequestParam("cqId") String cqId){

        try {
            choiceQuestionDao.deleteByCqId(cqId);
            return new Result(true, "更新数据成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }
}
