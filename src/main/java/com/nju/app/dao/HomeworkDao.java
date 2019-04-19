package com.nju.app.dao;

import com.nju.app.entities.Homework;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomeworkDao extends JpaRepository<Homework, Integer>{

    List<Homework> findByCId(String cId);
}
