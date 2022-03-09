package com.campusrecruit.service;

import com.campusrecruit.pojo.DO.Verify;

import java.util.List;

public interface VerifyService {

    /**
     * 插入认证数据
     * @param verify
     * @return
     */
    int insertOne(Verify verify);

    /**
     * 根据uid
     * @param valueOf
     * @return
     */
    Verify selectByUserId(Integer valueOf);


    /**
     * 更新
     * @param verify
     * @return
     */
    int updataOne(Verify verify);

    List<Verify> selectVerifyList();

    Verify selectById(Integer valueOf);
}
