package com.campusrecruit.service;

import com.campusrecruit.pojo.DO.Trade;

import java.util.List;

public interface TradeService {
    /**
     * 根据id查找
     * @param tradeId
     * @return
     */
    Trade selectById(Integer tradeId);

    /**
     * 查找所有
     * @return
     */
    List<Trade> selectTradeList();
}
