package com.nju.app.teacher.controller;


import com.nju.app.dao.AppointmentRecordDao;
import com.nju.app.dao.StudentDao;

import com.nju.app.entities.AppointRecordResult;
import com.nju.app.entities.AppointmentRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AppointRecordController {

    @GetMapping("/appointmentrecord")
    public String index(){
        return "teacher/appointmentrecord";
    }

    @Autowired
    AppointmentRecordDao appointmentRecordDao;

    @Autowired
    StudentDao studentDao;

    @ResponseBody
    @RequestMapping("/appointmentrecord/findAllAppointRecords")
    public List<AppointRecordResult> findAllAppointRecords(@RequestParam("aId") String aId){

        List<AppointmentRecord> appointRecords = appointmentRecordDao.findByAIdAndIsAppointed(aId,1);

        List<AppointRecordResult> appointRecordResults = new ArrayList<>();

        for (AppointmentRecord appointRecord : appointRecords){

            AppointRecordResult appointRecordResult = new AppointRecordResult();

            appointRecordResult.setaId(aId);
            appointRecordResult.setsId(appointRecord.getsId());
            appointRecordResult.setsName(studentDao.findBySId(appointRecord.getsId()).getsName());

            appointRecordResults.add(appointRecordResult);
        }

        return appointRecordResults;
    }
}
