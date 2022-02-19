package com.campusrecruit.utils;

import com.alibaba.fastjson.JSONObject;

import com.qiniu.common.QiniuException;

import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;

import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


public class QiniuUploadUtil {


    private static final String ACCESS_KEY = "xeEgPGECPo9LPW0y1oahZs6SOUeyxgxC6dkWAG0V";
    private static final String SECRET_KEY = "yzPAiaQZRtvkwOqLYjOSpdn9aWU-wfH9FI2DcaEF";
    private static final String BUCKET = "lsfpdf";
    private static final  String QINIUYUNDOMAIN ="http://r2apv4kp8.hn-bkt.clouddn.com/";

    public static String uploadFile(MultipartFile file)  {
        try {

            Configuration cfg = new Configuration();
            UploadManager uploadManager = new UploadManager(cfg);
            int dotPos = file.getOriginalFilename().lastIndexOf(".");
            if (dotPos < 0) {
                return null;
            }
            String fileExt = file.getOriginalFilename().substring(dotPos + 1).toLowerCase();

            String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExt;
            //获取token
            String token = Auth.create(ACCESS_KEY, SECRET_KEY).uploadToken(BUCKET);
            Response res = uploadManager.put(file.getBytes(), fileName, token);
            System.out.println(res);
            // 打印返回的信息
            if (res.isOK() && res.isJson()) {
                // 返回这张存储照片的地址
                return  QINIUYUNDOMAIN + JSONObject.parseObject(res.bodyString()).getString("key");
            } else {
                return null;
            }
        } catch (QiniuException e) {

            return null;
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }

}

