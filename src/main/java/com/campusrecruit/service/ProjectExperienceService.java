package com.campusrecruit.service;

import com.campusrecruit.pojo.DO.ProjectExperience;

import java.util.List;

public interface ProjectExperienceService {
    /**
     * 根据用户id查询
     * @param uid
     * @return
     */
    List<ProjectExperience> selectByUserId(Integer uid);


    /**
     *插入教育经历
     * @param proExp
     * @return
     */
    int insertOne(ProjectExperience proExp);

    /**
     * 根据经历id查询
     * @param id
     * @return
     */
    ProjectExperience selectById(Integer id);


    /**
     * 更新教育经历
     * @param proExp
     * @return
     */
    int updateOne(ProjectExperience proExp);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteOne(Integer id);
}
