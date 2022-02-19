package com.campusrecruit.service.lmpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.campusrecruit.mapper.InternshipExperienceMapper;
import com.campusrecruit.pojo.DO.InternshipExperience;
import com.campusrecruit.service.InternshipExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InternshipExperienceServicelmpl implements InternshipExperienceService {

    @Autowired
    private InternshipExperienceMapper internshipExperienceMapper;
    @Override
    public List<InternshipExperience> selectByUserId(Integer uid) {
        QueryWrapper<InternshipExperience> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",uid);
        return internshipExperienceMapper.selectList(wrapper);
    }

    @Override
    public int insertOne(InternshipExperience intExp) {
        return internshipExperienceMapper.insert(intExp);
    }

    @Override
    public InternshipExperience selectById(Integer id) {
        QueryWrapper<InternshipExperience> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return internshipExperienceMapper.selectOne(wrapper);
    }

    @Override
    public int updateOne(InternshipExperience intExp) {
        return internshipExperienceMapper.updateById(intExp);
    }

    @Override
    public int deleteOne(Integer id) {
        QueryWrapper<InternshipExperience> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return internshipExperienceMapper.delete(wrapper);
    }
}
