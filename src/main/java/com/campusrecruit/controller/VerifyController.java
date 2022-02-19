package com.campusrecruit.controller;

import com.campusrecruit.pojo.DO.User;
import com.campusrecruit.pojo.DO.Verify;
import com.campusrecruit.pojo.ResultMap;
import com.campusrecruit.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.jnlp.IntegrationService;
import java.util.Date;

@RestController
@RequestMapping("/verify")
public class VerifyController {
    @Autowired
    private VerifyService verifyService;

    @Autowired
    private UserService userService;





    @PostMapping("/selectVerify")
    public ResultMap selectByUserId(@RequestParam("userId") String userId) {

        Verify verify = verifyService.selectByUserId(Integer.valueOf(userId));
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(200);
        resultMap.setData(verify);
        resultMap.setResult("success");
        resultMap.setMessage("查询成功");
        return resultMap;
    }

    /**
     * 申请认证
     *
     * @param companyId
     * @param department
     * @param userName
     * @param userId
     * @param jobNumber
     * @return
     */
    @PostMapping("/saveVerify")
    public ResultMap saveVerify(@RequestParam("companyId") String companyId,
                                @RequestParam("department") String department,
                                @RequestParam("userName") String userName,
                                @RequestParam("userId") String userId,
                                @RequestParam("jobNumber") String jobNumber) {

        Verify verify = new Verify();
        verify.setCompanyId(Integer.valueOf(companyId)).setDepartment(department).setUserName(userName)
                .setUserId(Integer.valueOf(userId)).setJobNumber(jobNumber).setApplyDate(new Date()).setState(0);
        int res = verifyService.insertOne(verify);
        ResultMap resultMap = new ResultMap();
        if (res > 0) {
            resultMap.setCode(200);
            resultMap.setResult("success");
            resultMap.setMessage("保存成功");
        } else {
            resultMap.setCode(201);
            resultMap.setResult("error");
            resultMap.setMessage("保存失败！");
        }
        return resultMap;
    }
    @PostMapping("/verifySuccess")
    public ResultMap verifySuccess(@RequestParam("userId") String userId){
        Verify verify = verifyService.selectByUserId(Integer.valueOf(userId));
        verify.setState(1);
        verifyService.updataOne(verify);
        User user = userService.selectByUserId(Integer.valueOf(userId));
        user.setType(1);
        userService.updateOne(user);
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(200);
        resultMap.setResult("success");
        resultMap.setMessage("认证审核成功");
        return  resultMap;
    }

}
