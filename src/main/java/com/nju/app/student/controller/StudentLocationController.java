package com.nju.app.student.controller;

import com.nju.app.entities.Lesson;
import com.nju.app.entities.LessonLocation;
import com.nju.app.entities.Result;
import com.nju.app.student.service.LocationSignOnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StudentLocationController {

    @Autowired
    LocationSignOnService locationSignOnService;

    @ResponseBody
    @RequestMapping("/lesson/findAllLocationSignOnList")
    public List<Lesson> findAllSignOnList(@RequestParam("cId") String cId){

        return locationSignOnService.findLocationSignOnList(cId);
    }

    @ResponseBody
    @RequestMapping("/lesson/findLocationDetail")
    public LessonLocation findLocationDetail(@RequestParam("lId") String lId){
        return locationSignOnService.findLessonLocation(lId);
    }

    @ResponseBody
    @RequestMapping("/lesson/locationSignOn")
    public Result locationSignOn(@RequestParam("sId") String sId,
                                  @RequestParam("lId") String lId){

        return locationSignOnService.locationSignOn(sId, lId);
    }
}
