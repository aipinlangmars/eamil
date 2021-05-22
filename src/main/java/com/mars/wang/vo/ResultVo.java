package com.mars.wang.vo;

import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;
import java.util.List;

public class ResultVo implements Serializable {

    private boolean success;

    private List<T> list;

    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
