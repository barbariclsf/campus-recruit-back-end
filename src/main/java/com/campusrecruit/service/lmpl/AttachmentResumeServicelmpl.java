package com.campusrecruit.service.lmpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.campusrecruit.mapper.AttachmentResumeMapper;
import com.campusrecruit.pojo.DO.AttachmentResume;
import com.campusrecruit.service.AttachmentResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttachmentResumeServicelmpl implements AttachmentResumeService {
    @Autowired
    private AttachmentResumeMapper attachmentResumeMapper;


    @Override
    public int insertOne(AttachmentResume att) {
        return attachmentResumeMapper.insert(att);
    }

    @Override
    public List<AttachmentResume> selectByUserId(Integer userId) {
        QueryWrapper<AttachmentResume> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        return attachmentResumeMapper.selectList(wrapper);
    }

    @Override
    public int deleteOne(Integer id) {
        QueryWrapper<AttachmentResume> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return attachmentResumeMapper.delete(wrapper);
    }



    @Override
    public AttachmentResume selectById(int rid) {
        QueryWrapper<AttachmentResume> wrapper = new QueryWrapper<>();
        wrapper.eq("id",rid);
        return attachmentResumeMapper.selectOne(wrapper);
    }
}
