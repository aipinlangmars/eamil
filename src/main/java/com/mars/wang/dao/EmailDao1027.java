package com.mars.wang.dao;

import com.mars.wang.domain.Data1025;
import com.mars.wang.domain.Data1027;
import com.mars.wang.domain.Data1039;
import com.mars.wang.domain.ParentData;

import java.util.List;

public interface EmailDao1027 {


    void insert1027(List<Data1027> list);

    void update1027(Data1027 data1027);

    List<ParentData> getList1027(String s);
}
