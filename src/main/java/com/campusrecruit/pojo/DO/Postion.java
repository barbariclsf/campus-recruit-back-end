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
 * t_postion
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_postion")
public class Postion implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 职位id
     */
    private Integer postionId;

    /**
     * 公司id
     */
    private Integer companyId;

    /**
     * 发布者id
     */
    private Integer publicerId;

    /**
     * 职位名称
     */
    private String postionName;

    /**
     * 薪资
     */
    private String salary;

    /**
     * 学历要求
     */
    private String demandEducation;

    /**
     * 专业要求
     */
    private String demandMajor;

    /**
     * 职位描述
     */
    private String description;

    /**
     * 工作地点
     */
    private String location;

    /**
     * 发布时间
     */
    private Date publicDate;

    /**
     * 简历投递数量
     */
    private Integer num;


}