package com.nju.app.dao;

import com.nju.app.entities.SelectCourseRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SelectCourseRecordDao extends JpaRepository<SelectCourseRecord,Integer>{

    public SelectCourseRecord findByScId(String scId);

    public SelectCourseRecord findBySIdAndCId(String sId, String cId);

    //学生已选课程
    public List<SelectCourseRecord> findBySIdAndIsSelected(String sId, Integer isSelected);

    public List<SelectCourseRecord> findByCIdAndIsSelected(String cId, Integer isSelected);

    //统计已选课的人数
    public int countByCIdAndIsSelected(String cId, Integer isSelected);
}
