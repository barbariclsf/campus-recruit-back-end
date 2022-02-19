package com.campusrecruit.controller;


import com.campusrecruit.pojo.DO.Company;
import com.campusrecruit.pojo.DO.Postion;
import com.campusrecruit.pojo.DO.Trade;
import com.campusrecruit.pojo.VO.PostionAndCompanyVO;
import com.campusrecruit.pojo.ResultMap;
import com.campusrecruit.service.CompanyService;
import com.campusrecruit.service.PostionService;
import com.campusrecruit.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CommonController {

    @Autowired
    private PostionService postionService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private TradeService tradeService;

    @PostMapping("/search")
    public ResultMap search(@RequestParam("content") String content){
        List<Postion> postionList = postionService.searchByPostionName(content);
        List<Company> companyList = companyService.searchByCompanyName(content);

        List<PostionAndCompanyVO> pcs1 = new ArrayList<>();
        List<PostionAndCompanyVO> pcs2 = new ArrayList<>();
        postionList.forEach(item -> {
            PostionAndCompanyVO p1 = new PostionAndCompanyVO();
            Company company = companyService.selectById(item.getCompanyId());
            Trade trade = tradeService.selectById(item.getId());
            p1.setTrade(trade);
            p1.setCompany(company);
            p1.setPostion(item);
            pcs1.add(p1);
        });
        companyList.forEach(item -> {
            PostionAndCompanyVO p2 = new PostionAndCompanyVO();
            Trade trade = tradeService.selectById(item.getTrade());
            p2.setTrade(trade);
            p2.setCompany(item);
            pcs2.add(p2);
        });
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(200);
        resultMap.setData(pcs1);
        resultMap.setArrayList(pcs2);
        resultMap.setResult("success");
        return resultMap;
    }
}
