package com.oasis.shiro.service;

import com.oasis.shiro.dao.UserInfoDao;
import com.oasis.shiro.dao.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    public UserInfo findByUsername(String username){
        System.out.println("UserInfoService findByUsername()");
        return userInfoDao.findByUsername(username);
    }
}
