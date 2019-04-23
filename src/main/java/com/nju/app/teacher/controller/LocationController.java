package com.nju.app.teacher.controller;

import com.nju.app.dao.CourseDao;
import com.nju.app.dao.LessonDao;
import com.nju.app.dao.LessonLocationDao;
import com.nju.app.entities.Lesson;
import com.nju.app.entities.LessonLocation;
import com.nju.app.entities.Result;
import com.nju.app.utils.GetUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class LocationController {

    @Autowired
    LessonDao lessonDao;

    @Autowired
    LessonLocationDao lessonLocationDao;

    @Autowired
    CourseDao courseDao;

    @ResponseBody
    @RequestMapping("/lessonLocation/create")
    public Result create(@RequestParam("cId") String cId,
                         @RequestParam("lTime") Long lTime,
                         @RequestParam("latitude") String latitude,
                         @RequestParam("longitude") String longitude){

        Lesson lesson = new Lesson();
        LessonLocation lessonLocation = new LessonLocation();

        String lId = cId + GetUUID.getUUID();
        String lName = courseDao.findByCId(cId).getcName();
        lesson.setcId(cId);
        lesson.setlId(lId);
        lesson.setlName(lName);
        lesson.setActualNumber(0);
        lesson.setlLength(50);

        lessonLocation.setcId(cId);
        lessonLocation.setlId(lId);
        lessonLocation.setlName(lName);
        lessonLocation.setLatitude(latitude);
        lessonLocation.setLongitude(longitude);

        System.out.println(latitude + "  " + longitude);


        try{
            lesson.setlTime(new Date(lTime));

            Date date = new Date();
            lesson.setStart(date);
            lesson.setEnd(new Date(date.getTime() + 600000*6));
            System.out.println(lesson);
            lessonDao.saveAndFlush(lesson);
            lessonLocationDao.saveAndFlush(lessonLocation);
            return new Result(true, "创建成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }

    }
}
