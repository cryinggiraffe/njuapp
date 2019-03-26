package com.nju.app.dao;

import com.nju.app.entities.SimpleQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SimpleQuestionDao extends JpaRepository<SimpleQuestion,Integer>{

    public List<SimpleQuestion> findByCId(String cId);

    public SimpleQuestion findBySqId(String sqId);

    @Transactional
    public void deleteBySqId(String sqId);
}
