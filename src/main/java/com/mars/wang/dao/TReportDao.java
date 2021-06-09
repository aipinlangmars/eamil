package com.mars.wang.dao;

import com.mars.wang.domain.wci.TReport;

import java.util.List;

public interface TReportDao {
    //集合插入
    void insertTReport(List<TReport> tReports);



    //单挑数据插入
    void insertTReportByOne(TReport tReport);

    //数据修改

    void updateTReport(TReport tReport);


}
