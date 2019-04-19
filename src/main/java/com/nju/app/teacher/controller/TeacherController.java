package com.nju.app.teacher.controller;

import com.nju.app.dao.TeacherDao;
import com.nju.app.entities.Result;
import com.nju.app.entities.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TeacherController {
    @Autowired
    TeacherDao teacherDao;


    @ResponseBody
    @RequestMapping("teacher/create")
    public Result createUser(@RequestParam("t_id") String t_id,
                             @RequestParam("t_name") String t_name,
                             @RequestParam("sex") String sex){

        Teacher teacher = teacherDao.findByTId(t_id);

        if (teacher == null){
            teacher = new Teacher();
            teacher.settId(t_id);
            teacher.settName(t_name);
            teacher.setSex(sex);

            try {
                teacherDao.saveAndFlush(teacher);
                return new Result(true, "创建成功");
            } catch (Exception e) {
                e.printStackTrace();
                return new Result(false, "发生未知错误");
            }
        }else{
            return new Result(false, "用户已存在");
        }

    }
    @GetMapping("/teacher")
    public String index(){
        return "teacher/teacher";
    }

    @GetMapping("/homework")
    public String homework(){
        return "teacher/homework";
    }
}
