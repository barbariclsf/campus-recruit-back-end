package com.campusrecruit.service.lmpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.campusrecruit.mapper.JobIntentionMapper;
import com.campusrecruit.pojo.DO.JobIntention;
import com.campusrecruit.service.JobIntentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobIntentionServicelmpl implements JobIntentionService {
    @Autowired
    private JobIntentionMapper jobIntentionMapper;

    @Override
    public JobIntention selectByUserId(Integer userId) {
        QueryWrapper<JobIntention> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        return jobIntentionMapper.selectOne(wrapper);
    }

    @Override
    public int insertOne(JobIntention jobIntention) {
        return jobIntentionMapper.insert(jobIntention);
    }

    @Override
    public int updateOne(JobIntention jobIntention) {
        return jobIntentionMapper.updateById(jobIntention);
    }
}
