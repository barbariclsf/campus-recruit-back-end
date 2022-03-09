package com.campusrecruit.controller;


import com.campusrecruit.pojo.ResultMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/hello")
    public ResultMap test(){
        ResultMap resultMap = new ResultMap();
        resultMap.setCode(200);
        resultMap.setResult("success");
        return resultMap;
    }
}
