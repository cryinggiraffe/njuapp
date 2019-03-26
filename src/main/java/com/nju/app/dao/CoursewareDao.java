package com.nju.app.dao;

import com.nju.app.entities.Courseware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CoursewareDao extends JpaRepository<Courseware,Integer>{

    public Courseware findByCwId(String cwId);

    public List<Courseware> findByCId(String cId);

    @Transactional
    public void deleteByCwId(String cwId);

}
