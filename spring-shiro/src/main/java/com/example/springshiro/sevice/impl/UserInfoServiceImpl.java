package com.example.springshiro.sevice.impl;

import com.example.springshiro.dao.UserInfoDao;
import com.example.springshiro.entity.UserInfo;
import com.example.springshiro.sevice.UserInfoService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    /*
    @Resource默认按照名称方式进行bean匹配，@Autowired默认按照类型方式进行bean匹配
    @Resource(import javax.annotation.Resource;)是J2EE的注解，
    @Autowired( import org.springframework.beans.factory.annotation.Autowired;)是Spring的注解
    使用@Resource可以减少代码和Spring之间的耦合。
    如果一个接口有两个实现类@Resource(name = "manImpl")首字母小写，而@Autowired要加上@Qualifier("manImpl")
     */
    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo findByUsername(String username) {
        System.out.println("UserInfoServiceImpl.findByUsername()");
        return userInfoDao.findByUsername(username);
    }

    @Override
    public List<UserInfo> getUserList() {
        return userInfoDao.findAll();
    }

    @Override
    public void save(UserInfo UserInfo) {
        String hashAlgorithmName = "MD5";//加密方式
        Object crdentials = UserInfo.getPassword();//密码原值
        Object salt = UserInfo.getCredentialsSalt();//盐值
        int hashIterations = 2;//加密2次
        //对密码尽情加盐处理
        Object result = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);
        UserInfo.setPassword(result.toString());
        System.out.println(result);
        userInfoDao.save(UserInfo);
    }

    @Override
    public void edit(UserInfo UserInfo) {
        String hashAlgorithmName = "MD5";//加密方式
        Object crdentials = UserInfo.getPassword();//密码原值
        Object salt = UserInfo.getCredentialsSalt();//盐值
        int hashIterations = 2;//加密2次
        //对密码尽情加盐处理
        Object result = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);
        UserInfo.setPassword(result.toString());
        userInfoDao.modifyByusername(UserInfo.getName(), UserInfo.getPassword(), UserInfo.getState(), UserInfo.getUsername());
    }

    @Override
    public void deleteByUsername(String username) {
        userInfoDao.deleteByUsername(username);
    }

}