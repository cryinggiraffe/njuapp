package com.nju.app.teacher.controller;

import com.nju.app.dao.*;
import com.nju.app.entities.*;
import com.nju.app.utils.GetUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    @Autowired
    SimpleQuestionController simpleQuestionController;

    @ResponseBody
    @RequestMapping(value = "/getAllCourseQuestions")
    public List<ChoiceQuestion> getQuestionList(@RequestParam(value = "c_id") String cId){
        List<ChoiceQuestion> list = choiceQuestionController.findAllChoiceQuestions(cId);
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/getAllSimpleQuestions")
    public List<SimpleQuestion> getSimpleQuestionList(@RequestParam(value = "c_id") String cId){
        List<SimpleQuestion> list = simpleQuestionController.findAllSimpleQuestions(cId);
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/getHomeworkHistory")
    public List<Homework> getHomeworkHistory(@RequestParam(value = "c_id")String cId){
        List<Homework> list = homeworkDao.findByCId(cId);
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/getChoiceQuestions")
    public String getQuestions(@RequestParam(value = "h_id") String hId){
        List<HomeworkQuestionRecord> recordList = homeworkQuestionRecordDao.findByHId(hId);
        List<String> questionList =  new ArrayList<String>();
        for(int j=0;j<recordList.size();j++){
            questionList.add(recordList.get(j).getCqId());
        }
        return String.join(",",questionList);
    }


    @ResponseBody
    @RequestMapping(value = "/republishHomework")
    public Result republishHomework(@RequestParam(value = "c_id") String cId,
                                  @RequestParam(value = "h_id") String hId,
                                  @RequestParam(value = "h_title") String hTitle,
                                  @RequestParam(value = "release_time") String date,
                                    @RequestParam(value = "selected") String selected){
        Homework homework = new Homework();
        Homework temp = new Homework();
        List<Homework> list = homeworkDao.findByCId(cId);

        for(int i=0;i<list.size();i++){
            temp = list.get(i);

            if(temp.gethId().equals(hId)){
                homework = temp;
                homework.setcId(cId);
                homework.sethId(hId);
                homework.sethTitle(hTitle);
                homework.setReleasetime(strToDate(date));
                homeworkDao.save(homework);

                List<HomeworkQuestionRecord> records = homeworkQuestionRecordDao.findByHId(hId);
                for(int l=0;l<records.size();l++){
                    homeworkQuestionRecordDao.delete(records.get(l));
                }

                String[] sqId = selected.split("/");
                for(int j = 0;j<sqId.length;j++){
                    HomeworkQuestionRecord homeworkQuestionRecord = new HomeworkQuestionRecord();
                    homeworkQuestionRecord.setCqId(String.valueOf(sqId[j]));
                    homeworkQuestionRecord.sethId(hId);
                    homeworkQuestionRecordDao.save(homeworkQuestionRecord);
                }

                return new Result(true,"更新成功");
            }
        }
        return new Result(false,"更新失败");
    }

    @ResponseBody
    @RequestMapping(value = "/publishHomework")
    public Result publishHomework(@RequestParam(value = "c_id") String cId,
                                  @RequestParam(value = "h_title") String hTitle,
                                  @RequestParam(value = "release_time") String date,
                                  @RequestParam(value = "selected") String selected,
                                  @RequestParam(value = "selectedS") String selectedS){
        String hId = cId + GetUUID.getUUID();
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

        if(selected.length()>0){
            String[] sqId = selected.split("/");
            for(int i = 0;i<sqId.length;i++){
                HomeworkQuestionRecord homeworkQuestionRecord = new HomeworkQuestionRecord();
                homeworkQuestionRecord.setCqId(String.valueOf(sqId[i]));
                homeworkQuestionRecord.sethId(hId);
                homeworkQuestionRecordDao.save(homeworkQuestionRecord);
            }
        }

        if(selectedS.length()>0){
            String[] sqIdS = selectedS.split("/");
            for(int j = 0;j<sqIdS.length;j++){
                HomeworkQuestionRecord homeworkQuestionRecord = new HomeworkQuestionRecord();
                homeworkQuestionRecord.setSqId(String.valueOf(sqIdS[j]));
                homeworkQuestionRecord.sethId(hId);
                homeworkQuestionRecordDao.save(homeworkQuestionRecord);
            }
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
