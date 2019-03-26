package com.nju.app.dao;

import com.nju.app.entities.ClassSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


public interface ClassScheduleDao extends JpaRepository<ClassSchedule,Integer>{

    public ClassSchedule findByCsId(String csId);

    @Transactional
    public void deleteByCsId(String csId);
}
