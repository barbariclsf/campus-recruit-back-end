package com.campusrecruit.service;

import com.campusrecruit.pojo.DO.EducationExperience;

import java.util.List;


public interface EducationExperienceService {
    /**
     * 根据用户id查询教育经历
     * @param uid
     * @return
     */
    List<EducationExperience> selectByUserId(Integer uid);


    /**
     *插入教育经历
     * @param eduExp
     * @return
     */
    int insertOne(EducationExperience eduExp);

    /**
     * 根据经历id查询
     * @param id
     * @return
     */
    EducationExperience selectById(Integer id);


    /**
     * 更新教育经历
     * @param eduExp
     * @return
     */
    int updateOne(EducationExperience eduExp);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteOne(Integer id);
}
