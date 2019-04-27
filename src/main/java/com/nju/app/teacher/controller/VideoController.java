package com.nju.app.teacher.controller;

import com.nju.app.dao.VideoDao;
import com.nju.app.entities.Result;
import com.nju.app.entities.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Date;
import java.util.List;

@Controller
public class VideoController {

    @Autowired
    VideoDao videoDao;

    Video video = new Video();

    @GetMapping("/uploadvideo")
    public String index(){
        return "teacher/uploadvideo";
    }

    @ResponseBody
    @RequestMapping("video/findAllVideos")
    public List<Video> findAll(@RequestParam("cId") String cId){
        video.setcId(cId);
        return videoDao.findByCId(cId);
    }

    @ResponseBody
    @RequestMapping("video/findByVId")
    public Video findByVId(@RequestParam("vId") String vId){
        return videoDao.findByVId(vId);
    }

    @ResponseBody
    @RequestMapping("video/update")
    public Result update(@RequestParam("vId") String vId,
                         @RequestParam("vName") String vName){

        Video video = videoDao.findByVId(vId);
        video.setvName(vName);

        try{
            videoDao.saveAndFlush(video);
            return new Result(true, "更新数据成功");
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    @ResponseBody
    @RequestMapping("video/delete")
    public Result delete(@RequestParam("vId") String vId){

        try{
            videoDao.deleteByVId(vId);
            return new Result(true, "更新数据成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }


    @RequestMapping("uploadvideo/save")
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, Model model){

        if( (file == null) || file.getName().equals("") || file.getName() == null){

            return "";
        }

        //String url = "static/video/";
        //String path = request.getSession().getServletContext().getRealPath(url);
        //String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+url;
        //String path = "/Users/shahao/Downloads/nju/video/";
        String path = "/root/njuapp/video/";
        File filePath = new File(path);
        System.out.println("文件保存路径：" + filePath.getAbsolutePath());

        if (!filePath.exists() && !filePath.isDirectory()) {
            filePath.mkdirs();
            System.out.println("目录不存在，创建目录：" + filePath);
        }

        //获取原始文件名称
        String originalFileName = file.getOriginalFilename();
        System.out.println("原始文件名称：" + originalFileName);


        //获取文件类型，以最后一个`.`作为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        System.out.println("文件类型：" + type);

        //设置文件新名字
        String fileName = System.currentTimeMillis() + "." + type;
        System.out.println("文件新名称：" + fileName);

        String videoUrl = "/video/" + fileName;

        //在指定路径创建一个文件
        File targetFile = new File(path, fileName);

        video.setvId(fileName);
        video.setvName(originalFileName);
        video.setUrl(videoUrl);
        video.setCount(0);
        video.setUploadTime(new Date(new java.util.Date().getTime()));
        System.out.print(video);

        try {
            file.transferTo(targetFile);
            videoDao.saveAndFlush(video);
            System.out.println("保存文件成功...");
            model.addAttribute("message", "保存数据成功");
        }catch (Exception e){
            System.out.println("保存文件错误...");
            e.printStackTrace();
        }

        return "teacher/uploadvideo";
    }
}
