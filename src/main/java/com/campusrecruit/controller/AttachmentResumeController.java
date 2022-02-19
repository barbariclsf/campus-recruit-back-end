package com.campusrecruit.controller;


import com.campusrecruit.pojo.DO.AttachmentResume;
import com.campusrecruit.pojo.ResultMap;
import com.campusrecruit.service.AttachmentResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/attachmentResume")
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
        int res = attachmentResumeService.deleteOne(Integer.valueOf(id));
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

        int res = attachmentResumeService.insertOne(att);
        System.out.println(att);
        ResultMap resultMap = new ResultMap();
        if(res > 0){
            resultMap.setCode(200);
            resultMap.setData(att);
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
