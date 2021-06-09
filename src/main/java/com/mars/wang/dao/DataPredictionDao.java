package com.mars.wang.dao;

import com.mars.wang.domain.Prediction;

import java.util.List;

public interface DataPredictionDao {
    //插入集合数据
    void insertData(List<Prediction> predictions);

    //插入单条数据
    void  insertOneValue(Prediction prediction);

    //更新数据

    void updatePre(Prediction prediction);

}
