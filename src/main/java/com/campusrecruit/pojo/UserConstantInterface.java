package com.campusrecruit.pojo;


public interface UserConstantInterface {
    /**
     * 请求的网址
     */
    public static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";
    /**
     * appid
     */
    public static final String WX_LOGIN_APPID = "wx1e877c6cc706be14";
    /**
     * 秘钥
     */
    public static final String WX_LOGIN_SECRET = "084ed2364a59348a26f7271ae123f864";
    /**
     * 固定的参数
     */
    public static final String WX_LOGIN_GRANT_TYPE = "authorization_code";

}
