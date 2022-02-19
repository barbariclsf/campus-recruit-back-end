package com.campusrecruit.controller;


import com.campusrecruit.pojo.DO.Trade;
import com.campusrecruit.pojo.ResultMap;
import com.campusrecruit.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trade")
public class TradeController {
    @Autowired
    private TradeService tradeService;

    @GetMapping
    public ResultMap selectTradeList(){
        List<Trade> tradeList = tradeService.selectTradeList();
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(200);
        resultMap.setResult("success");
        resultMap.setData(tradeList);
        resultMap.setMessage("查询成功");
        return resultMap;
    }
}
