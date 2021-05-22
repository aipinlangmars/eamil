package com.mars.wang.dao;

import com.mars.wang.domain.PrivateCity;

import java.util.List;

public interface PrivateCityDao {

    List<String> getCity(String address);

}
