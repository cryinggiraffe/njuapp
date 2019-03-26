package com.nju.app.teacher.controller;

import com.nju.app.dao.LessonCommentRecordDao;
import com.nju.app.dao.StudentDao;
import com.nju.app.entities.LessonCommentRecord;
import com.nju.app.entities.Result;
import com.nju.app.utils.GetUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class LessonCommentRecordController {

    @GetMapping("/lessoncommentrecord")
    public String index(){

        return "/teacher/lessoncommentrecord";
    }

    @Autowired
    LessonCommentRecordDao lessonCommentRecordDao;

    @Autowired
    StudentDao studentDao;

    @ResponseBody
    @RequestMapping("/lessoncommentrecord/findAllLessonCommentRecords")
    public List<LessonCommentRecord> findAllLessonCommentRecords(@RequestParam("lId") String lId){
        return lessonCommentRecordDao.findByLId(lId);
    }

    @ResponseBody
    @RequestMapping("/lessoncommentrecord/findByLcId")
    public LessonCommentRecord findByLcId(@RequestParam("lcId") String lcId){

        return lessonCommentRecordDao.findByLcId(lcId);
    }

    @ResponseBody
    @RequestMapping("/lessoncommentrecord/create")
    public Result create(@RequestParam("lId") String lId,
                         @RequestParam("sId") String sId,
                         @RequestParam("comment") String comment){

        LessonCommentRecord lessonCommentRecord = new LessonCommentRecord();

        String lcId = lId + sId + GetUUID.getUUID();
        lessonCommentRecord.setLcId(lcId);
        lessonCommentRecord.setlId(lId);
        lessonCommentRecord.setsId(sId);
        lessonCommentRecord.setsName(studentDao.findBySId(sId).getsName());
        lessonCommentRecord.setComment(comment);

        java.util.Date date = new java.util.Date();

        lessonCommentRecord.setcTime(new Date(date.getTime()));
        try{

            lessonCommentRecordDao.saveAndFlush(lessonCommentRecord);
            return new Result(true, "创建成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    @ResponseBody
    @RequestMapping("/lessoncommentrecord/update")
    public Result update(@RequestParam("lId") String lId,
                         @RequestParam("sId") String sId,
                         @RequestParam("comment") String comment){

        LessonCommentRecord lessonCommentRecord = lessonCommentRecordDao.findByLIdAndSId(lId,sId);

        lessonCommentRecord.setComment(comment);

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String StrDate = simpleDateFormat.format(new Date());
        java.util.Date date = new java.util.Date();
        lessonCommentRecord.setcTime(new Date(date.getTime()));

        try {
            lessonCommentRecordDao.saveAndFlush(lessonCommentRecord);
            return new Result(true, "更新数据成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    @ResponseBody
    @RequestMapping("/lessoncommentrecord/delete")
    public Result delete(@RequestParam("lId") String lId,
                         @RequestParam("sId") String sId){

        try{
            lessonCommentRecordDao.deleteByLIdAndSId(lId,sId);
            return new Result(true, "更新数据成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }
}
