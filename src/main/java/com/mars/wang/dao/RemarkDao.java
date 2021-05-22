package com.mars.wang.dao;

import com.mars.wang.domain.City;
import com.mars.wang.domain.Remark;
import com.mars.wang.domain.RemarkPl;

import java.util.List;

public interface RemarkDao {

    void insertRemark(List<RemarkPl> readRemarks);

    int select(String remark);

    void insertRemarkOnly(Remark remark);


}
