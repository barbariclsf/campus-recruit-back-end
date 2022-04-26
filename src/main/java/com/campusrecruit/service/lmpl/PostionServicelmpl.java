package com.campusrecruit.service.lmpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.campusrecruit.mapper.PostionMapper;
import com.campusrecruit.pojo.DO.Postion;
import com.campusrecruit.service.PostionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostionServicelmpl implements PostionService {

    @Autowired
    private PostionMapper postionMapper;

    @Override
    public List<Postion> selectPostionList() {
        QueryWrapper<Postion> wrapper = new QueryWrapper<>();
        wrapper.eq("state",1);
        return postionMapper.selectList(wrapper);
    }

    @Override
    public Postion selectById(int postionId) {
        QueryWrapper<Postion> wrapper = new QueryWrapper<>();
        wrapper.eq("postion_id",postionId);
        return postionMapper.selectOne(wrapper);
    }

    @Override
    public List<Postion> selectPostionListByCId(Integer cId) {
        QueryWrapper<Postion> wrapper = new QueryWrapper<>();
        wrapper.eq("company_id",cId);
        return postionMapper.selectList(wrapper);

    }

    @Override
    public List<Postion> selectByPubId(Integer userId) {
        QueryWrapper<Postion> wrapper = new QueryWrapper<>();
        wrapper.eq("publicer_id",userId);
        wrapper.eq("state",1);
        return postionMapper.selectList(wrapper);
    }


    @Override
    public int updateOne(Postion postion) {
        return postionMapper.updateById(postion);
    }

    @Override
    public int deleteOne(Integer postionId) {
        QueryWrapper<Postion> wrapper = new QueryWrapper<>();
        wrapper.eq("postion_id",postionId);
        return  postionMapper.delete(wrapper);
    }

    @Override
    public List<Postion> searchByPostionName(String content) {
        QueryWrapper<Postion> wrapper = new QueryWrapper<>();
        wrapper.like("postion_name",content);
        return postionMapper.selectList(wrapper);
    }

    @Override
    public List<Postion> selectAllPostionList() {
        return postionMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public int insertOne(Postion postion) {
        return postionMapper.insert(postion);
    }
}
