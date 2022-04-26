package com.campusrecruit.controller;


import com.campusrecruit.pojo.DO.AttachmentResume;
import com.campusrecruit.pojo.ResultMap;
import com.campusrecruit.service.AttachmentResumeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/attachmentResume")
@Slf4j
public class AttachmentResumeController {

    @Autowired
    private AttachmentResumeService attachmentResumeService;



    /**
     * 删除
     * @param id
     * @return
     */
    @PostMapping("/deleteResume")
    public ResultMap deleteResume(@RequestParam("id") String id){
        AttachmentResume att = attachmentResumeService.selectById(Integer.valueOf(id));
        att.setState(0);
        int res = attachmentResumeService.updateOne(att);
        ResultMap resultMap = new ResultMap();
        if(res > 0){
            resultMap.setCode(200);
            log.info(att.getUserId() + "删除简历附件");
            resultMap.setResult("success");
            resultMap.setMessage("删除成功");
        }else{
            resultMap.setCode(201);
            resultMap.setResult("error");
            resultMap.setMessage("删除失败");
        }
        return  resultMap;
    }
    /**
     * 根据用户id查找所有简历
     * @param userId
     * @return
     */
    @PostMapping("/selectResume")
    public ResultMap selectResume(@RequestParam("userId") String userId){
        List<AttachmentResume> attList = attachmentResumeService.selectByUserId(Integer.valueOf(userId));
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(200);
        resultMap.setData(attList);
        resultMap.setResult("success");
        resultMap.setMessage("查询成功");
        return  resultMap;
    }

    /**
     * 插入简历数据
     * @param userId
     * @param resumeName
     * @param resumeUrl
     * @return
     */
    @PostMapping("/saveResume")
    public ResultMap saveResume(@RequestParam("userId") String userId,
                                @RequestParam("resumeName") String resumeName,
                                @RequestParam("resumeUrl") String resumeUrl){
        AttachmentResume att = new AttachmentResume();
        att.setUserId(Integer.valueOf(userId));
        att.setResumeName(resumeName);
        att.setResumeUrl(resumeUrl);
        att.setPublicDate(new Date());
        att.setState(1);
        int res = attachmentResumeService.insertOne(att);
        ResultMap resultMap = new ResultMap();
        if(res > 0){
            resultMap.setCode(200);
            resultMap.setData(att);
            log.info(att.getUserId() + "增加简历附件");
            resultMap.setResult("success");
            resultMap.setMessage("保存成功");
        }else{
            resultMap.setCode(201);
            resultMap.setResult("error");
            resultMap.setMessage("保存失败");
        }
        return resultMap;

    }
}
