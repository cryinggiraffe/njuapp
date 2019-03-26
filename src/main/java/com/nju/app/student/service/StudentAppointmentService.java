package com.nju.app.student.service;


import com.nju.app.dao.AppointmentDao;
import com.nju.app.dao.AppointmentRecordDao;
import com.nju.app.dao.TeacherDao;

import com.nju.app.entities.Appointment;
import com.nju.app.entities.AppointmentRecord;
import com.nju.app.entities.Result;
import com.nju.app.student.entities.StudentAppointment;
import com.nju.app.utils.GetUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StudentAppointmentService {

    @Autowired
    AppointmentDao appointmentDao;

    @Autowired
    AppointmentRecordDao appointmentRecordDao;

    @Autowired
    TeacherDao teacherDao;

    //返回已预约和未预约的预约列表
    public List<StudentAppointment> findAllAppointments(String sId){

        Date now = new Date();

        List<Appointment> appointments = appointmentDao.findByIsBooked(1);
        List<AppointmentRecord> appointmentRecords = appointmentRecordDao.findBySId(sId);

        List<StudentAppointment> result = new ArrayList<>();

        for (Appointment appointment : appointments){

            StudentAppointment studentAppointment = new StudentAppointment();

            if ((appointment.getStart().getTime() < now.getTime()) && (now.getTime() < appointment.getEnd().getTime())){
                studentAppointment.setaId(appointment.getaId());
                studentAppointment.settName(teacherDao.findByTId(appointment.gettId()).gettName());
                studentAppointment.setStart(appointment.getStart().toString());
                studentAppointment.setEnd(appointment.getEnd().toString());

                Integer isAppointed = 0;
                for (AppointmentRecord appointmentRecord : appointmentRecords){
                    if (appointmentRecord.getaId().equals(appointment.getaId())){
                        isAppointed = appointmentRecord.getIsAppointed();
                    }
                }

                studentAppointment.setIsAppointed(isAppointed);

                result.add(studentAppointment);
            }
        }

        System.out.println(result);
        return  result;
    }


    public Result apoint(String aId, String sId){

        AppointmentRecord appointmentRecord = appointmentRecordDao.findByAIdAndSId(aId, sId);

        if (appointmentRecord == null){
            appointmentRecord = new AppointmentRecord();

            String arId = aId + sId + GetUUID.getUUID();
            appointmentRecord.setaId(aId);
            appointmentRecord.setArId(arId);
            appointmentRecord.setsId(sId);
            appointmentRecord.setIsAppointed(1);

            try{
                appointmentRecordDao.saveAndFlush(appointmentRecord);
                return new Result(true, "创建成功");
            }catch (Exception e){
                e.printStackTrace();
                return new Result(false, "发生未知错误");
            }
        }else {
            try{
                appointmentRecord.setIsAppointed(1);
                appointmentRecordDao.saveAndFlush(appointmentRecord);
                return new Result(true, "创建成功");
            }catch (Exception e) {
                e.printStackTrace();
                return new Result(false, "发生未知错误");
            }
        }
    }

    public Result cancel(String aId, String sId){

        AppointmentRecord appointmentRecord = appointmentRecordDao.findByAIdAndSId(aId, sId);

        appointmentRecord.setIsAppointed(0);

        try{
            appointmentRecordDao.saveAndFlush(appointmentRecord);
            return new Result(true, "更新成功");
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }
}
