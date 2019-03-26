package com.nju.app.teacher.controller;

import com.nju.app.dao.CourseDao;
import com.nju.app.dao.TeachCourseRecordDao;
import com.nju.app.entities.Course;
import com.nju.app.entities.Result;
import com.nju.app.entities.TeachCourseRecord;
import com.nju.app.utils.GetUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
public class TeachCourseController {

    @GetMapping("/teachcourse")
    public String index(){
        return "teacher/teachcourse";
    }

    @Autowired
    TeachCourseRecordDao teachCourseRecordDao;

    @Autowired
    CourseDao courseDao;

    @ResponseBody
    @RequestMapping("/teachcourse/findAllCourses")
    public List<Course> findAllCourses(@RequestParam("tId") String tId){

        List<TeachCourseRecord> teachCourseRecords = teachCourseRecordDao.findByTId(tId);
        List<Course> courses = new ArrayList<>();

        for (TeachCourseRecord teachCourseRecord: teachCourseRecords){
            if (courseDao.findByCId(teachCourseRecord.getcId()) != null){
                courses.add(courseDao.findByCId(teachCourseRecord.getcId()));
            }
        }

        return courses;
    }

    @ResponseBody
    @RequestMapping("/teachcourse/findByCId")
    public Course findByCId(@RequestParam("cId") String cId){

        Course course = courseDao.findByCId(cId);

        return course;
    }

    @ResponseBody
    @RequestMapping("/teachcourse/create")
    public Result create(@RequestParam("tId") String tId,
                         @RequestParam("cName") String cName,
                         @RequestParam("start") String start,
                         @RequestParam("end") String end,
                         @RequestParam("token") String token){

        Course course = new Course();

        String cId = tId + GetUUID.getUUID();
        course.setcId(cId);
        course.setcName(cName);
        course.setStart(Date.valueOf(start));
        course.setEnd(Date.valueOf(end));
        course.setToken(token);

        TeachCourseRecord teachCourseRecord = new TeachCourseRecord();
        teachCourseRecord.settId(tId);
        teachCourseRecord.setcId(cId);

        try{
            courseDao.saveAndFlush(course);
            teachCourseRecordDao.saveAndFlush(teachCourseRecord);
            return new Result(true, "创建成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    @ResponseBody
    @RequestMapping("/teachcourse/update")
    public Result update(@RequestParam("cId") String cId,
                         @RequestParam("cName") String cName,
                         @RequestParam("token") String token){

        Course course = courseDao.findByCId(cId);

        course.setcName(cName);
        course.setToken(token);

        try{
            courseDao.saveAndFlush(course);
            return new Result(true, "更新数据成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    @ResponseBody
    @RequestMapping("/teachcourse/delete")
    public Result delete(@RequestParam("cId") String cId){

        try{
            courseDao.deleteByCId(cId);
            return new Result(true, "更新数据成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

}
