package com.nju.app.student.controller;

import com.nju.app.entities.Video;
import com.nju.app.student.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StudentVideoController {

    @Autowired
    VideoService videoService;

    @ResponseBody
    @RequestMapping("/video/findAll")
    public List<Video> findAll(){
        return videoService.findAll();
    }

    @ResponseBody
    @RequestMapping("/video/play")
    public Video play(@RequestParam("vId") String vId){

        return videoService.play(vId);
    }
}
