package com.nju.app.teacher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeacherController {

    @GetMapping("/teacher")
    public String index(){
        return "teacher/teacher";
    }

    @GetMapping("/homework")
    public String homework(){
        return "teacher/homework";
    }
}
