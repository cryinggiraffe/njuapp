package com.nju.app.dao;

import com.nju.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User,Integer>{

    public User findByUsername(String username);

    public User findByOpenId(String openId);


}
