package com.campusrecruit.controller;

import com.campusrecruit.pojo.DO.Manager;
import com.campusrecruit.pojo.ResultMap;
import com.campusrecruit.service.ManagerService;
import com.campusrecruit.shiro.UserToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @PostMapping(value = "/doLogin")
    public ResultMap doLogin(@RequestParam("manager_id") String managerId,
                             @RequestParam("password") String password,
                             HttpServletRequest request) {
        ResultMap resultMap = new ResultMap();
        Subject subject = SecurityUtils.getSubject();
        try {
            UserToken token = new UserToken(managerId, password, "Manager");
            subject.login(token);
            Manager manager = managerService.selectById(Integer.valueOf(managerId));
            manager.setLastLoginDate(new Date());
            managerService.updateOne(manager);
            manager.setPassword("****");
            manager.setSalt("****");
            request.getSession().setAttribute("manager", manager);
            resultMap.setCode(200);
            resultMap.setData(manager);
            resultMap.setResult("success");
            resultMap.setMessage("登入成功");
        } catch (UnsupportedOperationException e) {
            resultMap.setCode(201);
            resultMap.setResult("error");
            resultMap.setMessage("没有该用户");

        } catch (IncorrectCredentialsException e) {
            resultMap.setCode(201);
            resultMap.setResult("error");
            resultMap.setMessage("密码错误");
        }
        return resultMap;
    }
}
