package com.nju.app.student.service;

import com.nju.app.dao.VideoDao;
import com.nju.app.entities.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

    @Autowired
    VideoDao videoDao;

    //所有学生可播放的课程
    public List<Video> findAll(){
        return videoDao.findAll();
    }

    //视频的路径
    public Video play(String vId){

        Video video = videoDao.findByVId(vId);

        int count = video.getCount() + 1;
        video.setCount(count);

        try{
            videoDao.saveAndFlush(video);
            return video;
        }catch(Exception e){
            e.printStackTrace();
            return video;
        }
    }
}
