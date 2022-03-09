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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/selectUserList")
    public ResultMap selectUserList() {
        ResultMap resultMap = new ResultMap();
        List<User> userList = managerService.selectUserList();
        resultMap.setCode(200);
        resultMap.setResult("success");
        resultMap.setMessage("查询用户列表成功");
        resultMap.setData(userList);
        return resultMap;
    }

    @GetMapping("/selectPostionList")
    public ResultMap selectPostionList() {
        ResultMap resultMap = new ResultMap();
        List<Postion> postionList = postionService.selectPostionList();
        resultMap.setCode(200);
        resultMap.setResult("success");
        resultMap.setMessage("查询用户列表成功");
        resultMap.setData(postionList);
        return resultMap;
    }

    @GetMapping("/selectCompanyList")
    public ResultMap selectCompanyList() {
        ResultMap resultMap = new ResultMap();
        List<Company> companyList = companyService.selectCompanyList();
        List<Trade> tradeList = tradeService.selectTradeList();
        resultMap.setCode(200);
        resultMap.setResult("success");
        resultMap.setMessage("查询公司列表成功");
        resultMap.setData(companyList);
        resultMap.setArrayList(tradeList);
        return resultMap;
    }

    @GetMapping("/selectVerifyList")
    public ResultMap selectVerifyList() {
        ResultMap resultMap = new ResultMap();
        List<Verify> verifyList = verifyService.selectVerifyList();
        resultMap.setCode(200);
        resultMap.setResult("success");
        resultMap.setMessage("查询公司列表成功");
        resultMap.setData(verifyList);

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
            @RequestParam("demandEducation") String demandEducation) {
        ResultMap resultMap = new ResultMap();
        Postion postion =  postionService.selectById(Integer.valueOf(postionId));
        postion.setPostionName(postionName);
        postion.setNum(Integer.valueOf(num));
        postion.setSalary(salary);
        postion.setLocation(location);
        postion.setDemandEducation(demandEducation);
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
