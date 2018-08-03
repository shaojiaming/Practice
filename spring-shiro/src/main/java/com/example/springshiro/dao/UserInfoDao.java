package com.example.springshiro.dao;


import com.example.springshiro.entity.UserInfo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//CrudRepository 中的save方法是相当于merge+save ，它会先判断记录是否存在，如果存在则更新，不存在则插入记录
public interface UserInfoDao extends CrudRepository<UserInfo,Long> {
    /**通过username查找用户信息;*/
    UserInfo findByUsername(String username);

    List<UserInfo> findAll();

    @Modifying
    @Query("delete from UserInfo where username = ?1")
    int deleteByUsername(String username);

    @Modifying
    @Query("update UserInfo u set u.name = ?1,u.password = ?2,u.state = ?3 where u.username = ?4")
    int modifyByusername(String name, String password, int state, String username);
}