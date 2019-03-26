package com.nju.app.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nju.app.dao.TeacherDao;
import com.nju.app.dao.UserDao;
import com.nju.app.entities.*;
import com.nju.app.utils.GetOpenid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class LoginController {

    @Autowired
    UserDao userDao;

    @Autowired
    TeacherDao teacherDao;

    @GetMapping("/login")
    public String login(){
        return "home/login";
    }

    @ResponseBody
    @RequestMapping("/web/login")
    public Result login(@RequestParam(value = "username") String username,
                        @RequestParam(value = "password") String password,
                        @RequestParam(value = "type") String type){

        User user = userDao.findByUsername(username);

        if (user == null){

            return new Result(false, "failed");
        }else{

            if (user.getPassword().equals(password) && user.getType().equals(type)){
                System.out.print(user.getUsername() + " " + user.getPassword() + " " + user.getType());
                return new Result(true, user.getType());
            }else{
                return new Result(false, "failed");
            }
        }
    }


    @ResponseBody
    @RequestMapping("/wx/getopenid")
    public WxOpenid getOpenId(@RequestParam("code") String code){

        GetOpenid getopenid = new GetOpenid();

        String jsonId = getopenid.getopenid(code);
        JSONObject jsonObject = JSON.parseObject(jsonId);

        WxOpenid wxOpenid = new WxOpenid(jsonObject.getString("openid"), jsonObject.getString("session_key"));



        System.out.println(jsonId);
        System.out.println(wxOpenid);

        return wxOpenid;

    }

    @ResponseBody
    @RequestMapping("/wx/login")
    public WxResult wxlogin(@RequestParam("openId") String openId){
        
        User user = userDao.findByOpenId(openId);

        if (user == null){

            return new WxResult(false, "failed","");
        }else{
            System.out.print(user.getUsername() + " " + user.getPassword() + " " + user.getOpenId() + user.getType());
            return new WxResult(true, user.getType(), user.getUsername());
        }
    }

    @ResponseBody
    @RequestMapping(value = "/wx/login2", method = RequestMethod.POST)
    public Result wxlogin2(@RequestParam(value = "username") String username,
                             @RequestParam(value = "password") String password,
                             @RequestParam(value = "type") String type,
                             @RequestParam("openId") String openId){

        User user = userDao.findByUsername(username);

        if (user == null){

            return new Result(false, "failed");
        }else{

            if (user.getPassword().equals(password) && user.getType().equals(type)){

                user.setOpenId(openId);
                userDao.saveAndFlush(user);
                System.out.print(user.getUsername() + " " + user.getPassword() + " " + user.getType());
                return new Result(true, user.getType());
            }else{
                return new Result(false, "failed");
            }
        }
    }

    @GetMapping("/forget")
    public String forget(){
        return "home/forget";
    }


    @ResponseBody
    @RequestMapping("/forget")
    public Result forget(@RequestParam(value = "username") String username,
                         @RequestParam(value = "id_card") String id_card,
                         @RequestParam(value = "password") String password){

        User user = userDao.findByUsername(username);

        //身份证
        Teacher teacher = teacherDao.findByTId(username);

        if (teacher.getId_card().equals(id_card)){
            user.setPassword(password);
            userDao.saveAndFlush(user);
            return new Result(true, "更新数据成功");
        }else {
            return new Result(false, "发生未知错误");
        }


    }


}
