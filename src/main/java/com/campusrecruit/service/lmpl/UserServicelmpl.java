package com.campusrecruit.service.lmpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.campusrecruit.mapper.UserMapper;
import com.campusrecruit.pojo.DO.User;
import com.campusrecruit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicelmpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public User selectByUserId(Integer userId) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);

        return userMapper.selectOne(wrapper);
    }

    @Override
    public User selectByOpenId(String openId) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("open_id",openId);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public int updateOne(User user) {
        return  userMapper.updateById(user);
    }

    @Override
    public int insertOne(User user) {
        return userMapper.insert(user);
    }

    @Override
    public List<User> selectUserList() {
        return userMapper.selectList(new QueryWrapper<>());
    }
}
