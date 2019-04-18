package com.nju.app.student.service;

import com.nju.app.dao.AttendLessonRecordDao;
import com.nju.app.dao.LessonDao;
import com.nju.app.dao.LessonLocationDao;
import com.nju.app.entities.AttendLessonRecord;
import com.nju.app.entities.Lesson;
import com.nju.app.entities.LessonLocation;
import com.nju.app.entities.Result;
import com.nju.app.utils.GetUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class LocationSignOnService {

    @Autowired
    LessonDao lessonDao;

    @Autowired
    LessonLocationDao lessonLocationDao;

    @Autowired
    AttendLessonRecordDao attendLessonRecordDao;

    public List<Lesson> findLocationSignOnList(String cId){

        Date date = new Date();

        List<Lesson> lessons = lessonDao.findByCId(cId);

        List<Lesson> signOn_list = new LinkedList<>();
        for (Lesson lesson : lessons){

            if ((lesson.getStart().getTime() <= date.getTime()) && (lesson.getEnd().getTime() > date.getTime())){
                if (lessonLocationDao.findByLId(lesson.getlId()).getLatitude().length() > 0 && lessonLocationDao.findByLId(lesson.getlId()).getLongitude().length() > 0){
                    signOn_list.add(lesson);
                }
            }
        }

        return signOn_list;
    }

    public LessonLocation findLessonLocation(String lId){
        return lessonLocationDao.findByLId(lId);
    }

    public Result locationSignOn(String sId, String lId){

        Lesson lesson = lessonDao.findByLId(lId);
        AttendLessonRecord attendLessonRecord = attendLessonRecordDao.findBySIdAndLId(sId, lId);

        if (attendLessonRecord == null || !attendLessonRecord.getsId().equals(sId)){
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
            return new Result(false, "已签到");
        }

    }


}
