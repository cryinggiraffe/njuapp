package com.nju.app.teacher.controller;

import com.nju.app.dao.*;
import com.nju.app.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class HomeworkController {
    @Autowired
    HomeworkDao homeworkDao;

    @Autowired
    HomeworkQuestionRecordDao homeworkQuestionRecordDao;

    @Autowired
    ChoiceQuestionController choiceQuestionController;

    @ResponseBody
    @RequestMapping(value = "/getAllCourseQuestions")
    public List<ChoiceQuestion> getQuestionList(@RequestParam(value = "c_id") String cId){
        List<ChoiceQuestion> list = choiceQuestionController.findAllChoiceQuestions(cId);
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/getHomeworkHistory")
    public List<Homework> getHomeworkHistory(@RequestParam(value = "c_id")String cId){
        List<Homework> list = homeworkDao.findByCId(cId);
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/getHomeworkDetail")
    //获取作业中选择题的cqId
    public List<String> getHomeworkDetail(@RequestParam(value = "h_id")String hId){
        List<String> res = null;
        List<HomeworkQuestionRecord> list = homeworkQuestionRecordDao.findByHId(hId);
        for(int i=0;i<list.size();i++){
            res.add(list.get(i).getCqId());
        }
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/publishHomework")
    public Result publishHomework(@RequestParam(value = "c_id") String cId,
                                  @RequestParam(value = "h_id") String hId,
                                  @RequestParam(value = "h_title") String hTitle,
                                  @RequestParam(value = "release_time") String date,
                                  @RequestParam(value = "selected") String selected){
        Homework temp = new Homework();
        List<Homework> list = homeworkDao.findByCId(cId);
        for(int i=0;i<list.size();i++){
            temp = list.get(i);
            if(temp.gethId() == hId){
                return new Result(false,"homework_id重复");
            }
        }
        Homework homework = new Homework();
        homework.setcId(cId);
        homework.sethId(hId);
        homework.sethTitle(hTitle);
        homework.setReleasetime(strToDate(date));
        homeworkDao.saveAndFlush(homework);

        String[] sqId = selected.split("/");
        for(int i = 0;i<sqId.length;i++){
            HomeworkQuestionRecord homeworkQuestionRecord = new HomeworkQuestionRecord();
            homeworkQuestionRecord.setCqId(String.valueOf(sqId[i]));
            homeworkQuestionRecord.sethId(hId);
            homeworkQuestionRecordDao.save(homeworkQuestionRecord);
        }

        return new Result(true,homework.gethId());
    }

    public static java.sql.Date strToDate(String strDate) {
        String str = strDate;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Date date = new java.sql.Date(d.getTime());
        return date;
    }

}
