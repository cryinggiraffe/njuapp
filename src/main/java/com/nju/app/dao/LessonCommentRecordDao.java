package com.nju.app.dao;

import com.nju.app.entities.LessonCommentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LessonCommentRecordDao extends JpaRepository<LessonCommentRecord, Integer>{

    public List<LessonCommentRecord> findByLId(String lId);

    public LessonCommentRecord findByLIdAndSId(String lId, String sId);

    public LessonCommentRecord findByLcId(String lcId);

    @Transactional
    public void deleteByLcId(String lcId);

    @Transactional
    public void deleteByLIdAndSId(String lId, String sId);
}
