package com.campusrecruit.service.lmpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.campusrecruit.mapper.DeliverMapper;
import com.campusrecruit.pojo.DO.Deliver;
import com.campusrecruit.service.DeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliverServicelmpl implements DeliverService {
    @Autowired
    private DeliverMapper deliverMapper;

    @Override
    public int insertOne(Deliver deliver) {
        return deliverMapper.insert(deliver);
    }

    @Override
    public List<Deliver> selectByPublicerId(Integer pubId) {
        QueryWrapper<Deliver> wrapper = new QueryWrapper<>();
        wrapper.eq("publicer_id",pubId);
        return deliverMapper.selectList(wrapper);
    }

    @Override
    public List<Deliver> selectByDeliverId(Integer did) {
        QueryWrapper<Deliver> wrapper = new QueryWrapper<>();
        wrapper.eq("deliver_id",did);
        return deliverMapper.selectList(wrapper);
    }
}
