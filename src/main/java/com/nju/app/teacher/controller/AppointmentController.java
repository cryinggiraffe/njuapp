package com.nju.app.teacher.controller;

import com.nju.app.dao.AppointmentDao;
import com.nju.app.entities.Appointment;
import com.nju.app.entities.Result;
import com.nju.app.utils.GetUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class AppointmentController {

    @GetMapping("/appointment")
    public String index(){
        return "teacher/appointment";
    }

    @Autowired
    AppointmentDao appointmentDao;

    @ResponseBody
    @RequestMapping("/appointment/findAllAppointments")
    public List<Appointment> findAllAppointments(@RequestParam("tId") String tId){

        return  appointmentDao.findByTId(tId);
    }

    @ResponseBody
    @RequestMapping("/appointment/findByAId")
    public Appointment findByAId(@RequestParam("aId") String aId){

        return appointmentDao.findByAId(aId);
    }

    @ResponseBody
    @RequestMapping("/appointment/create")
    public Result create(@RequestParam("tId") String tId,
                         @RequestParam("start") Long start,
                         @RequestParam("end") Long end,
                         @RequestParam("isBooked") Integer isBooked){

        Appointment appointment = new Appointment();
        String aId = tId + GetUUID.getUUID();
        appointment.setaId(aId);
        appointment.settId(tId);
        appointment.setIsBooked(isBooked);
        appointment.setStart(new Date(start));
        appointment.setEnd(new Date(end));

//        StringBuffer StrStart = new StringBuffer(start);
//        StringBuffer StrEnd = new StringBuffer(end);

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try{
//            appointment.setStart(StrStart.substring(0,10) + " " + StrStart.substring(11,16) + ":00");
//            appointment.setEnd(StrEnd.substring(0,10) + " " + StrEnd.substring(11,16) + ":00");
            appointmentDao.saveAndFlush(appointment);
            return new Result(true, "创建成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    @ResponseBody
    @RequestMapping("/appointment/update")
    public Result update(@RequestParam("aId") String aId,
                         @RequestParam("start") Long start,
                         @RequestParam("end") Long end,
                         @RequestParam("isBooked") Integer isBooked){

        Appointment appointment = appointmentDao.findByAId(aId);

        appointment.setStart(new Date(start));
        appointment.setEnd(new Date(end));
        appointment.setIsBooked(isBooked);

//        StringBuffer StrStart = new StringBuffer(start);
//        StringBuffer StrEnd = new StringBuffer(end);

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
//            appointment.setStart(StrStart.substring(0,10) + " " + StrStart.substring(11,16) + ":00");
//            appointment.setEnd(StrEnd.substring(0,10) + " " + StrEnd.substring(11,16) + ":00");
            appointmentDao.saveAndFlush(appointment);
            return new Result(true, "更新数据成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }
}
