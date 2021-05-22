package com.mars.wang.domain;

import java.io.Serializable;

public class Remark implements Serializable {

    private String id;

    private String remark;

    private String createTime;

    public Remark(){

    }


    public Remark(String id,String remark, String creatTime){
        this.id = id;
        this.remark = remark;
        this.createTime = creatTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


}
