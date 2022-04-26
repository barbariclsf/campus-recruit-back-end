package com.campusrecruit.controller;

import com.campusrecruit.pojo.DO.*;
import com.campusrecruit.pojo.VO.DeliverVo;
import com.campusrecruit.pojo.VO.PostionAndCompanyVO;
import com.campusrecruit.pojo.ResultMap;
import com.campusrecruit.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/deliver")
@Slf4j
public class DeliverController {

    @Autowired
    private DeliverService deliverService;

    @Autowired
    private AttachmentResumeService attachmentResumeService;

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private PostionService postionService;

    @Autowired
    private TradeService tradeService;

    @PostMapping("/checkDeliver")
    public ResultMap checkDeiver(@RequestParam("userId") String userId,
                                 @RequestParam("postionId") String postionId){
        System.out.println(userId + " " + postionId);
        List<Deliver> delivers = deliverService.checkIsDeliver(Integer.valueOf(userId),Integer.valueOf(postionId));
        ResultMap resultMap = new ResultMap();
        if(delivers.size() > 0){
            resultMap.setCode(200);

            resultMap.setResult("success");
            resultMap.setMessage("已投递");
        }else {
            resultMap.setCode(201);
            resultMap.setResult("error");
            resultMap.setMessage("未投递");
        }
        return resultMap;
    }

    @PostMapping("/saveDeliver")
    public ResultMap saveDeliver(@RequestParam("deliverId") String deliverId,
                                 @RequestParam("resumeId") String resumeId,
                                 @RequestParam("postionId") String postionId,
                                 @RequestParam("publicerId")String publicerId,
                                 @RequestParam("type") String type){
        Deliver deliver = new Deliver();
        deliver.setDeliverId(Integer.valueOf(deliverId));
        deliver.setResumeId(Integer.valueOf(resumeId));
        deliver.setPostionId(Integer.valueOf(postionId));
        deliver.setPublicerId(Integer.valueOf(publicerId));
        deliver.setType(Integer.valueOf(type));
        deliver.setDeliverDate(new Date());
        deliver.setState(0);
        int res = deliverService.insertOne(deliver);
        ResultMap resultMap = new ResultMap();
        if(res > 0){
            resultMap.setCode(200);
            log.info(deliverId + "投递" + deliver.getPostionId() + "职位成功");
            resultMap.setResult("success");
            resultMap.setMessage("新增成功");
        }else{
            resultMap.setCode(202);
            resultMap.setResult("error");
            resultMap.setMessage("新增失败");
        }
        return resultMap;
    }

    @PostMapping("/selectByDeliverId")
    public ResultMap selectByDeliverId(@RequestParam("deliverId") String deliverId){
        List<Deliver> delivers =  deliverService.selectByDeliverId(Integer.valueOf(deliverId));
        List<PostionAndCompanyVO> pcv = new ArrayList<>();
       delivers.forEach(v -> {
           PostionAndCompanyVO p = new PostionAndCompanyVO();
           Postion postion = postionService.selectById(v.getPostionId());
           Company company = companyService.selectById(postion.getCompanyId());
           Trade trade = tradeService.selectById(company.getTrade());
           p.setTrade(trade);
           p.setPostion(postion);
           p.setCompany(company);
           pcv.add(p);
       });
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(200);
        resultMap.setData(pcv);
        resultMap.setResult("success");
        resultMap.setMessage("查询成功");
        return resultMap;
    }
    @PostMapping("/selectByPubId")
    public ResultMap selectByPubId(@RequestParam("publicerId") String publicerId){
        List<Deliver> delivers = deliverService.selectByPublicerId(Integer.valueOf(publicerId));
        List<DeliverVo> deliverVos = new ArrayList<>();
        delivers.forEach(v -> {
            DeliverVo d = new DeliverVo();
           //在线简历
            if(v.getType() == 1){
                int rid = v.getResumeId();
                AttachmentResume att =  attachmentResumeService.selectById(rid);
                d.setResumeUrl(att.getResumeUrl());
                d.setResumeName(att.getResumeName());
                d.setType(1);

            }else{
                d.setType(0);
            }
            int uid = v.getDeliverId();
            User deliver = userService.selectByUserId(uid);
            d.setDeliverId(uid);
            d.setDeliverName(deliver.getUserName());
            d.setDeliverDate(v.getDeliverDate());
            deliverVos.add(d);

        });
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(200);
        resultMap.setData(deliverVos);
        resultMap.setResult("success");
        resultMap.setMessage("查询成功");
        return resultMap;
    }
}
