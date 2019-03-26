package com.nju.app.student.service;

import com.nju.app.dao.CourseDao;
import com.nju.app.dao.CourseReviewRecordDao;
import com.nju.app.dao.SelectCourseRecordDao;
import com.nju.app.dao.StudentDao;
import com.nju.app.entities.*;
import com.nju.app.student.entities.CourseScoreAndReview;
import com.nju.app.student.entities.StudentSelectCourse;
import com.nju.app.utils.GetUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentSelectCourseService {

    @Autowired
    SelectCourseRecordDao selectCourseRecordDao;

    @Autowired
    CourseReviewRecordDao courseReviewRecordDao;

    @Autowired
    CourseDao courseDao;

    @Autowired
    StudentDao studentDao;

    //返回已选和未选的课程
    public List<StudentSelectCourse> findAllCourses(String sId){

        List<Course> courses = courseDao.findAll();
        List<SelectCourseRecord> selectCourseRecords = selectCourseRecordDao.findBySIdAndIsSelected(sId,1);

        List<Course> selectedCourses = new ArrayList<>();
        for (SelectCourseRecord selectCourseRecord : selectCourseRecords){
            Course course = new Course();
            course.setcId(selectCourseRecord.getcId());

            selectedCourses.add(course);
        }
        System.out.println(selectedCourses);

        List<Course> allCourses = new ArrayList<>();
        for (Course course : courses){
            Course course2 = new Course();

            course2.setcId(course.getcId());
            allCourses.add(course2);
        }

        List<StudentSelectCourse> studentSelectCourses = new ArrayList<>();
        for (Course course : selectedCourses){

            StudentSelectCourse studentSelectCourse = new StudentSelectCourse();

            studentSelectCourse.setcId(course.getcId());
            studentSelectCourse.setcName(courseDao.findByCId(course.getcId()).getcName());
            studentSelectCourse.setsId(sId);
            studentSelectCourse.setIsSelected(1);

            studentSelectCourses.add(studentSelectCourse);
        }

        for (int i = 0; i < selectedCourses.size(); i++){

           for (int j = 0; j < allCourses.size(); j++){
               if (allCourses.get(j).getcId().equals(selectedCourses.get(i).getcId())){
                   allCourses.remove(j);
               }
           }

        }

        System.out.println(allCourses);

        for (Course course : allCourses){

            StudentSelectCourse studentSelectCourse2 = new StudentSelectCourse();

            studentSelectCourse2.setcId(course.getcId());
            studentSelectCourse2.setcName(courseDao.findByCId(course.getcId()).getcName());
            studentSelectCourse2.setsId(sId);
            studentSelectCourse2.setIsSelected(0);

            studentSelectCourses.add(studentSelectCourse2);
        }

        return studentSelectCourses;
    }

    //选课
    public Result selectCourse(String cId, String sId, String token){

        Course course = courseDao.findByCId(cId);

        SelectCourseRecord selectCourseRecord = selectCourseRecordDao.findBySIdAndCId(sId,cId);

        if(course.getToken().equals(token)) {

            if (selectCourseRecord != null){
                selectCourseRecord.setIsSelected(1);
            }else {
                selectCourseRecord = new SelectCourseRecord();

                String scId = cId + sId + GetUUID.getUUID();
                selectCourseRecord.setScId(scId);
                selectCourseRecord.setcId(cId);
                selectCourseRecord.setsId(sId);
                selectCourseRecord.setIsSelected(1);
            }
            try{
                selectCourseRecordDao.saveAndFlush(selectCourseRecord);
                return new Result(true, "创建成功");
            }catch (Exception e){
                e.printStackTrace();
                return new Result(false, "发生未知错误");
            }
        } else {
            return new Result(false, "发生未知错误");
        }
    }

    //课程成绩及复议结果
    public CourseScoreAndReview getCourseScore(String cId, String sId){

        SelectCourseRecord selectCourseRecord = selectCourseRecordDao.findBySIdAndCId(sId,cId);
        CourseReviewRecord courseReviewRecord = courseReviewRecordDao.findByCIdAndSId(cId, sId);

        CourseScoreAndReview courseScoreAndReview = new CourseScoreAndReview();

        courseScoreAndReview.setGrade(selectCourseRecord.getScore());
        courseScoreAndReview.setResult(courseReviewRecord.getResult());

        return courseScoreAndReview;
    }
}
