package com.campusrecruit.service;

import com.campusrecruit.pojo.DO.AttachmentResume;

import java.util.List;

public interface AttachmentResumeService {
    /**
     * 插入数据
     * @param att
     * @return
     */
    int insertOne(AttachmentResume att);

    /**
     * 根据userId查找所有简历
     * @param userId
     * @return
     */
    List<AttachmentResume> selectByUserId(Integer userId);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteOne(Integer id);



    AttachmentResume selectById(int rid);
}
