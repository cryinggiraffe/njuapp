package com.nju.app.student.service;

import com.nju.app.dao.*;
import com.nju.app.entities.*;
import com.nju.app.student.entities.ChoiceQuestionStatistic;
import com.nju.app.student.entities.StatisticContent;
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

    @Autowired
    SimpleQuestionDao simpleQuestionDao;

    @Autowired
    SimpleQuestionRecordDao simpleQuestionRecordDao;

    public String getHomeworkTypeByHId(String hId) {
        List<HomeworkQuestionRecord> questionRecords = homeworkQuestionRecordDao.findByHId(hId);
        if (questionRecords.get(0).getCqId() == null){
            return "sq";
        }
        return "cq";
    }

    public SimpleQuestion getSimpleQuestionByHId(String hId){
        List<HomeworkQuestionRecord> questionRecords = homeworkQuestionRecordDao.findByHId(hId);
        SimpleQuestion result = new SimpleQuestion();
        if (questionRecords.size() == 0 || questionRecords == null){
            return result;
        }
        return simpleQuestionDao.findBySqId(questionRecords.get(0).getSqId());
    }

    public boolean isSimpleSubmit(String hId, String sId){
        List<SimpleQuestionRecord> simpleQuestionRecords = simpleQuestionRecordDao.findBySIdAndHId(sId, hId);

        if (simpleQuestionRecords.size() > 0){
            return true;
        }

        return false;
    }

    public Result submitSimpleRe(String hId, String sId, String sqId, String sqResult){
        SimpleQuestionRecord simpleQuestionRecord = new SimpleQuestionRecord();
        simpleQuestionRecord.sethId(hId);
        simpleQuestionRecord.setsId(sId);
        simpleQuestionRecord.setSqId(sqId);
        simpleQuestionRecord.setSqResult(sqResult);

        try {
            simpleQuestionRecordDao.saveAndFlush(simpleQuestionRecord);
            return new Result(true, "提交成功");
        }catch (Exception e){
            return new Result(false, "发生未知错误");
        }
    }

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
	
    public ChoiceQuestionStatistic getChoiceQuestionStatistic(String hId) {
        //得到所有questions
        List<HomeworkQuestionRecord> questionRecords = homeworkQuestionRecordDao.findByHId(hId);
        List<ChoiceQuestionRecord> choiceQuestionRecords = choiceQuestionRecordDao.findByHId(hId);
        List<String> questions = new LinkedList<>();
		List<String> quest = new LinkedList<>();
		int i = 1;
        for (HomeworkQuestionRecord h : questionRecords){
            questions.add(h.getCqId());
			quest.add("题目" + i);
			i++;
        }
		i = 1;

        List<StatisticContent> scs = new LinkedList<>();
        StatisticContent sA = new StatisticContent();
        StatisticContent sB = new StatisticContent();
        StatisticContent sC = new StatisticContent();
        StatisticContent sD = new StatisticContent();
        List<Integer> dA = new LinkedList<>();
        List<Integer> dB = new LinkedList<>();
        List<Integer> dC = new LinkedList<>();
        List<Integer> dD = new LinkedList<>();
        sA.setName("A");
        sA.setCategory("bar");
        sB.setName("B");
        sB.setCategory("bar");
        sC.setName("C");
        sC.setCategory("bar");
        sD.setName("D");
        sD.setCategory("bar");
        int A = 0;
        int B = 0;
        int C = 0;
        int D = 0;

        for (String que : questions){
            for (ChoiceQuestionRecord cqr : choiceQuestionRecords){
                if (que.equals(cqr.getCqId())){
                    if (cqr.getCqSelect().equals("A")){
                        A++;
                    }
                    if (cqr.getCqSelect().equals("B")){
                        B++;
                    }
                    if (cqr.getCqSelect().equals("C")){
                        C++;
                    }
                    if (cqr.getCqSelect().equals("D")){
                        D++;
                    }
                }
            }
            dA.add(A);
            dB.add(B);
            dC.add(C);
            dD.add(D);
            A = 0;
            B = 0;
            C = 0;
            D = 0;
        }

        sA.setData(dA);
        sB.setData(dB);
        sC.setData(dC);
        sD.setData(dD);

        scs.add(sA);
        scs.add(sB);
        scs.add(sC);
        scs.add(sD);
        ChoiceQuestionStatistic result = new ChoiceQuestionStatistic();
        result.setCquestion(quest);
        result.setStatisticContents(scs);

        System.out.println(result);
        return result;

    }

}
