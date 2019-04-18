package com.nju.app.student.service;

import com.nju.app.dao.AttendLessonRecordDao;
import com.nju.app.dao.LessonDao;
import com.nju.app.entities.AttendLessonRecord;
import com.nju.app.entities.Lesson;
import com.nju.app.entities.Result;
import com.nju.app.utils.GetUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SignOnService {

    @Autowired
    LessonDao lessonDao;

    @Autowired
    AttendLessonRecordDao attendLessonRecordDao;

    public List<Lesson> findAllSignOnList(){

        Date date = new Date();

        List<Lesson> lessons = lessonDao.findAll();

        List<Lesson> signOn_list = new ArrayList<>();
        for (Lesson lesson : lessons){

            if ((lesson.getStart().getTime() <= date.getTime()) && (lesson.getEnd().getTime() > date.getTime()) && lesson.getSignCode().length() > 0){
                signOn_list.add(lesson);
            }
        }

        return signOn_list;
    }

    public Result signOn(String sId, String lId, String signCode){

        Lesson lesson = lessonDao.findByLId(lId);

        if (lesson.getSignCode().equals(signCode)){

            AttendLessonRecord attendLessonRecord = attendLessonRecordDao.findBySIdAndLId(sId, lId);

            if (attendLessonRecord == null){

                attendLessonRecord = new AttendLessonRecord();

                String alId = lId + sId + GetUUID.getUUID();
                attendLessonRecord.setAlId(alId);
                attendLessonRecord.setlId(lId);
                attendLessonRecord.setsId(sId);
                attendLessonRecord.setIsAttended(1);

                int ActualNumber = attendLessonRecordDao.countByLIdAndIsAttended(lId,1) + 1;
                lesson.setActualNumber(ActualNumber);

                try {
                    attendLessonRecordDao.saveAndFlush(attendLessonRecord);
                    lessonDao.saveAndFlush(lesson);
                    return new Result(true, "创建成功");
                } catch (Exception e) {
                    e.printStackTrace();
                    return new Result(false, "发生未知错误");
                }
            }else {
                return new Result(true, "已签到");
            }
        }else {
            return new Result(false, "发生未知错误");
        }
    }
}
