package com.nju.app.admin.controller;

import com.nju.app.dao.StudentDao;
import com.nju.app.dao.TeacherDao;
import com.nju.app.dao.UserDao;
import com.nju.app.entities.Result;
import com.nju.app.entities.Student;
import com.nju.app.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterController {

    @Autowired
    UserDao userDao;

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    StudentDao studentDao;

    @ResponseBody
    @RequestMapping(value = "/wx/register", method = RequestMethod.POST)
    public Result wxregister(@RequestParam(value = "s_id") String openid,
                             @RequestParam(value = "s_name") String name,
                             @RequestParam(value = "s_sex") String sex,
                             @RequestParam(value = "s_idCard") String idCard,
                             @RequestParam(value = "password") String password){

        if(studentDao.findBySId(openid)==null){
            Student student = new Student();
            student.setsId(openid);
            student.setsName(name);
            student.setSex(sex);
            student.setId_card(idCard);
            studentDao.saveAndFlush(student);

            User user = new User();
            user.setUsername(idCard);
            user.setType("student");
            user.setPassword(password);
            user.setOpenId(openid);
            userDao.save(user);
            return new Result(true, student.getId_card());
        }else{
            return new Result(false, "failed");
        }
    }
}
