package com.nju.app.dao;


import com.nju.app.entities.AppointmentRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRecordDao extends JpaRepository<AppointmentRecord, Integer>{

    public List<AppointmentRecord> findByAId(String aId);

    public List<AppointmentRecord> findBySId(String sId);

    public List<AppointmentRecord> findByIsAppointed(Integer isAppointed);

    public List<AppointmentRecord> findByAIdAndIsAppointed(String aId, Integer isAppointed);

    public AppointmentRecord findByAIdAndSId(String aId, String sId);

    //public List<AppointmentRecord> findBySIdAndIsAppointed(String sId, Integer isAppointed);
}
