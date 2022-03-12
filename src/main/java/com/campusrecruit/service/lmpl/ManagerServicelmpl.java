package com.campusrecruit.service.lmpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.campusrecruit.mapper.ManagerMapper;
import com.campusrecruit.mapper.UserMapper;
import com.campusrecruit.pojo.DO.Manager;
import com.campusrecruit.pojo.DO.User;
import com.campusrecruit.service.ManagerService;
import com.campusrecruit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServicelmpl implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Manager selectById(Integer id) {
        QueryWrapper<Manager> wrapper = new QueryWrapper<>();
        wrapper.eq("manager_id", id);
        return managerMapper.selectOne(wrapper);
    }

    @Override
    public int updateOne(Manager manager) {
        return managerMapper.updateById(manager);
    }


}
