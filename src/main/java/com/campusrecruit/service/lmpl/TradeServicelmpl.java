package com.campusrecruit.service.lmpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.campusrecruit.mapper.TradeMapper;
import com.campusrecruit.pojo.DO.Trade;
import com.campusrecruit.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeServicelmpl implements TradeService {
    @Autowired
    private TradeMapper tradeMapper;
    @Override
    public Trade selectById(Integer id) {
        QueryWrapper<Trade> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return tradeMapper.selectOne(wrapper);
    }

    @Override
    public List<Trade> selectTradeList() {
        return tradeMapper.selectList(new QueryWrapper<>());
    }
}
