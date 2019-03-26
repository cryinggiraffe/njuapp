package com.nju.app.admin.controller;

import com.nju.app.utils.GetOpenid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
public class IndexController {

    @GetMapping(value = {"/", "index"})
    public String index(){


        //GetOpenid getopenid=new GetOpenid();
        //调用访问微信服务器工具方法，传入三个参数获取带有openid、session_key的json字符串
        //String jsonId=getopenid.getopenid(appid,code,secret);
        //String jsonId = getopenid.getopenid();
        //JSONObject jsonObject = JSONObject.fromObject(jsonId);
        //从json字符串获取openid和session_key
        //String openid=jsonObject.getString("openid");
        //String session_key=jsonObject.getString("session_key");

        //System.out.println(jsonId);

        return "home/index";
    }

}
