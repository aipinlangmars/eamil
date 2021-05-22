package com.mars.wang.dao;

import com.mars.wang.domain.Customer;

import java.util.List;

public interface CustomerDao {

    void insertCustomer(List<Customer> customers);


    Customer searchByCode(String code);

}
