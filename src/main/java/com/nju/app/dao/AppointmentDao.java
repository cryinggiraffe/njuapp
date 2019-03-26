package com.nju.app.dao;

import com.nju.app.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentDao extends JpaRepository<Appointment, Integer>{

    public List<Appointment> findByTId(String tId);

    public List<Appointment> findByTIdAndIsBooked(String tId, Integer isBooked);

    public List<Appointment> findByIsBooked(Integer isBooked);

    public Appointment findByAId(String aId);
}
