package com.campusrecruit.pojo.VO;

import com.campusrecruit.pojo.DO.Company;
import com.campusrecruit.pojo.DO.Postion;
import com.campusrecruit.pojo.DO.Trade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PostionAndCompanyVO {
    private Postion postion;
    private Company company;
    private Trade trade;
}
