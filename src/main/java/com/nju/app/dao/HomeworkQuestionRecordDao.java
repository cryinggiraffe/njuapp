package com.nju.app.dao;

import com.nju.app.entities.HomeworkQuestionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeworkQuestionRecordDao extends JpaRepository<HomeworkQuestionRecord, Integer> {
    List<HomeworkQuestionRecord> findByHId(String hId);
}
