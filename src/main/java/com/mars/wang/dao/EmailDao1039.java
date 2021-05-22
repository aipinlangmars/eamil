package com.mars.wang.dao;

import com.mars.wang.domain.Data1025;
import com.mars.wang.domain.Data1039;
import com.mars.wang.domain.ParentData;

import java.util.List;

public interface EmailDao1039 {

    void insert1039(List<Data1039> list);
    int select();

    void update1039(Data1039 data1039);

    List<ParentData> getList1039(String s);

}
