package com.nju.app.student.controller;

import com.nju.app.entities.Appointment;
import com.nju.app.entities.Result;
import com.nju.app.student.entities.StudentAppointment;
import com.nju.app.student.service.StudentAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StudentAppointmentController {

    @Autowired
    StudentAppointmentService studentAppointmentService;

    @ResponseBody
    @RequestMapping("/appointment/findAll")
    public List<StudentAppointment> findAll(@RequestParam("sId") String sId){
       return studentAppointmentService.findAllAppointments(sId);
    }


    @ResponseBody
    @RequestMapping("/apppintment/apoint")
    public Result apoint(@RequestParam("aId") String aId,
                         @RequestParam("sId") String sId){
        return  studentAppointmentService.apoint(aId, sId);
    }
    
    @ResponseBody
    @RequestMapping("/appointment/findAppointed")
    public List<StudentAppointment> findAppointed(@RequestParam("sId") String sId){
        return studentAppointmentService.findAppointed(sId);
    }

    @ResponseBody
    @RequestMapping("/apppintment/cancel")
    public Result cancel(@RequestParam("aId") String aId,
                         @RequestParam("sId") String sId){
        return  studentAppointmentService.cancel(aId, sId);
    }
}
