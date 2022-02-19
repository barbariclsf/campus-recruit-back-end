package com.campusrecruit.pojo.DO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * t_jobintention 求职意向类
 * @author lsf
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_jobintention")
public class JobIntention implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 期望行业
     */
    private String trade;

    /**
     * 期望职位
     */
    private String postion;

    /**
     * 期望薪资
     */
    private String salary;

    /**
     * 期望地点
     */
    private String location;

}