package com.nju.app.teacher.controller;

import com.nju.app.dao.CourseReviewRecordDao;
import com.nju.app.dao.StudentDao;
import com.nju.app.entities.Course;
import com.nju.app.entities.CourseReviewRecord;
import com.nju.app.entities.Result;
import com.nju.app.utils.GetUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CourseReviewRecordController {

    @GetMapping("/coursereviewrecord")
    public String index(){
        return "teacher/coursereviewrecord";
    }

    @Autowired
    CourseReviewRecordDao courseReviewRecordDao;

    @Autowired
    StudentDao studentDao;

    @ResponseBody
    @RequestMapping("/coursereviewrecord/findAllCourseReviewRecords")
    public List<CourseReviewRecord> findAllCourseReviewRecords(@RequestParam("cId") String cId){

        return courseReviewRecordDao.findByCId(cId);
    }

    @ResponseBody
    @RequestMapping("/coursereviewrecord/findByCrId")
    public CourseReviewRecord findByCrId(@RequestParam("crId") String crId){

        return courseReviewRecordDao.findByCrId(crId);
    }

    @ResponseBody
    @RequestMapping("/coursereviewrecord/create")
    public Result create(@RequestParam("cId") String cId,
                         @RequestParam("sId") String sId,
                         @RequestParam("review") String review){

        CourseReviewRecord courseReviewRecord = new CourseReviewRecord();

        String crId = cId + sId + GetUUID.getUUID();
        courseReviewRecord.setCrId(crId);
        courseReviewRecord.setcId(cId);
        courseReviewRecord.setsId(sId);
        courseReviewRecord.setsName(studentDao.findBySId(sId).getsName());
        courseReviewRecord.setReview(review);

        try{
            courseReviewRecordDao.saveAndFlush(courseReviewRecord);
            return new Result(true, "创建成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    @ResponseBody
    @RequestMapping("/coursereviewrecord/updateReview")
    public Result updateReview(@RequestParam("cId") String cId,
                               @RequestParam("sId") String sId,
                               @RequestParam("review") String review){

        CourseReviewRecord courseReviewRecord = courseReviewRecordDao.findByCIdAndSId(cId,sId);
        courseReviewRecord.setReview(review);

        try{
            courseReviewRecordDao.saveAndFlush(courseReviewRecord);
            return new Result(true, "更新数据成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    @ResponseBody
    @RequestMapping("/coursereviewrecord/updateResult")
    public Result updateResult(@RequestParam("crId") String crId,
                               @RequestParam("result") String result){

        CourseReviewRecord courseReviewRecord = courseReviewRecordDao.findByCrId(crId);

        courseReviewRecord.setResult(result);

        try{
            courseReviewRecordDao.saveAndFlush(courseReviewRecord);
            return new Result(true, "更新数据成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    @ResponseBody
    @RequestMapping("/coursereviewrecord/delete")
    public Result delete(@RequestParam("cId") String cId,
                         @RequestParam("sId") String sId){

        try{
            courseReviewRecordDao.deleteByCIdAndSId(cId,sId);
            return new Result(true, "更新数据成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }
}
