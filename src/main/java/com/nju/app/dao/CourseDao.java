package com.nju.app.dao;

import com.nju.app.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CourseDao extends JpaRepository<Course,Integer>{

    public Course findByCId(String cId);

    @Transactional
    public void  deleteByCId(String cId);
}
