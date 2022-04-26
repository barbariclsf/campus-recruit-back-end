package com.campusrecruit.service;

import com.campusrecruit.pojo.DO.Postion;
import okhttp3.internal.Internal;

import java.util.List;

public interface PostionService {
    /**
     * 职位列表
     * @return
     */
    List<Postion> selectPostionList();

    /**
     * 根据职位id查找
     * @param postionId
     * @return
     */
    Postion selectById(int postionId);

    /**
     * 根据公司id
     * @param cId
     * @return
     */
    List<Postion> selectPostionListByCId(Integer cId);

    /**
     * 根据发布者id
     * @param userId
     * @return
     */
    List<Postion> selectByPubId(Integer userId);

    /**
     * 新增职位
     * @param postion
     * @return
     */
    int insertOne(Postion postion);


    /**
     * 更新职位
     * @param postion
     * @return
     */
    int updateOne(Postion postion);

    /**
     * 删除
     * @param postionId
     * @return
     */
    int deleteOne(Integer postionId);

    /**
     * 搜索
     * @param content
     * @return
     */
    List<Postion> searchByPostionName(String content);

    List<Postion> selectAllPostionList();
}
