package com.campusrecruit.controller;


import com.campusrecruit.pojo.DO.Company;
import com.campusrecruit.pojo.DO.Postion;
import com.campusrecruit.pojo.DO.Trade;
import com.campusrecruit.pojo.VO.PostionAndCompanyVO;
import com.campusrecruit.pojo.ResultMap;
import com.campusrecruit.service.CompanyService;
import com.campusrecruit.service.PostionService;
import com.campusrecruit.service.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/company")
@Slf4j
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private TradeService tradeService;

    @Autowired
    private PostionService postionService;



    /**
     * 公司列表
     * @return
     */
    @GetMapping
    public ResultMap selectCompanyList(){
        List<PostionAndCompanyVO> pcs = new ArrayList<>();
        List<Company> companyList = companyService.selectCompanyList();
        companyList.forEach(item -> {
            PostionAndCompanyVO pc = new PostionAndCompanyVO();
            Trade trade = tradeService.selectById(item.getTrade());
            pc.setTrade(trade);
            pc.setCompany(item);
            pcs.add(pc);
        });
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(200);
        resultMap.setResult("success");
        resultMap.setData(pcs);
        resultMap.setMessage("查询成功");
        return resultMap;
    }

    /**
     * 根据公司id查找
     * @param companyId
     * @return
     */
    @PostMapping("/selectCompany")
    public ResultMap selectCompany(@RequestParam("companyId") String companyId){

        Company company = companyService.selectById(Integer.valueOf(companyId));
        List<Postion> postions = postionService.selectPostionListByCId(Integer.valueOf(companyId));
        Trade trade = tradeService.selectById(company.getTrade());
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(200);
        resultMap.setResult("success");
        List<Object> list = new ArrayList<>();
        list.add(company);
        list.add(postions);
        list.add(trade);
        resultMap.setData(list);
        resultMap.setMessage("查询成功");
        return resultMap;
    }
}
