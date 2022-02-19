package com.campusrecruit.service.lmpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.campusrecruit.mapper.VerifyMapper;
import com.campusrecruit.pojo.DO.Verify;
import com.campusrecruit.service.VerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VerifyServicelmpl implements VerifyService {

    @Autowired
    private VerifyMapper verifyMapper;
    @Override
    public int insertOne(Verify verify) {
        return verifyMapper.insert(verify);
    }

    @Override
    public Verify selectByUserId(Integer userId) {
        QueryWrapper<Verify> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        wrapper.eq("state",1);
        return  verifyMapper.selectOne(wrapper);
    }

    @Override
    public int updataOne(Verify verify) {
        return verifyMapper.updateById(verify);
    }
}
