package com.mars.wang.dao;

import com.mars.wang.domain.Data1025;
import com.mars.wang.domain.Data1039;
import com.mars.wang.domain.ParentData;
import com.mars.wang.utils.getdata.Parent;

import java.util.List;

public interface EmailDao1025 {


    void insert1025(List<Data1025> list);

    void update1025(Data1025 data1025);

    List<ParentData> getList1025(String s);
}
