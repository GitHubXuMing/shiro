package com.oasis.shiro.dao;

import com.oasis.shiro.dao.pojo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoDao extends JpaRepository<UserInfo,Integer> {
    public UserInfo findByUsername(String username);
}
