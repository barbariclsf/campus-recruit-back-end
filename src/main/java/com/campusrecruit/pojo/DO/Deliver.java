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
 * t_deliver
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("t_deliver")
public class Deliver implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
     * 投递者id
     */
    private Integer deliverId;

    /**
     * 简历id
     */
    private Integer resumeId;

    /**
     * 职位id
     */
    private Integer postionId;

    /**
     * 发布者id
     */
    private Integer publicerId;

    /**
     * 投递时间
     */
    private Date deliverDate;

    /**
     * 简历类别
     */
    private Integer type;

    /**
     * 状态
     */
    private Integer state;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeliverId() {
        return deliverId;
    }

    public void setDeliverId(Integer deliverId) {
        this.deliverId = deliverId;
    }

    public Integer getResumeId() {
        return resumeId;
    }

    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }

    public Integer getPostionId() {
        return postionId;
    }

    public void setPostionId(Integer postionId) {
        this.postionId = postionId;
    }

    public Integer getPublicerId() {
        return publicerId;
    }

    public void setPublicerId(Integer publicerId) {
        this.publicerId = publicerId;
    }

    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}