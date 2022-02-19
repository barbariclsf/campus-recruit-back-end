package com.campusrecruit.service.lmpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.campusrecruit.mapper.CollectMapper;
import com.campusrecruit.mapper.CompanyMapper;
import com.campusrecruit.pojo.DO.Collect;
import com.campusrecruit.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServicelmpl implements CollectService {

    @Autowired
    private CollectMapper collectMapper;
    @Override
    public List<Collect> selectByUserId(Integer userId) {
        QueryWrapper<Collect> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        return collectMapper.selectList(wrapper);
    }

    @Override
    public int insertOne(Collect collect) {
        return collectMapper.insert(collect);
    }

    @Override
    public Collect selectByUserIdAndContenId(Integer userId, Integer contentId) {
        QueryWrapper<Collect> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        wrapper.eq("content_id",contentId);
        return collectMapper.selectOne(wrapper);
    }

    @Override
    public int deleteOne(Integer userId, Integer contentId) {
        QueryWrapper<Collect> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        wrapper.eq("content_id",contentId);
        return  collectMapper.delete(wrapper);
    }
}
