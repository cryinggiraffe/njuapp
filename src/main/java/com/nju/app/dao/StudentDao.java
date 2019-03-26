package com.nju.app.dao;

import com.nju.app.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<Student, Integer>{

    public Student findBySId(String sId);
}
