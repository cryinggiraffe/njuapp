package com.nju.app.dao;

import com.nju.app.entities.LessonLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonLocationDao extends JpaRepository<LessonLocation, Integer> {

    LessonLocation findByLId(String lId);
}
