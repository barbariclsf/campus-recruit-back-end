package com.campusrecruit.pojo.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * t_user
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_user")
public class User implements Serializable {

    @TableId(value = "id",type= IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * open_id
     */
    private String openId;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户名字
     */
    private String userName;

    /**
     * 出生日期
     */
    private String birth;

    /**
     * 性别
     */
    private String sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String telephone;

    /**
     * 地址
     */
    private String address;

    /**
     * 最高学历
     */
    private String highestDegree;

    /**
     * 最高学历学校
     */
    private String schoolName;


    /**
     * 专业
     */
    private String major;

    /**
     * 毕业年份
     */
    private String graduationYear;

    /**
     * 用户类型
     */
    private Integer type;

    /**
     * 状态
     */
    private Integer state;

}