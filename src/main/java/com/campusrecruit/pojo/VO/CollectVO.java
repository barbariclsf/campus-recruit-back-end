package com.campusrecruit.pojo.VO;

import com.campusrecruit.pojo.DO.Company;
import com.campusrecruit.pojo.DO.Postion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CollectVO {

    private List<Company> company;
    private List<Postion> postion;
}
