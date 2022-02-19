package com.campusrecruit.service;

import com.campusrecruit.pojo.DO.JobIntention;

public interface JobIntentionService {
    /**
     * 根据用户id查找
     * @param userId
     * @return
     */
    JobIntention selectByUserId(Integer userId);

    /**
     * 插入数据
     * @param jobIntention
     * @return
     */
    int insertOne(JobIntention jobIntention);

    /**
     * 更新
     * @param jobIntention
     * @return
     */
    int updateOne(JobIntention jobIntention);
}
