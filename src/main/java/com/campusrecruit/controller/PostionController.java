package com.campusrecruit.controller;

import com.campusrecruit.pojo.DO.Company;
import com.campusrecruit.pojo.DO.Postion;
import com.campusrecruit.pojo.DO.Trade;
import com.campusrecruit.pojo.VO.PostionAndCompanyVO;
import com.campusrecruit.pojo.ResultMap;
import com.campusrecruit.service.CompanyService;
import com.campusrecruit.service.PostionService;
import com.campusrecruit.service.TradeService;
import com.campusrecruit.utils.GenerateIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/postion")
@Slf4j
public class PostionController {
    @Autowired
    private PostionService postionService;
    @Autowired
    private CompanyService companyService;

    @Autowired
    private TradeService tradeService;

    /**
     * 新增职位
     *
     * @param companyId
     * @param publicerId
     * @param postionName
     * @param salary
     * @param demandEducation
     * @param demandMajor
     * @param description
     * @param location
     * @return
     */
    @PostMapping("/savePostion")
    public ResultMap savePostion(@RequestParam("companyId") String companyId,
                                 @RequestParam("publicerId") String publicerId,
                                 @RequestParam("postionName") String postionName,
                                 @RequestParam("salary") String salary,
                                 @RequestParam("num") String num,
                                 @RequestParam("demandEducation") String demandEducation,
                                 @RequestParam("demandMajor") String demandMajor,
                                 @RequestParam("description") String description,
                                 @RequestParam("location") String location) {
        Postion postion = new Postion();
        int pid = Integer.valueOf(GenerateIdUtil.generateUID());
        synchronized (this) {
            while (postionService.selectById(pid) != null) {
                pid = Integer.valueOf(GenerateIdUtil.generateUID());
            }
        }
        postion.setPostionId(pid);
        postion.setPublicerId(Integer.valueOf(publicerId));
        postion.setCompanyId(Integer.valueOf(companyId));
        postion.setPostionName(postionName);
        postion.setSalary(salary);
        postion.setNum(Integer.valueOf(num));
        postion.setDemandEducation(demandEducation);
        postion.setDemandMajor(demandMajor);
        postion.setDescription(description);
        postion.setLocation(location);
        postion.setPublicDate(new Date());
        postion.setState(1);
        int res = postionService.insertOne(postion);
        ResultMap resultMap = new ResultMap();
        if (res > 0) {
            resultMap.setCode(200);
            resultMap.setResult("success");
            resultMap.setMessage("保存成功");
        } else {
            resultMap.setCode(201);
            resultMap.setResult("error");
            resultMap.setMessage("保存失败");
        }
        return resultMap;
    }

    @PostMapping("/deletePostion")
    public ResultMap deletePostion(@RequestParam("postionId") String postionId) {
        Postion postion = postionService.selectById(Integer.valueOf(postionId));
        postion.setState(0);
        int res = postionService.updateOne(postion);
        ResultMap resultMap = new ResultMap();
        if (res > 0) {
            resultMap.setCode(200);
            resultMap.setResult("success");
            resultMap.setMessage("删除成功");
        } else {
            resultMap.setCode(201);
            resultMap.setResult("error");
            resultMap.setMessage("删除失败");
        }
        return resultMap;


    }

    @PostMapping("/updatePostion")
    public ResultMap updatePostion(@RequestParam("postionId") String postionId,
                                   @RequestParam("postionName") String postionName,
                                   @RequestParam("salary") String salary,
                                   @RequestParam("num") String num,
                                   @RequestParam("demandEducation") String demandEducation,
                                   @RequestParam("demandMajor") String demandMajor,
                                   @RequestParam("description") String description,
                                   @RequestParam("location") String location) {
        Postion postion = postionService.selectById(Integer.valueOf(postionId));
        postion.setPostionName(postionName);
        postion.setSalary(salary);
        postion.setDemandEducation(demandEducation);
        postion.setDemandMajor(demandMajor);
        postion.setDescription(description);
        postion.setLocation(location);
        postion.setNum(Integer.valueOf(num));
        postion.setPublicDate(new Date());
        Company company = companyService.selectById(postion.getCompanyId());
        company.setPostionNum(company.getPostionNum() + 1);
        companyService.updateOne(company);
        postionService.updateOne(postion);
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(200);
        resultMap.setResult("success");
        resultMap.setMessage("更新成功");
        return resultMap;
    }

    /**
     * 职位列表
     *
     * @return
     */
    @GetMapping
    public ResultMap selectPostionList() {
        List<PostionAndCompanyVO> pcs = new ArrayList<>();
        List<Postion> postionList = postionService.selectPostionList();
        postionList.forEach(item -> {
            PostionAndCompanyVO pc = new PostionAndCompanyVO();
            Company company = companyService.selectById(item.getCompanyId());
            Trade trade = tradeService.selectById(company.getTrade());
            pc.setCompany(company);
            pc.setPostion(item);
            pc.setTrade(trade);
            pcs.add(pc);
        });
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(200);
        resultMap.setResult("success");
        resultMap.setData(pcs);
        resultMap.setMessage("查询成功");
        return resultMap;
    }


    @PostMapping("/selectByPubId")
    public ResultMap selectByPubId(@RequestParam("userId") String userId) {
        List<PostionAndCompanyVO> pcs = new ArrayList<>();
        List<Postion> postionList = postionService.selectByPubId(Integer.valueOf(userId));
        postionList.forEach(item -> {
            PostionAndCompanyVO pc = new PostionAndCompanyVO();
            Company company = companyService.selectById(item.getCompanyId());
            Trade trade = tradeService.selectById(company.getTrade());
            pc.setCompany(company);
            pc.setPostion(item);
            pc.setTrade(trade);
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
     * 根据职位id查找
     *
     * @param postionId
     * @return
     */
    @PostMapping("/selectPostion")
    public ResultMap selectPostion(@RequestParam("postionId") String postionId) {

        Postion postion = postionService.selectById(Integer.valueOf(postionId));
        Company company = companyService.selectById(postion.getCompanyId());
        Trade trade = tradeService.selectById(company.getTrade());

        ResultMap resultMap = new ResultMap();
        resultMap.setCode(200);
        resultMap.setResult("success");
        List<Object> list = new ArrayList<>();
        list.add(company);
        list.add(postion);
        list.add(trade);
        resultMap.setData(list);
        resultMap.setMessage("查询成功");
        return resultMap;
    }
}
