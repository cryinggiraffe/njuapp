package com.nju.app.admin.controller;

import com.nju.app.dao.ClassScheduleDao;
import com.nju.app.entities.ClassSchedule;
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

@Controller
public class UploadController {

    @GetMapping("/upload")
    public String upload(){
        return "admin/upload";
    }

    @Autowired
    ClassScheduleDao classScheduleDao;

    @RequestMapping("upload/save")
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, Model model){

        String url = "static/classSchedule/";
        //String path = request.getSession().getServletContext().getRealPath(url);
        //String path = "/Users/shahao/Downloads/nju/classSchedule/";
        String path = "/root/njuapp/classSchedule/";
        //String path = ClassUtils.getDefaultClassLoader().getResource("").getPath()+url;

        File filePath = new File(path);
        //File filePath2 = new File(path2);
        System.out.println("文件保存路径：" + filePath.getAbsolutePath());
        //System.out.println("文件保存路径：" + filePath2.getAbsolutePath());
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

        String ClassScheduleUrl = "/classSchedule/"+fileName;

        //在指定路径创建一个文件
        //File targetFile = new File(path, originalFileName);
        File targetFile = new File(path, fileName);

        ClassSchedule classSchedule = new ClassSchedule();
        classSchedule.setCsId(fileName);
        classSchedule.setCsName(originalFileName);
        classSchedule.setUrl(ClassScheduleUrl);
        System.out.print(classSchedule);

        try {
            file.transferTo(targetFile);
            classScheduleDao.saveAndFlush(classSchedule);
            model.addAttribute("message", "保存数据成功");

        } catch (IOException e) {
            System.out.println("保存文件错误...");
            e.printStackTrace();
        }

        return "admin/upload";
    }
}
