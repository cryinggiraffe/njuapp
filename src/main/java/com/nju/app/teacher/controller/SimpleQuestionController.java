package com.nju.app.teacher.controller;

import com.nju.app.dao.SimpleQuestionDao;
import com.nju.app.entities.Result;
import com.nju.app.entities.SimpleQuestion;
import com.nju.app.utils.GetUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SimpleQuestionController {

    @GetMapping("/simplequestion")
    public String index(){
        return "teacher/simplequestion";
    }

    @Autowired
    SimpleQuestionDao simpleQuestionDao;

    @ResponseBody
    @RequestMapping("/simplequestion/findAllSimpleQuestions")
    public List<SimpleQuestion> findAllSimpleQuestions(@RequestParam("cId") String cId){

        return simpleQuestionDao.findByCId(cId);
    }

    @ResponseBody
    @RequestMapping("/simplequestion/findBySqId")
    public SimpleQuestion findBySqId(@RequestParam("sqId") String sqId){

        return simpleQuestionDao.findBySqId(sqId);
    }


    @ResponseBody
    @RequestMapping("/simplequestion/create")
    public Result create(@RequestParam("cId") String cId,
                         @RequestParam("sqContent") String sqContent,
                         @RequestParam("sqCorrectAnswer") String sqCorrectAnswer){

        SimpleQuestion simpleQuestion = new SimpleQuestion();

        String sqId = cId + GetUUID.getUUID();
        simpleQuestion.setcId(cId);
        simpleQuestion.setSqId(sqId);
        simpleQuestion.setSqContent(sqContent);
        simpleQuestion.setSqCorrectAnswer(sqCorrectAnswer);

        try{
            simpleQuestionDao.saveAndFlush(simpleQuestion);
            return new Result(true, "创建成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    @ResponseBody
    @RequestMapping("/simplequestion/update")
    public Result update(@RequestParam("sqId") String sqId,
                         @RequestParam("sqContent") String sqContent,
                         @RequestParam("sqCorrectAnswer") String sqCorrectAnswer){

        SimpleQuestion simpleQuestion = simpleQuestionDao.findBySqId(sqId);

        simpleQuestion.setSqContent(sqContent);
        simpleQuestion.setSqCorrectAnswer(sqCorrectAnswer);

        try{
            simpleQuestionDao.saveAndFlush(simpleQuestion);
            return new Result(true, "创建成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    @ResponseBody
    @RequestMapping("/simplequestion/delete")
    public Result delete(@RequestParam("sqId") String sqId){

        try{
            simpleQuestionDao.deleteBySqId(sqId);
            return new Result(true, "更新数据成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }

    }
}
