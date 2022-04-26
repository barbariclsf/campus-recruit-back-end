package com.campusrecruit.service;

import com.campusrecruit.pojo.DO.Company;

import java.util.List;

public interface CompanyService {
    /**
     * 根据id查找
     * @param companyId
     * @return
     */
    Company selectById(Integer companyId);

    /**
     * 查询公司列表
     * @return
     */
    List<Company> selectCompanyList();


    /**
     * 搜索
     * @param content
     * @return
     */
    List<Company> searchByCompanyName(String content);

    int insertOne(Company company);

    int updateOne(Company company);

    List<Company> selectAllCompanyList();
}
