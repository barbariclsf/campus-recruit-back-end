package com.campusrecruit.service.lmpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.campusrecruit.mapper.EducationExperienceMapper;
import com.campusrecruit.pojo.DO.EducationExperience;
import com.campusrecruit.service.EducationExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationExperienceServicelmpl implements EducationExperienceService {

    @Autowired
    private EducationExperienceMapper educationExperienceMapper;
    @Override
    public List<EducationExperience> selectByUserId(Integer uid) {
        QueryWrapper<EducationExperience> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",uid);
        return educationExperienceMapper.selectList(wrapper);
    }

    @Override
    public int insertOne(EducationExperience eduExp) {
        return educationExperienceMapper.insert(eduExp);
    }

    @Override
    public EducationExperience selectById(Integer id) {
        QueryWrapper<EducationExperience> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return educationExperienceMapper.selectOne(wrapper);
    }

    @Override
    public int updateOne(EducationExperience eduExp) {
        return educationExperienceMapper.updateById(eduExp);
    }

    @Override
    public int deleteOne(Integer id) {
        QueryWrapper<EducationExperience> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return educationExperienceMapper.delete(wrapper);
    }
}
