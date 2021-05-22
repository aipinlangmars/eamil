package com.mars.wang.dao;

import com.mars.wang.domain.City;

import java.util.List;

public interface CityDao {

    void insertCity(List<City> cities);

    String getAriLead(String city);


    String getNormalLead(String city);


    City searchByCity(String city);

    String getAirCarrier(String city);

    String getNormalCarrier(String city);
}
