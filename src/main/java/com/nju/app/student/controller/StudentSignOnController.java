package com.nju.app.student.controller;

import com.nju.app.entities.Lesson;
import com.nju.app.entities.Result;
import com.nju.app.student.service.SignOnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StudentSignOnController {

    @Autowired
    SignOnService signOnService;

    @ResponseBody
    @RequestMapping("/lesson/findAllSignOnList")
    public List<Lesson> findAllSignOnList(){

        return signOnService.findAllSignOnList();
    }

    @ResponseBody
    @RequestMapping("/lesson/signOn")
    public Result signOn(@RequestParam("sId") String sId,
                         @RequestParam("lId") String lId,
                         @RequestParam("signCode") String signCode){

        return signOnService.signOn(sId,lId,signCode);
        //return signOnService.signOn2(sId,lId,signCode);
    }
}
