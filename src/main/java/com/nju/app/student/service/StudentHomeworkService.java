package com.nju.app.student.service;

import com.nju.app.dao.ChoiceQuestionDao;
import com.nju.app.dao.ChoiceQuestionRecordDao;
import com.nju.app.dao.HomeworkDao;
import com.nju.app.dao.HomeworkQuestionRecordDao;
import com.nju.app.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class StudentHomeworkService {

    @Autowired
    HomeworkDao homeworkDao;

    @Autowired
    HomeworkQuestionRecordDao homeworkQuestionRecordDao;

    @Autowired
    ChoiceQuestionDao choiceQuestionDao;

    @Autowired
    ChoiceQuestionRecordDao choiceQuestionRecordDao;

    public List<Homework> getHomeworkByCId(String cId){
//        System.out.println(homeworkDao.findByCId(cId));
        return homeworkDao.findByCId(cId);
    }

    public List<ChoiceQuestion> getChoiceQuestionByHId(String hId){
        List<HomeworkQuestionRecord> questionRecords = homeworkQuestionRecordDao.findByHId(hId);
        List<ChoiceQuestion> result = new LinkedList<>();
        if (questionRecords.size() == 0 || questionRecords == null){
            return result;
        }
        for (HomeworkQuestionRecord homeworkQuestionRecord : questionRecords){
            ChoiceQuestion choiceQuestion = choiceQuestionDao.findByCqId(homeworkQuestionRecord.getCqId());
            result.add(choiceQuestion);
        }
//        System.out.println(result);
        return result;

    }

    public boolean isSubmit(String hId, String sId) {
        List<ChoiceQuestionRecord> choiceQuestionRecords = choiceQuestionRecordDao.findBySIdAndHId(sId, hId);
//        System.out.println("查询结果：  " + choiceQuestionRecords + "  " + choiceQuestionRecords.size());

        if (choiceQuestionRecords.size() > 0 ){
            return true;
        }

        return false;
    }


    public Result seleceChoiceQuestion(String hId, String sId, String cqId, String cqSelect){

        ChoiceQuestionRecord choiceQuestionRecord = new ChoiceQuestionRecord();
        choiceQuestionRecord.sethId(hId);
        choiceQuestionRecord.setCqId(cqId);
        choiceQuestionRecord.setsId(sId);
        choiceQuestionRecord.setCqSelect(cqSelect);

        try {
            choiceQuestionRecordDao.saveAndFlush(choiceQuestionRecord);
            return new Result(true, "提交成功");
        } catch (Exception e) {
            return new Result(false, "发生未知错误");
        }

    }

}
