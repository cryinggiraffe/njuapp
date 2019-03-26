package com.nju.app.dao;

import com.nju.app.entities.CourseSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CourseSummaryDao extends JpaRepository<CourseSummary, Integer>{

    public CourseSummary findByCsId(String csId);

    public List<CourseSummary> findByCId(String cId);

    @Transactional
    public void deleteByCsId(String csId);
}
