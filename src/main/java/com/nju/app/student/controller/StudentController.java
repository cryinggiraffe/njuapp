package com.nju.app.student.controller;

import com.nju.app.dao.StudentDao;
import com.nju.app.entities.Result;
import com.nju.app.entities.Student;
import com.nju.app.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by guagua on 2019/4/15.
 */
@Controller
public class StudentController {
    @Autowired
    StudentDao studentDao;


    @ResponseBody
    @RequestMapping("student/create")
    public Result createUser(@RequestParam("s_id") String s_id,
                             @RequestParam("s_name") String s_name,
                             @RequestParam("sex") String sex){

        Student student = studentDao.findBySId(s_id);

        if (student == null){
            student = new Student();
            student.setsId(s_id);
            student.setsName(s_name);
            student.setSex(sex);

            try {
                studentDao.saveAndFlush(student);
                return new Result(true, "创建成功");
            } catch (Exception e) {
                e.printStackTrace();
                return new Result(false, "发生未知错误");
            }
        }else{
            return new Result(false, "用户已存在");
        }

    }
}
