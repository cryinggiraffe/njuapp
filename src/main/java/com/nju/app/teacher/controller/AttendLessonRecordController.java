package com.nju.app.teacher.controller;

import com.nju.app.dao.AttendLessonRecordDao;
import com.nju.app.dao.LessonDao;
import com.nju.app.dao.SelectCourseRecordDao;
import com.nju.app.dao.StudentDao;
import com.nju.app.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import static org.apache.coyote.http11.Constants.a;

@Controller
public class AttendLessonRecordController {

    //所有学生到课情况
    @GetMapping("/attendlessonrecord")
    public String index1(){
        return "teacher/attendlessonrecord";
    }

    //某堂课的到课学生名单
    @GetMapping("/attendlessonstudent")
    public String index2(){
        return "teacher/attendlessonstudent";
    }

    //缺课学生名单
    @GetMapping("/absentlessonstudent")
    public String index3(){
        return "teacher/absentlessonstudent";
    }

    @Autowired
    LessonDao lessonDao;

    @Autowired
    AttendLessonRecordDao attendLessonRecordDao;

    @Autowired
    SelectCourseRecordDao selectCourseRecordDao;

    @Autowired
    StudentDao studentDao;

    //当前课程到课记录
    @ResponseBody
    @RequestMapping("/attendlessonrecord/findAllAttendLessonRecords")
    public List<AttendLessonRecord> findAllAttendLessonRecords(@RequestParam("lId") String lId){

        return attendLessonRecordDao.findByLId(lId);
    }

    //当前课程到课学生
    @ResponseBody
    @RequestMapping("/attendlessonrecord/findAllAttendLessonStudents")
    public List<AttendLessonRecordResult> findAttendLessonRecordByLId(@RequestParam("lId") String lId){

        List<AttendLessonRecord> attendLessonRecords = attendLessonRecordDao.findByLId(lId);

        List<AttendLessonRecordResult> attendLessonRecordResults = new ArrayList<>();

        for (AttendLessonRecord attendLessonRecord : attendLessonRecords){

            AttendLessonRecordResult attendLessonRecordResult = new AttendLessonRecordResult();

            attendLessonRecordResult.setlId(lId);
            attendLessonRecordResult.setsId(attendLessonRecord.getsId());
            attendLessonRecordResult.setsName(studentDao.findBySId(attendLessonRecord.getsId()).getsName());
            attendLessonRecordResult.setAlTime(lessonDao.findByLId(attendLessonRecord.getlId()).getlTime().toString());

            attendLessonRecordResults.add(attendLessonRecordResult);
        }

        return attendLessonRecordResults;
    }

    //查找某堂课的某个学生
    @ResponseBody
    @RequestMapping("/attendlessonrecord/findAttendLessonRecordResultBySId")
    public AttendLessonRecordResult findAttendLessonRecordBySId(@RequestParam("lId") String lId,
                                                                @RequestParam("sId") String sId){

        AttendLessonRecord attendLessonRecord = attendLessonRecordDao.findBySIdAndLId(sId,lId);

        AttendLessonRecordResult attendLessonRecordResult = new AttendLessonRecordResult();

        attendLessonRecordResult.setlId(lId);
        attendLessonRecordResult.setsId(attendLessonRecord.getsId());
        attendLessonRecordResult.setsName(studentDao.findBySId(attendLessonRecord.getsId()).getsName());
        attendLessonRecordResult.setAlTime(lessonDao.findByLId(attendLessonRecord.getlId()).getlTime().toString());

        return attendLessonRecordResult;
    }



    @ResponseBody
    @RequestMapping("/attendlessonrecord/findAllAttendLessonRecordResults")
    public List<AttendLessonRecordResult> findAllAttendLessonRecordResults(@RequestParam("cId") String cId){

        List<Lesson> lessons = lessonDao.findByCId(cId);

        List<AttendLessonRecord> attendLessonRecords = new ArrayList<>();

        List<AttendLessonRecordResult> attendLessonRecordResults = new ArrayList<>();

        for (Lesson lesson : lessons){

            attendLessonRecords.addAll(attendLessonRecordDao.findByLId(lesson.getlId()));
        }

        for (AttendLessonRecord attendLessonRecord : attendLessonRecords){

            AttendLessonRecordResult attendLessonRecordResult = new AttendLessonRecordResult();

            attendLessonRecordResult.setlId(attendLessonRecord.getlId());
            attendLessonRecordResult.setsId(attendLessonRecord.getsId());
            attendLessonRecordResult.setsName(studentDao.findBySId(attendLessonRecord.getsId()).getsName());
            attendLessonRecordResult.setAlTime(lessonDao.findByLId(attendLessonRecord.getlId()).getlTime().toString());

            attendLessonRecordResults.add(attendLessonRecordResult);
        }

        return attendLessonRecordResults;
    }

    //查询某学生某门课的到课记录
    @ResponseBody
    @RequestMapping("/attendlessonrecord/findAttendLessonRecordResultsBySId")
    public List<AttendLessonRecordResult> findAttendLessonRecordResultsBySId(@RequestParam("cId") String cId,
                                                                             @RequestParam("sId") String sId){
        List<Lesson> lessons = lessonDao.findByCId(cId);

        List<AttendLessonRecord> attendLessonRecord1s = new ArrayList<>();

        List<AttendLessonRecordResult> attendLessonRecordResults = new ArrayList<>();

        for (Lesson lesson : lessons){
            attendLessonRecord1s.addAll(attendLessonRecordDao.findByLId(lesson.getlId()));
        }

        List<AttendLessonRecord> attendLessonRecord2s = attendLessonRecordDao.findBySIdAndIsAttended(sId,1);

        attendLessonRecord1s.retainAll(attendLessonRecord2s);

        for (AttendLessonRecord attendLessonRecord : attendLessonRecord1s){

            AttendLessonRecordResult attendLessonRecordResult = new AttendLessonRecordResult();

            attendLessonRecordResult.setlId(attendLessonRecord.getlId());
            attendLessonRecordResult.setsId(attendLessonRecord.getsId());
            attendLessonRecordResult.setsName(studentDao.findBySId(attendLessonRecord.getsId()).getsName());
            attendLessonRecordResult.setAlTime(lessonDao.findByLId(attendLessonRecord.getlId()).getlTime().toString());

            attendLessonRecordResults.add(attendLessonRecordResult);
        }

        return attendLessonRecordResults;
    }



    //缺课名单
    @ResponseBody
    @RequestMapping("/absentlessonrecord/findAllAbsentLessonStudents")
    public List<Student> findAllAbsentLessonRecords(@RequestParam("cId") String cId,
                                                    @RequestParam("lId") String lId){

        //List<AttendLessonRecord> attendLessonRecords = attendLessonRecordDao.findByLIdAndIsAttended(lId,0);

        List<AttendLessonRecord> attendLessonRecords = attendLessonRecordDao.findByLId(lId);

        List<SelectCourseRecord> selectCourseRecords = selectCourseRecordDao.findByCIdAndIsSelected(cId,1);

        List<Student> attend_students = new ArrayList<>();
        List<Student> select_students = new ArrayList<>();


        for (AttendLessonRecord attendLessonRecord : attendLessonRecords){

            Student student = studentDao.findBySId(attendLessonRecord.getsId());
            attend_students.add(student);
        }

        for (SelectCourseRecord selectCourseRecord : selectCourseRecords){

            Student student = studentDao.findBySId(selectCourseRecord.getsId());
            select_students.add(student);
        }

        select_students.removeAll(attend_students);
        return select_students;
    }

}
