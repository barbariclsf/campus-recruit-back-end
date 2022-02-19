package com.campusrecruit.service.lmpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.campusrecruit.mapper.ProjectExperienceMapper;
import com.campusrecruit.pojo.DO.ProjectExperience;
import com.campusrecruit.service.ProjectExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectExperienceServicelmpl implements ProjectExperienceService {

    @Autowired
    private ProjectExperienceMapper projectExperienceMapper;
    @Override
    public List<ProjectExperience> selectByUserId(Integer uid) {
        QueryWrapper<ProjectExperience> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",uid);
        return projectExperienceMapper.selectList(wrapper);
    }

    @Override
    public int insertOne(ProjectExperience proExp) {
        return projectExperienceMapper.insert(proExp);
    }

    @Override
    public ProjectExperience selectById(Integer id) {
        QueryWrapper<ProjectExperience> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return projectExperienceMapper.selectOne(wrapper);
    }

    @Override
    public int updateOne(ProjectExperience proExp) {
        return projectExperienceMapper.updateById(proExp);
    }

    @Override
    public int deleteOne(Integer id) {
        QueryWrapper<ProjectExperience> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return projectExperienceMapper.delete(wrapper);
    }
}
