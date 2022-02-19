package com.campusrecruit.utils;

import java.util.Random;

public class GenerateIdUtil {

    public static  String generateUID() {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < 8; i++) {
            //首字母不能为0
            result += (random.nextInt(9) + 1);
        }
        return result;
    }

}
