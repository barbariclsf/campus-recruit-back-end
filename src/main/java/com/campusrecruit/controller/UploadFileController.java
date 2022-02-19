package com.campusrecruit.controller;


import com.campusrecruit.pojo.ResultMap;
import com.campusrecruit.utils.QiniuUploadUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.util.Map;


@RestController
@RequestMapping("/upload")
public class UploadFileController {

    public static final String DEV_UPLOAD_PATH = "D:/upload/";


    /**
     * 上传简历文件
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/uploadFile")
    @ResponseBody
    public ResultMap uploadUserImg(MultipartRequest request) {
        Map<String, MultipartFile> fileMap = request.getFileMap();
        ResultMap resultMap = new ResultMap();
        if (fileMap.size() > 0) {
            for (String str : fileMap.keySet()) {
                MultipartFile multipartFile = fileMap.get(str);
                String res = QiniuUploadUtil.uploadFile(multipartFile);
                if (res != null) {
                    resultMap.setCode(200);
                    resultMap.setData(res);
                    resultMap.setResult("success");
                    resultMap.setMessage("上传成功！");
                }else{
                    resultMap.setCode(201);
                    resultMap.setResult("error");
                    resultMap.setMessage("上传失败！");
                }
            }
        } else {
            resultMap.setCode(201);
            resultMap.setResult("error");
            resultMap.setMessage("不能为空");
        }
        return resultMap;
    }

}