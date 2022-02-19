package com.campusrecruit.service;

import com.campusrecruit.pojo.DO.ProfessionalSkill;

import java.util.List;

public interface ProfessionalSkillService {

    /**
     * 根据用户id查找
     * @param uid
     * @return
     */
    List<ProfessionalSkill> selectByUserId(Integer uid);

    /**
     * 插入
     * @param intExp
     * @return
     */
    int insertOne(ProfessionalSkill intExp);

    /**
     * 查找根据经历id
     * @param id
     * @return
     */
    ProfessionalSkill selectById(Integer id);

    /**
     * 更新
     * @param intExp
     * @return
     */
    int updateOne(ProfessionalSkill intExp);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteOne(Integer id);
}
