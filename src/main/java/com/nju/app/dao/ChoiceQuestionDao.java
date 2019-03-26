package com.nju.app.dao;

import com.nju.app.entities.ChoiceQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ChoiceQuestionDao extends JpaRepository<ChoiceQuestion,Integer> {

    public List<ChoiceQuestion> findByCId(String cId);

    public ChoiceQuestion findByCqId(String cqId);

    @Transactional
    public void deleteByCqId(String cqId);
}
