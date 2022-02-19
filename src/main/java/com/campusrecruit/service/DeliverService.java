package com.campusrecruit.service;

import com.campusrecruit.pojo.DO.Deliver;

import java.util.List;

public interface DeliverService {

    int insertOne(Deliver deliver);

    List<Deliver> selectByPublicerId(Integer pubId);

    List<Deliver> selectByDeliverId(Integer valueOf);
}
