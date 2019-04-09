package com.nju.app.dao;

import com.nju.app.entities.ChoiceQuestionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChoiceQuestionRecordDao extends JpaRepository<ChoiceQuestionRecord, Integer>{

    List<ChoiceQuestionRecord> findBySIdAndHId(String sId, String hId);
	
	List<ChoiceQuestionRecord> findByHId(String hId);
}
