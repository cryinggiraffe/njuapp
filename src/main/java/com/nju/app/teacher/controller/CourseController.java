package com.nju.app.teacher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController {

    @GetMapping("/course")
    public String index(){
        return "teacher/course";
    }

}
