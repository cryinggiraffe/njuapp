package com.nju.app.student.controller;

import com.nju.app.entities.Result;
import com.nju.app.entities.SelectCourseRecord;
import com.nju.app.entities.SelectCourseRecordResult;
import com.nju.app.student.entities.CourseScoreAndReview;
import com.nju.app.student.entities.StudentSelectCourse;
import com.nju.app.student.service.StudentSelectCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StudentSelectCourseController {

    @Autowired
    StudentSelectCourseService studentSelectCourseService;

    //学生已选和未选的课程
    @ResponseBody
    @RequestMapping("/findAllSelectedCourses")
    public List<StudentSelectCourse> findAllSelectedCourses(@RequestParam("sId") String sId){

        return studentSelectCourseService.findAllCourses(sId);
    }


    @ResponseBody
    @RequestMapping("/selectCourse")
    public Result selectCourse(@RequestParam("cId") String cId,
                               @RequestParam("sId") String sId,
                               @RequestParam("token") String token){

        return studentSelectCourseService.selectCourse(cId, sId, token);
    }

    @ResponseBody
    @RequestMapping("/getCourseScoreAndReview")
    public CourseScoreAndReview getCourseScoreAndReview(@RequestParam("cId") String cId,
                                                        @RequestParam("sId") String sId){

        return studentSelectCourseService.getCourseScore(cId, sId);

    }


}
