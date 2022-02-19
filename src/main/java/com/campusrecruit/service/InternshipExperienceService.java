package com.campusrecruit.service;

import com.campusrecruit.pojo.DO.InternshipExperience;

import java.util.List;

public interface InternshipExperienceService {
    /**
     * 根据用户id查找
     * @param uid
     * @return
     */
    List<InternshipExperience> selectByUserId(Integer uid);

    /**
     * 插入
     * @param intExp
     * @return
     */
    int insertOne(InternshipExperience intExp);

    /**
     * 查找根据经历id
     * @param id
     * @return
     */
    InternshipExperience selectById(Integer id);

    /**
     * 更新
     * @param intExp
     * @return
     */
    int updateOne(InternshipExperience intExp);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteOne(Integer id);
}
