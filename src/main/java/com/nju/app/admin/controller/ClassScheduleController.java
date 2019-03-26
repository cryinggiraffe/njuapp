package com.nju.app.admin.controller;

import com.nju.app.dao.ClassScheduleDao;
import com.nju.app.entities.ClassSchedule;
import com.nju.app.entities.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ClassScheduleController {

    @Autowired
    ClassScheduleDao classScheduleDao;

    @ResponseBody
    @RequestMapping("/classschedule/findAllClassSchedules")
    public List<ClassSchedule> findAll(){

        return classScheduleDao.findAll();
    }

    @ResponseBody
    @RequestMapping("/classschedule/findByCsId")
    public ClassSchedule findByCsId(@RequestParam("csId") String csId){
        return classScheduleDao.findByCsId(csId);
    }

    @ResponseBody
    @RequestMapping("/classschedule/update")
    public Result update(@RequestParam("csId") String csId,
                         @RequestParam("csName") String csName){

        ClassSchedule classSchedule = classScheduleDao.findByCsId(csId);
        classSchedule.setCsName(csName);
        try {
            classScheduleDao.saveAndFlush(classSchedule);
            return new Result(true, "更新数据成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    @ResponseBody
    @RequestMapping("/classschedule/delete")
    public Result delete(@RequestParam("csId") String csId){
        try {
            classScheduleDao.deleteByCsId(csId);
            //userDao.delete(user);
            return new Result(true, "更新数据成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    //返回最新的课程表
    @ResponseBody
    @RequestMapping("/classschedule/getLastClassschedule")
    public String getImageUrl(){

        List<ClassSchedule> classSchedules = classScheduleDao.findAll();

        int max = classSchedules.get(0).getId();

        for (int i = 0; i < classSchedules.size(); i++){

            if(max > classSchedules.get(i).getId()){
                max = classSchedules.get(i).getId();
            }
        }

        return classSchedules.get(max - 1).getUrl();

    }
}
