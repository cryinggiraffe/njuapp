package com.nju.app.teacher.controller;

import com.nju.app.dao.CourseDao;
import com.nju.app.dao.SelectCourseRecordDao;
import com.nju.app.dao.StudentDao;
import com.nju.app.entities.Result;
import com.nju.app.entities.SelectCourseRecord;
import com.nju.app.entities.SelectCourseRecordResult;
import com.nju.app.utils.GetUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SelectCourseRecordController {

    @GetMapping("/selectcourserecord")
    public String index(){
        return "teacher/selectcourserecord";
    }

    @Autowired
    SelectCourseRecordDao selectCourseRecordDao;

    @Autowired
    CourseDao courseDao;

    @Autowired
    StudentDao studentDao;

    //所有选该课程的学生
    @ResponseBody
    @RequestMapping("/selectcourserecord/findAllSelectCourseRecords")
    public List<SelectCourseRecordResult> findAllSelectCourseRecords(@RequestParam("cId") String cId){

        List<SelectCourseRecord> selectCourseRecords = selectCourseRecordDao.findByCIdAndIsSelected(cId,1);

        List<SelectCourseRecordResult> selectCourseRecordResults = new ArrayList<>();

        for (SelectCourseRecord selectCourseRecord : selectCourseRecords){

            SelectCourseRecordResult selectCourseRecordResult = new SelectCourseRecordResult();

            selectCourseRecordResult.setScId(selectCourseRecord.getScId());
            selectCourseRecordResult.setcId(cId);
            selectCourseRecordResult.setcName(courseDao.findByCId(cId).getcName());
            selectCourseRecordResult.setsId(selectCourseRecord.getsId());
            selectCourseRecordResult.setsName(studentDao.findBySId(selectCourseRecord.getsId()).getsName());
            selectCourseRecordResult.setScore(selectCourseRecord.getScore());

            selectCourseRecordResults.add(selectCourseRecordResult);
        }

        return selectCourseRecordResults;

    }

    @ResponseBody
    @RequestMapping("/selectcourserecord/findByScId")
    public SelectCourseRecord findByScId(@RequestParam("scId") String scId){
        return selectCourseRecordDao.findByScId(scId);
    }

    //修改成绩
    @ResponseBody
    @RequestMapping("/selectcourserecord/updateGrade")
    public Result updateGrade(@RequestParam("scId") String scId,
                              @RequestParam("score") Integer score){

        SelectCourseRecord selectCourseRecord = selectCourseRecordDao.findByScId(scId);
        selectCourseRecord.setScore(score);

        try{
            selectCourseRecordDao.saveAndFlush(selectCourseRecord);
            return new Result(true, "更新数据成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    //添加选课记录
    @ResponseBody
    @RequestMapping("/selectcourserecord/create")
    public Result create(@RequestParam("cId") String cId,
                         @RequestParam("sId") String sId){

        SelectCourseRecord selectCourseRecord = new SelectCourseRecord();

        String scId = cId + sId + GetUUID.getUUID();
        selectCourseRecord.setScId(scId);
        selectCourseRecord.setcId(cId);
        selectCourseRecord.setsId(sId);
        selectCourseRecord.setIsSelected(1);

        try{
            selectCourseRecordDao.saveAndFlush(selectCourseRecord);
            return new Result(true, "创建成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    //退选
    @ResponseBody
    @RequestMapping("/selectcourserecord/updateSelected")
    public Result updateSelected(@RequestParam("cId") String cId,
                                 @RequestParam("sId") String sId){

        SelectCourseRecord selectCourseRecord = selectCourseRecordDao.findBySIdAndCId(sId,cId);
        selectCourseRecord.setIsSelected(0);

        try {
            selectCourseRecordDao.saveAndFlush(selectCourseRecord);
            return new Result(true, "更新数据成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }



}
