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
import java.util.List;

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
                         @RequestParam("longitude") String longitude,
                         @RequestParam("radius") String radius){

        Date now = new Date();
        List<Lesson> all = lessonDao.findByCId(cId);
        boolean first = true;
        Lesson lesson = new Lesson();
        LessonLocation lessonLocation = new LessonLocation();

        if (all != null){
            for (int i = 0; i < all.size(); i++){
                if ((all.get(i).getStart().getTime() <= now.getTime()) && (all.get(i).getEnd().getTime() > now.getTime())){
                    if (lessonLocationDao.findByLId(lesson.getlId()).getLatitude().length() > 0){
                        lesson = all.get(i);
                        lessonLocation = lessonLocationDao.findByLId(all.get(i).getlId());
                        first = false;
                        break;
                    }
                }
            }
        }

        if (first){
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
            lessonLocation.setRadius(radius);
        }else {
            lessonLocation.setRadius(radius);
        }

        System.out.println(latitude + "  " + longitude);


        try{
            lesson.setlTime(new Date(lTime));

            Date date = new Date();
            lesson.setStart(date);
            lesson.setEnd(new Date(date.getTime() + 600000*6));
            System.out.println(lesson);
            lessonDao.saveAndFlush(lesson);
            lessonLocationDao.saveAndFlush(lessonLocation);
            if (first){
                return new Result(true, "开始签到成功");
            }else {
                return new Result(true, "修改成功");
            }

        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }

    }
}
