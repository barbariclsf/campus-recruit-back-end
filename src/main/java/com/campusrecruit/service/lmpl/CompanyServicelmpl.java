package com.campusrecruit.service.lmpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.campusrecruit.mapper.CompanyMapper;
import com.campusrecruit.pojo.DO.Company;
import com.campusrecruit.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServicelmpl implements CompanyService {
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public Company selectById(Integer companyId) {
        QueryWrapper<Company> wrapper = new QueryWrapper<>();
        wrapper.eq("company_id",companyId);
        return companyMapper.selectOne(wrapper);
    }

    @Override
    public List<Company> selectCompanyList() {
        QueryWrapper<Company> wrapper = new QueryWrapper<>();
        wrapper.eq("state",1);
        return companyMapper.selectList(wrapper);
    }

    @Override
    public List<Company> searchByCompanyName(String content) {
        QueryWrapper<Company> wrapper = new QueryWrapper<>();
        wrapper.like("company_name",content);
        return companyMapper.selectList(wrapper);
    }

    @Override
    public int insertOne(Company company) {
        return companyMapper.insert(company);
    }

    @Override
    public int updateOne(Company company) {
        return companyMapper.updateById(company);
    }

    @Override
    public List<Company> selectAllCompanyList() {
        return companyMapper.selectList(new QueryWrapper<>());
    }


}
