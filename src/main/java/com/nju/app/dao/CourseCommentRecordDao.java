package com.nju.app.dao;

import com.nju.app.entities.CourseCommentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CourseCommentRecordDao extends JpaRepository<CourseCommentRecord, Integer>{

    public List<CourseCommentRecord> findByCId(String cId);

    public CourseCommentRecord findBySIdAndCId(String sId, String cId);

    public CourseCommentRecord findByCcId(String ccId);

    @Transactional
    public void deleteByCcId(String ccId);

    @Transactional
    public void deleteBySIdAndCId(String sId, String cId);
}
