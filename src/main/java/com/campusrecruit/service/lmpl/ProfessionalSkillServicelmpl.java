package com.campusrecruit.service.lmpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.campusrecruit.mapper.ProfessionalSkillMapper;
import com.campusrecruit.pojo.DO.ProfessionalSkill;
import com.campusrecruit.service.ProfessionalSkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionalSkillServicelmpl implements ProfessionalSkillService {

    @Autowired
    private ProfessionalSkillMapper professionalSkillMapper;

    @Override
    public List<ProfessionalSkill> selectByUserId(Integer uid) {
        QueryWrapper<ProfessionalSkill> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",uid);
        return professionalSkillMapper.selectList(wrapper);
    }

    @Override
    public int insertOne(ProfessionalSkill eduExp) {
        return professionalSkillMapper.insert(eduExp);
    }

    @Override
    public ProfessionalSkill selectById(Integer id) {
        QueryWrapper<ProfessionalSkill> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return professionalSkillMapper.selectOne(wrapper);
    }

    @Override
    public int updateOne(ProfessionalSkill eduExp) {
        return professionalSkillMapper.updateById(eduExp);
    }

    @Override
    public int deleteOne(Integer id) {
        QueryWrapper<ProfessionalSkill> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return professionalSkillMapper.delete(wrapper);
    }
}
