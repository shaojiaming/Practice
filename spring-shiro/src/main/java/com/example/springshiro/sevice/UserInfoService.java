package com.example.springshiro.sevice;

import com.example.springshiro.entity.UserInfo;

import java.util.List;

public interface UserInfoService {
    /**通过username查找用户信息;*/
    UserInfo findByUsername(String username);

    List<UserInfo> getUserList();

    void save(UserInfo UserInfo);

    void edit(UserInfo UserInfo);

    void deleteByUsername(String username);

}