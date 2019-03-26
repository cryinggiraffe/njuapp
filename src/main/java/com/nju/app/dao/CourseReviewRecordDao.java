package com.nju.app.dao;

import com.nju.app.entities.CourseReviewRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CourseReviewRecordDao extends JpaRepository<CourseReviewRecord, Integer>{

    public List<CourseReviewRecord> findByCId(String cId);

    public CourseReviewRecord findByCIdAndSId(String cId, String sId);

    public CourseReviewRecord findByCrId(String crId);

    @Transactional
    public void deleteByCIdAndSId(String cId, String sId);

    @Transactional
    public void deleteByCrId(String crId);

}
