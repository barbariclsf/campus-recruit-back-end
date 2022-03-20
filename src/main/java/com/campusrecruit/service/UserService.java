package com.campusrecruit.service;

import com.campusrecruit.pojo.DO.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    User selectByUserId(Integer userId);

    User selectByOpenId(String openId);

    int updateOne(User user);

    int insertOne(User user);

    List<User> selectUserList();
}
