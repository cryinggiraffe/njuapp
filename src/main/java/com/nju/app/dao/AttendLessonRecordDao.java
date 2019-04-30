package com.nju.app.dao;

import com.nju.app.entities.AttendLessonRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendLessonRecordDao extends JpaRepository<AttendLessonRecord,Integer>{

    public AttendLessonRecord findByAlId(String alId);

    public AttendLessonRecord findBySIdAndLId(String sId, String lId);

    //查找所有当节课程的到课记录
    public List<AttendLessonRecord> findByLId(String lId);

    public List<AttendLessonRecord> findByLIdAndIsAttended(String lId, Integer isAttended);

    //查询到课人数
    public int countByLIdAndIsAttended(String lId, Integer isAttended);

    //查询某学生的所有到课记录
    public List<AttendLessonRecord> findBySIdAndIsAttended(String sId, Integer isAttended);
}
