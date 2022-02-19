package com.campusrecruit.pojo.VO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DeliverVo {
    private  int deliverId;
    private String deliverName;
    private String resumeName;
    private String resumeUrl;
    private Date deliverDate;
    private int type;
}
