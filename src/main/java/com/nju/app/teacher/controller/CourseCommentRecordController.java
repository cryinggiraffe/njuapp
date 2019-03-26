package com.nju.app.teacher.controller;

import com.nju.app.dao.CourseCommentRecordDao;
import com.nju.app.dao.StudentDao;
import com.nju.app.entities.Course;
import com.nju.app.entities.CourseCommentRecord;
import com.nju.app.entities.Result;
import com.nju.app.utils.GetUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.List;

@Controller
public class CourseCommentRecordController {

    @GetMapping("/coursecommentrecord")
    public String index(){
        return "teacher/coursecommentrecord";
    }

    @Autowired
    CourseCommentRecordDao courseCommentRecordDao;

    @Autowired
    StudentDao studentDao;

    @ResponseBody
    @RequestMapping("/coursecommentrecord/findAllCourseCommentRecords")
    public List<CourseCommentRecord> findAllCourseCommentRecords(@RequestParam("cId") String cId){

        List<CourseCommentRecord> courseCommentRecords = courseCommentRecordDao.findByCId(cId);

        return courseCommentRecords;
    }

    @ResponseBody
    @RequestMapping("/coursecommentrecord/findByCcId")
    public CourseCommentRecord findByCcId(@RequestParam("ccId") String ccId){
        return courseCommentRecordDao.findByCcId(ccId);
    }

    @ResponseBody
    @RequestMapping("/coursecommentrecord/create")
    public Result create(@RequestParam("cId") String cId,
                         @RequestParam("sId") String sId,
                         @RequestParam("comment") String comment){

        CourseCommentRecord courseCommentRecord = new CourseCommentRecord();

        String ccId = sId + cId + GetUUID.getUUID();
        courseCommentRecord.setCcId(ccId);
        courseCommentRecord.setcId(cId);
        courseCommentRecord.setsId(sId);
        courseCommentRecord.setsName(studentDao.findBySId(sId).getsName());
        courseCommentRecord.setComment(comment);
        courseCommentRecord.setcTime(new Date(new java.util.Date().getTime()));

        try{
            courseCommentRecordDao.saveAndFlush(courseCommentRecord);
            return new Result(true, "创建成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    @ResponseBody
    @RequestMapping("/coursecommentrecord/update")
    public Result update(@RequestParam("cId") String cId,
                         @RequestParam("sId") String sId,
                         @RequestParam("comment") String comment){

        CourseCommentRecord courseCommentRecord = courseCommentRecordDao.findBySIdAndCId(sId,cId);

        courseCommentRecord.setComment(comment);
        try{
            courseCommentRecordDao.saveAndFlush(courseCommentRecord);
            return new Result(true, "更新数据成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    @ResponseBody
    @RequestMapping("/coursecommentrecord/delete")
    public Result delete(@RequestParam("cId") String cId,
                         @RequestParam("sId") String sId){

        try{
            courseCommentRecordDao.deleteBySIdAndCId(sId,cId);
            return new Result(true, "更新数据成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }
}
