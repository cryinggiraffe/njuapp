package com.nju.app.teacher.controller;

import com.nju.app.dao.CoursewareDao;
import com.nju.app.entities.Courseware;
import com.nju.app.entities.Result;
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
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@Controller
public class CoursewareController {

    @Autowired
    CoursewareDao coursewareDao;

    Courseware courseware = new Courseware();

    @GetMapping("/uploadcourseware")
    public String index(){
        return "teacher/uploadcourseware";
    }

    @ResponseBody
    @RequestMapping("courseware/findAllCoursewares")
    public List<Courseware> findAllCoursewares(@RequestParam("cId") String cId){

        //上传课件完成后，页面刷新后，给上传的课件加上cId,
        courseware.setcId(cId);
        return coursewareDao.findByCId(cId);
    }

    @ResponseBody
    @RequestMapping("courseware/findByCwId")
    public Courseware findByCwId(@RequestParam("cwId") String cwId){
        return coursewareDao.findByCwId(cwId);
    }

    @ResponseBody
    @RequestMapping("courseware/update")
    public Result update(@RequestParam("cwId") String cwId,
                         @RequestParam("cwName") String cwName){
        Courseware courseware = coursewareDao.findByCwId(cwId);
        courseware.setCwName(cwName);
        try {
            coursewareDao.saveAndFlush(courseware);
            return new Result(true, "更新数据成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    @ResponseBody
    @RequestMapping("courseware/delete")
    public Result delete(@RequestParam("cwId") String cwId){
        try{
            coursewareDao.deleteByCwId(cwId);
            return new Result(true, "更新数据成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }


    @RequestMapping("uploadcourseware/save")
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, Model model){

        //String url = "static/courseware/";
        //String path = request.getSession().getServletContext().getRealPath(url);
        //String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+url;

        String path = "/root/njuapp/courseware/";

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

        //在指定路径创建一个文件
        //File targetFile = new File(path, originalFileName);
        File targetFile = new File(path, fileName);

        String coursewareUrl = "/courseware/" + fileName;

        courseware.setCwId(fileName);
        courseware.setCwName(originalFileName);
        courseware.setUrl(coursewareUrl);
        courseware.setViewCount(0);
        courseware.setDownCount(0);
        courseware.setUploadTime(new Date(System.currentTimeMillis()));

        System.out.print(courseware);

        try {
            file.transferTo(targetFile);
            coursewareDao.saveAndFlush(courseware);
            System.out.println("保存文件成功...");
            model.addAttribute("message", "保存数据成功");
        } catch (IOException e) {
            System.out.println("保存文件错误...");
            e.printStackTrace();
        }

        return "teacher/uploadcourseware";
    }

//    @GetMapping("/download")
//    public String index2(){
//        return "admin/download";
//    }


    @ResponseBody
    @RequestMapping("/courseware/view")
    public String view(@RequestParam("cwId") String cwId){

        Courseware courseware = coursewareDao.findByCwId(cwId);

        Integer ViewCount = courseware.getViewCount() + 1;
        courseware.setViewCount(ViewCount);

        try {
            coursewareDao.saveAndFlush(courseware);
            return courseware.getUrl();
        }catch (Exception e){
            e.printStackTrace();
            return "发生未知错误";
        }



    }

    @ResponseBody
    @RequestMapping("/courseware/download")
    public String download(@RequestParam("cwId") String cwId){

        Courseware courseware = coursewareDao.findByCwId(cwId);

        Integer DownCount = courseware.getDownCount() + 1;
        courseware.setDownCount(DownCount);

        try {
            coursewareDao.saveAndFlush(courseware);
            return courseware.getUrl();
        }catch (Exception e){
            e.printStackTrace();
            return "发生未知错误";
        }
    }
}
