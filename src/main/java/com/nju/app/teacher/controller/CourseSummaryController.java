package com.nju.app.teacher.controller;

import com.nju.app.dao.CourseSummaryDao;
import com.nju.app.dao.TeacherDao;
import com.nju.app.entities.CourseSummary;
import com.nju.app.entities.CourseSummaryResult;
import com.nju.app.entities.Result;
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
public class CourseSummaryController {

    @GetMapping("/coursesummary")
    public String index(){
        return "teacher/coursesummary";
    }

    @Autowired
    CourseSummaryDao courseSummaryDao;

    @Autowired
    TeacherDao teacherDao;

    @ResponseBody
    @RequestMapping("/coursesummary/findAllCourseSummarys")
    public List<CourseSummaryResult> findAllCourseSummarys(@RequestParam("cId") String cId){

        List<CourseSummary> courseSummaries = courseSummaryDao.findByCId(cId);

        List<CourseSummaryResult> courseSummaryResults = new ArrayList<>();

        for (CourseSummary courseSummary : courseSummaries){

            CourseSummaryResult courseSummaryResult = new CourseSummaryResult();

            courseSummaryResult.setCsId(courseSummary.getCsId());
            courseSummaryResult.setcId(courseSummary.getcId());
            courseSummaryResult.settId(courseSummary.gettId());
            courseSummaryResult.settName(teacherDao.findByTId(courseSummary.gettId()).gettName());
            courseSummaryResult.setSummary(courseSummary.getSummary());

            courseSummaryResults.add(courseSummaryResult);
        }
        return courseSummaryResults;
    }

    @ResponseBody
    @RequestMapping("/coursesummary/findByCsId")
    public CourseSummary findByCsId(@RequestParam("csId") String csId){
        return courseSummaryDao.findByCsId(csId);
    }

    @ResponseBody
    @RequestMapping("/coursesummary/create")
    public Result create(@RequestParam("cId") String cId,
                         @RequestParam("tId") String tId,
                         @RequestParam("summary") String summary){

        CourseSummary courseSummary = new CourseSummary();

        String csId = cId + tId + GetUUID.getUUID();
        courseSummary.setCsId(csId);
        courseSummary.setcId(cId);
        courseSummary.settId(tId);
        courseSummary.setSummary(summary);

        try{
            courseSummaryDao.saveAndFlush(courseSummary);
            return new Result(true, "更新数据成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }

    }

    @ResponseBody
    @RequestMapping("/coursesummary/update")
    public Result update(@RequestParam("csId") String csId,
                         @RequestParam("summary") String summary){

        CourseSummary courseSummary = courseSummaryDao.findByCsId(csId);

        courseSummary.setSummary(summary);

        try{
            courseSummaryDao.saveAndFlush(courseSummary);
            return new Result(true, "更新数据成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    @ResponseBody
    @RequestMapping("/coursesummary/delete")
    public Result delete(@RequestParam("csId") String csId){

        try{
            courseSummaryDao.deleteByCsId(csId);
            return new Result(true, "更新数据成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }
}
