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
 * t_company
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_company")
public class Company implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 公司id
     */
    private Integer companyId;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司logo
     */
    private String logo;

    /**
     * 公司类型
     */
    private Integer trade;

    /**
     * 公司规模
     */
    private String scale;

    /**
     * 公司介绍
     */
    private String description;

    /**
     * 工作地点
     */
    private String location;

    /**
     * 在招人数
     */
    private Integer postionNum;


}