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
 * t_verify
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_verify")
public class Verify implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 申请者id
     */
    private Integer userId;

    /**
     * 申请者名称
     */
    private String userName;

    /**
     * 公司id
     */
    private Integer companyId;

    /**
     * 部门
     */
    private String department;

    /**
     * 工号
     */
    private String jobNumber;

    /**
     * 申请时间
     */
    private Date applyDate;

    /**
     * 状态
     */
    private Integer state;
}