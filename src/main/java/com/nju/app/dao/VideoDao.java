package com.nju.app.dao;

import com.nju.app.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VideoDao extends JpaRepository<Video, Integer>{

    public Video findByVId(String vId);

    public List<Video> findByCId(String cId);

    @Transactional
    public void deleteByVId(String vId);
}
