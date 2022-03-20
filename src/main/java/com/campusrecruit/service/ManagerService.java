package com.campusrecruit.service;

import com.campusrecruit.pojo.DO.Manager;
import com.campusrecruit.pojo.DO.User;

import java.util.List;

public interface ManagerService {
    Manager selectById(Integer id);

    int updateOne(Manager manager);


}
