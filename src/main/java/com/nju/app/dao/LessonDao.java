package com.nju.app.dao;

import com.nju.app.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonDao extends JpaRepository<Lesson, Integer>{

    public List<Lesson> findByCId(String cId);

    public Lesson findByLId(String lId);
}
