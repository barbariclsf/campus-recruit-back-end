package com.campusrecruit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.campusrecruit.pojo.DO.Company;
import org.springframework.stereotype.Component;

@Component
public interface CompanyMapper extends BaseMapper<Company> {
    int deleteByPrimaryKey(Integer id);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);
}