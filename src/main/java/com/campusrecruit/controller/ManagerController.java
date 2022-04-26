package com.campusrecruit.controller;

import com.campusrecruit.pojo.DO.*;
import com.campusrecruit.pojo.ResultMap;
import com.campusrecruit.service.*;
import com.campusrecruit.shiro.UserToken;
import com.campusrecruit.utils.GenerateIdUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostionService postionService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private TradeService tradeService;

    @Autowired
    private VerifyService verifyService;

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
        } catch (UnknownAccountException e) {
            resultMap.setCode(201);
            resultMap.setResult("error");
            resultMap.setMessage("账户或者密码错误");
        }
        return resultMap;
    }

    @PostMapping("/selectUserList")
    public ResultMap selectUserList(@RequestParam(value = "userName",required = false) String userName) {
        ResultMap resultMap = new ResultMap();
        List<User> userList = userService.selectUserList();
        if(!StringUtils.isEmpty(userName)){
            List<User> list = new ArrayList<>();
            userList.forEach(item -> {
                if(item.getUserName().equals(userName)){
                    list.add(item);
                }
            });
            resultMap.setData(list);
        }else{
            resultMap.setData(userList);
        }
        resultMap.setCode(200);
        resultMap.setResult("success");
        resultMap.setMessage("查询用户列表成功");
        return resultMap;
    }

    @PostMapping("/selectPostionList")
    public ResultMap selectPostionList(@RequestParam(value = "postionName",required = false)String postionName) {
        ResultMap resultMap = new ResultMap();
        List<Postion> postionList = postionService.selectAllPostionList();
        if(!StringUtils.isEmpty(postionName)){
            List<Postion> list = new ArrayList<>();
            postionList.forEach(item -> {
                if(item.getPostionName().equals(postionName)){
                    list.add(item);
                }
            });
            resultMap.setData(list);
        }else{
            resultMap.setData(postionList);
        }
        resultMap.setCode(200);
        resultMap.setResult("success");
        resultMap.setMessage("查询职位列表成功");
        return resultMap;
    }

    @PostMapping("/selectCompanyList")
    public ResultMap selectCompanyList(@RequestParam(value = "companyName",required = false) String companyName) {
        ResultMap resultMap = new ResultMap();
        List<Company> companyList = companyService.selectAllCompanyList();
        if(!StringUtils.isEmpty(companyName)){
            List<Company> list = new ArrayList<>();
            companyList.forEach(item -> {
                if(item.getCompanyName().equals(companyName)){
                    list.add(item);
                }
            });
            resultMap.setData(list);
        }else{
            resultMap.setData(companyList);
        }
        List<Trade> tradeList = tradeService.selectTradeList();
        resultMap.setCode(200);
        resultMap.setResult("success");
        resultMap.setMessage("查询公司列表成功");
        resultMap.setArrayList(tradeList);
        return resultMap;
    }

    @PostMapping("/selectVerifyList")
    public ResultMap selectVerifyList(@RequestParam(value = "userName",required = false)String userName) {
        ResultMap resultMap = new ResultMap();
        List<Verify> verifyList = verifyService.selectVerifyList();
        if(!StringUtils.isEmpty(userName)){
            List<Verify> list = new ArrayList<>();
            verifyList.forEach(item -> {
                if(item.getUserName().equals(userName)){
                    list.add(item);
                }
            });
            resultMap.setData(list);
        }else{
            resultMap.setData(verifyList);
        }
        resultMap.setCode(200);
        resultMap.setResult("success");
        resultMap.setMessage("查询认证列表成功");
        return resultMap;
    }

    @PostMapping("/addCompany")
    public ResultMap addCompany(@RequestParam("companyName") String companyName,
                                @RequestParam("logo") String companyLogo,
                                @RequestParam("scale") String scale,
                                @RequestParam("trade") String trade,
                                @RequestParam("location") String location,
                                @RequestParam("description") String description) {
        String cid = GenerateIdUtil.generateUID();
        synchronized (this) {
            while (companyService.selectById(Integer.valueOf(cid)) != null) {
                cid = GenerateIdUtil.generateUID();
            }
        }
        Company company = new Company();
        company.setCompanyId(Integer.valueOf(cid));
        company.setCompanyName(companyName);
        company.setLogo(companyLogo);
        company.setScale(scale);
        company.setTrade(Integer.valueOf(trade));
        company.setLocation(location);
        company.setDescription(description);
        company.setPostionNum(0);
        int res = companyService.insertOne(company);
        ResultMap resultMap = new ResultMap();
        if (res > 0) {
            resultMap.setCode(200);
            resultMap.setResult("success");
            resultMap.setMessage("添加公司成功");
        } else {
            resultMap.setCode(201);
            resultMap.setResult("error");
            resultMap.setMessage("添加公司失败");
        }
        return resultMap;
    }

    @PostMapping("/updateCompany")
    public ResultMap updateCompany(
            @RequestParam("companyId") String companyId,
            @RequestParam("companyName") String companyName,
            @RequestParam("logo") String companyLogo,
            @RequestParam("scale") String scale,
            @RequestParam("trade") String trade,
            @RequestParam("location") String location,
            @RequestParam("description") String description) {
        ResultMap resultMap = new ResultMap();
        System.out.println(companyId);
        Company company = companyService.selectById(Integer.valueOf(companyId));
        company.setCompanyName(companyName);
        company.setLogo(companyLogo);
        company.setScale(scale);
        company.setTrade(Integer.valueOf(trade));
        company.setLocation(location);
        company.setDescription(description);
        int res = companyService.updateOne(company);
        if (res > 0) {
            resultMap.setCode(200);
            resultMap.setResult("success");
            resultMap.setMessage("更新公司成功");
        } else {
            resultMap.setCode(201);
            resultMap.setResult("error");
            resultMap.setMessage("更新公司失败");
        }
        return resultMap;
    }

    @PostMapping("/updatePostion")
    public ResultMap updatePostion(
            @RequestParam("postionId") String postionId,
            @RequestParam("postionName") String postionName,
            @RequestParam("num") String num,
            @RequestParam("salary") String salary,
            @RequestParam("location") String location,
            @RequestParam("demandEducation") String demandEducation,
            @RequestParam("description") String desrciption) {
        ResultMap resultMap = new ResultMap();
        Postion postion =  postionService.selectById(Integer.valueOf(postionId));
        postion.setPostionName(postionName);
        postion.setNum(Integer.valueOf(num));
        postion.setSalary(salary);
        postion.setLocation(location);
        postion.setDemandEducation(demandEducation);
        postion.setDescription(desrciption);
        int res = postionService.updateOne(postion);
        if (res > 0) {
            resultMap.setCode(200);
            resultMap.setResult("success");
            resultMap.setMessage("更新职位成功");
        } else {
            resultMap.setCode(201);
            resultMap.setResult("error");
            resultMap.setMessage("更新职位失败");
        }
        return resultMap;
    }

    @PostMapping("/updatePostionStatus")
    public ResultMap updatePostionStatus(@RequestParam("postionId") String postionId) {
        ResultMap resultMap = new ResultMap();
        Postion postion = postionService.selectById(Integer.valueOf(postionId));

        int status = postion.getState() == 0 ? 1 : 0;
        postion.setState(status);
        postionService.updateOne(postion);
        resultMap.setCode(200);
        resultMap.setResult("success");
        resultMap.setMessage("更新职位状态成功");
        return resultMap;
    }

    @PostMapping("/updateVerifyStatus")
    public ResultMap updateVerifyStatus(@RequestParam("id") String id) {
        ResultMap resultMap = new ResultMap();
        Verify verify = verifyService.selectById(Integer.valueOf(id));
        int status = verify.getState() == 0 ? 1 : 0;
        verify.setState(status);
        verifyService.updataOne(verify);
        resultMap.setCode(200);
        resultMap.setResult("success");
        resultMap.setMessage("更新申请状态成功");
        return resultMap;
    }

    @PostMapping("/updateCompanyStatus")
    public ResultMap updateCompanyStatus(@RequestParam("companyId") String companyId) {

        ResultMap resultMap = new ResultMap();
        Company company = companyService.selectById(Integer.valueOf(companyId));

        int status = company.getState() == 0 ? 1 : 0;
        company.setState(status);
        companyService.updateOne(company);
        resultMap.setCode(200);
        resultMap.setResult("success");
        resultMap.setMessage("更新公司状态成功");
        return resultMap;

    }

    @PostMapping("/updateUserStatus")
    public ResultMap updateUserStatus(@RequestParam("userId") String userId) {
        ResultMap resultMap = new ResultMap();
        User u = userService.selectByUserId(Integer.valueOf(userId));
        int status = u.getState() == 0 ? 1 : 0;
        u.setState(status);
        userService.updateOne(u);
        resultMap.setCode(200);
        resultMap.setResult("success");
        resultMap.setMessage("更新用户状态成功");
        return resultMap;

    }
}
