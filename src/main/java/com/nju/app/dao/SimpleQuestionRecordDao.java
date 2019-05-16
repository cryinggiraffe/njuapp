package com.nju.app.dao;

import com.nju.app.entities.SimpleQuestionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SimpleQuestionRecordDao extends JpaRepository<SimpleQuestionRecord, Integer> {

    List<SimpleQuestionRecord> findBySIdAndHId(String sId, String hId);

    List<SimpleQuestionRecord> findByHId(String hId);
}
