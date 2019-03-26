package com.nju.app.teacher.controller;

import com.nju.app.dao.AttendLessonRecordDao;
import com.nju.app.dao.CourseDao;
import com.nju.app.dao.LessonDao;
import com.nju.app.dao.SelectCourseRecordDao;
import com.nju.app.entities.Lesson;
import com.nju.app.entities.Result;
import com.nju.app.utils.GetUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class LessonController {

    @GetMapping("/lesson")
    public String index(){
        return "teacher/lesson";
    }

    @Autowired
    LessonDao lessonDao;

    @Autowired
    CourseDao courseDao;

    @Autowired
    SelectCourseRecordDao selectCourseRecordDao;

    @Autowired
    AttendLessonRecordDao attendLessonRecordDao;

    @ResponseBody
    @RequestMapping("/lesson/findAllLessons")
    public List<Lesson> findAllLessons(@RequestParam("cId") String cId){
        return lessonDao.findByCId(cId);
    }

    @ResponseBody
    @RequestMapping("/lesson/findByLId")
    public Lesson findByLId(@RequestParam("lId") String lId){

        System.out.println(lessonDao.findByLId(lId));
        return lessonDao.findByLId(lId);
    }

    //应到人数（选课人数），页面初始刷新
    @ResponseBody
    @RequestMapping("/lesson/updateReachedNumber")
    public Result updateReachedNumber(@RequestParam("cId") String cId){

        List<Lesson> lessons = lessonDao.findByCId(cId);

        int ReachedNumber = selectCourseRecordDao.countByCIdAndIsSelected(cId,1);

        try{
            for (Lesson lesson : lessons){
                lesson.setReachedNumber(ReachedNumber);
                lessonDao.saveAndFlush(lesson);
            }
            return new Result(true, "更新数据成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }


    //实到人数
    @ResponseBody
    @RequestMapping("/lesson/updateActualNumber")
    public Result updateActualNumber(@RequestParam("lId") String lId){

        int ActualNumber = attendLessonRecordDao.countByLIdAndIsAttended(lId,1);

        Lesson lesson = lessonDao.findByLId(lId);
        lesson.setActualNumber(ActualNumber);

        try{
            lessonDao.saveAndFlush(lesson);
            return new Result(true, "更新数据成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }


    @ResponseBody
    @RequestMapping("/lesson/create")
    public Result create(@RequestParam("cId") String cId,
                         @RequestParam("lTime") Long lTime,
                         @RequestParam("signCode") String signCode){

        Lesson lesson = new Lesson();

        String lId = cId + GetUUID.getUUID();
        lesson.setcId(cId);
        lesson.setlId(lId);
        lesson.setlName(courseDao.findByCId(cId).getcName());
        lesson.setActualNumber(0);
        lesson.setlLength(50);
        lesson.setSignCode(signCode);

        //StringBuffer StrLTime = new StringBuffer(lTime);
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try{
            //lesson.setlTime(simpleDateFormat.parse(StrLTime.toString()));
            lesson.setlTime(new Date(lTime));

            Date date = new Date();
            lesson.setStart(date);
            lesson.setEnd(new Date(date.getTime() + 600000*6));
            System.out.println(lesson);
            lessonDao.saveAndFlush(lesson);
            return new Result(true, "创建成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }

    }



    @ResponseBody
    @RequestMapping("/lesson/update")
    public Result update(@RequestParam("lId") String lId,
                         @RequestParam("signCode") String signCode){

        Lesson lesson = lessonDao.findByLId(lId);

        lesson.setSignCode(signCode);

        try{
            lessonDao.saveAndFlush(lesson);
            return new Result(true, "更新数据成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }


}
