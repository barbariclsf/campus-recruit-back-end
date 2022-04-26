package com.campusrecruit.controller;

import com.alibaba.fastjson.JSONObject;
import com.campusrecruit.pojo.DO.User;
import com.campusrecruit.pojo.ResultMap;
import com.campusrecruit.pojo.UserConstantInterface;
import com.campusrecruit.service.UserService;
import com.campusrecruit.shiro.UserToken;
import com.campusrecruit.utils.GenerateIdUtil;
import com.campusrecruit.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/selectByUserId")
    public ResultMap selectByUserId(@RequestParam("userId") String userId){
        User user = userService.selectByUserId(Integer.valueOf(userId));
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(200);
        resultMap.setResult("success");
        resultMap.setMessage("查看成功");
        resultMap.setData(user);
        return  resultMap;
    }

    @PostMapping("/updateUserInfo")
    public ResultMap updateUserInfo(@RequestParam("userId") String userId,
                                    @RequestParam("userName") String userName,
                                    @RequestParam("sex") String sex,
                                    @RequestParam("birth") String birth,
                                    @RequestParam("email") String email,
                                    @RequestParam("telephone") String telephone,
                                    @RequestParam("address") String address,
                                    @RequestParam("highestDegree") String highestDegree,
                                    @RequestParam("schoolName") String schoolName,
                                    @RequestParam("major") String major,
                                    @RequestParam("graduationYear") String graduationYear){
        User user = userService.selectByUserId(Integer.valueOf(userId));
        user.setUserName(userName);
        user.setSex(sex);
        user.setBirth(birth);
        user.setEmail(email);
        user.setTelephone(telephone);
        user.setAddress(address);
        user.setHighestDegree(highestDegree);
        user.setSchoolName(schoolName);
        user.setMajor(major);
        user.setGraduationYear(graduationYear);
        userService.updateOne(user);
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(200);
        resultMap.setResult("success");
        log.info(userName +"更新个人信息");
        resultMap.setMessage("更新个人信息成功");
        resultMap.setData(user);
        return  resultMap;
    }


    /**
     * 登入
     * @param code
     * @param userName
     * @param userAvatar
     * @param userAddress
     * @param userSex
     * @param request
     * @return
     */
    @PostMapping( "/doLogin")
    public ResultMap doLogin(@RequestParam("code") String code,
                             @RequestParam("userName") String userName,
                             @RequestParam("userAvatar") String userAvatar,
                             @RequestParam("userAddress") String userAddress,
                             @RequestParam("userSex") String userSex,
                             HttpServletRequest request) {
        JSONObject jsonObject = getOpenId(code);
        //获取openid,微信用户标识
        String openId = jsonObject.get("openid").toString();
        String sessionId = request.getSession().getId();
        User user = userService.selectByOpenId(openId);
        Subject subject = SecurityUtils.getSubject();
        ResultMap resultMap = new ResultMap();
        if (!ObjectUtils.isEmpty(user)) {
            String uid = String.valueOf(user.getUserId());
            //以用户身份登入
            subject.login(new UserToken(uid, openId, "User"));
            user = userService.selectByOpenId(openId);
            log.info(userName + "在" + new Date() + "登录");
            resultMap.setCode(200);
            resultMap.setResult("success");
            resultMap.setMessage("登录成功");
            resultMap.setData(user);
            resultMap.setSessionId(sessionId);

        } else {
            //注册
            int userId = Integer.valueOf(GenerateIdUtil.generateUID());
            //生成唯一id
            synchronized (this) {
                while (userService.selectByUserId(userId) != null) {
                    userId = Integer.valueOf(GenerateIdUtil.generateUID());
                }
            }
            user = new User();
            user.setUserId(userId);
            user.setOpenId(openId);
            user.setAddress(userAddress);
            user.setSex(userSex);
            user.setUserName(userName);
            user.setAvatar(userAvatar);
            user.setType(0);
            int res = userService.insertOne(user);
            if (res > 0) {
                String uid = String.valueOf(user.getUserId());
                subject.login(new UserToken(uid, openId, "User"));
                user = userService.selectByOpenId(openId);
                resultMap.setCode(200);
                resultMap.setResult("success");
                resultMap.setMessage("用户登录成功");
                resultMap.setData(user);
                resultMap.setSessionId(sessionId);
            } else {
                resultMap.setCode(201);
                resultMap.setResult("error");
                resultMap.setMessage("用户登录失败");
            }
        }
        return resultMap;

    }

    public static JSONObject getOpenId(String code) {
        Map<String, String> param = new HashMap<>();
        param.put("appid", UserConstantInterface.WX_LOGIN_APPID);
        param.put("secret", UserConstantInterface.WX_LOGIN_SECRET);
        param.put("js_code", code);
        param.put("grant_type", UserConstantInterface.WX_LOGIN_GRANT_TYPE);
        String wxResult = HttpClientUtil.sendPost(UserConstantInterface.WX_LOGIN_URL, param);
        JSONObject jsonObject = JSONObject.parseObject(wxResult);
        return jsonObject;
    }
}
