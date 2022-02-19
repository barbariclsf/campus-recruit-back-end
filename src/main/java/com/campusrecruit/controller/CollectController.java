package com.campusrecruit.controller;


import com.campusrecruit.pojo.DO.Collect;
import com.campusrecruit.pojo.DO.Company;
import com.campusrecruit.pojo.DO.Postion;
import com.campusrecruit.pojo.DO.Trade;
import com.campusrecruit.pojo.VO.PostionAndCompanyVO;
import com.campusrecruit.pojo.ResultMap;
import com.campusrecruit.service.CollectService;
import com.campusrecruit.service.CompanyService;
import com.campusrecruit.service.PostionService;
import com.campusrecruit.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private PostionService postionService;

    @Autowired
    private TradeService tradeService;


    @PostMapping("/deleteCollect")
    public ResultMap deleteCollect(@RequestParam("userId") String userId,
                                   @RequestParam("contentId") String contentId){
       int res =  collectService.deleteOne(Integer.valueOf(userId),Integer.valueOf(contentId));
       ResultMap resultMap = new ResultMap();
        if(res > 0){
            resultMap.setCode(200);
            resultMap.setResult("success");
            resultMap.setMessage("删除成功");
        }else{
            resultMap.setCode(201);
            resultMap.setResult("error");
            resultMap.setMessage("删除失败");
        }
        return resultMap;
    }
    /**
     * 查询收藏根据userid和收藏id
     * @param userId
     * @param contentId
     * @return
     */
    @PostMapping("/selectOneCollect")
    public ResultMap selectOneCollect(@RequestParam("userId") String userId,
                                      @RequestParam("contentId") String contentId){

        Collect collect = collectService.selectByUserIdAndContenId(Integer.valueOf(userId),Integer.valueOf(contentId));
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(200);
        resultMap.setData(collect);
        resultMap.setResult("success");
        resultMap.setMessage("查看成功");
        return  resultMap;
    }

    /**
     * 插入
     * @param userId
     * @param contentId
     * @param type
     * @return
     */
    @PostMapping("/saveCollect")
    public ResultMap saveCollect(@RequestParam("userId") String userId,
                                 @RequestParam("contentId") String contentId,
                                 @RequestParam("type") String type){
        Collect collect = new Collect();
        collect.setUserId(Integer.valueOf(userId)).setContentId(Integer.valueOf(contentId)).setType(Integer.valueOf(type));
        int res = collectService.insertOne(collect);
        ResultMap resultMap = new ResultMap();
        if(res > 0){
            resultMap.setCode(200);
            resultMap.setResult("success");
            resultMap.setMessage("收藏成功");
        }else{
            resultMap.setCode(201);
            resultMap.setResult("error");
            resultMap.setMessage("收藏失败");
        }
        return resultMap;
    }

    /**
     * 查询所有
     * @param userId
     * @return
     */
    @PostMapping("/selectCollect")
    public ResultMap selectCollect(@RequestParam("userId") String userId){
        List<Collect> collectList = collectService.selectByUserId(Integer.valueOf(userId));
        List<PostionAndCompanyVO> pcs1 = new ArrayList<>();
        List<PostionAndCompanyVO> pcs2 = new ArrayList<>();

        collectList.forEach(item -> {
            PostionAndCompanyVO pc = new PostionAndCompanyVO();
            int id = item.getContentId();
            if(item.getType() == 0){
                Postion postion = postionService.selectById(id);
                Company company = companyService.selectById(postion.getCompanyId());
                Trade trade = tradeService.selectById(company.getTrade());
                pc.setPostion(postion);
                pc.setCompany(company);
                pc.setTrade(trade);
                pcs1.add(pc);
            }else{
                Company company = companyService.selectById(id);
                Trade trade = tradeService.selectById(company.getTrade());
                pc.setTrade(trade);
                pc.setCompany(company);
                pcs2.add(pc);
            }
        });
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(200);
        resultMap.setResult("success");
        resultMap.setData(pcs1);
        resultMap.setArrayList(pcs2);
        resultMap.setMessage("查询成功");
        return resultMap;
    }

}
