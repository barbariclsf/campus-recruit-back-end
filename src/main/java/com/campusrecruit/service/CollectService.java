package com.campusrecruit.service;

import com.campusrecruit.pojo.DO.Collect;

import java.util.List;

public interface CollectService {
    /**
     * 根据userId查询所有收藏
     * @param userId
     * @return
     */
    List<Collect> selectByUserId(Integer userId);

    /**
     * 插入收藏数据
     * @param collect
     * @return
     */
    int insertOne(Collect collect);

    /**
     * 根据userId和收藏id查询
     * @param userId
     * @param contentId
     * @return
     */
    Collect selectByUserIdAndContenId(Integer userId, Integer contentId);

    /**
     * 删除
     * @param userId
     * @param contentId
     * @return
     */
    int deleteOne(Integer userId, Integer contentId);
}
