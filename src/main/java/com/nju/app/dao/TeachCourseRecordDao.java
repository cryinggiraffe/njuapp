package com.nju.app.dao;

import com.nju.app.entities.TeachCourseRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeachCourseRecordDao extends JpaRepository<TeachCourseRecord,Integer>{

    public List<TeachCourseRecord> findByTId(String tId);
}
