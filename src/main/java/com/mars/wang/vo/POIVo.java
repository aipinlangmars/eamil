package com.mars.wang.vo;

import java.io.InputStream;

public class POIVo {
    private InputStream inputStream;
    private String fileName;
    private String remark;
    public POIVo(){

    }

    public POIVo(InputStream inputStream, String fileName,String remark) {
        this.inputStream = inputStream;
        this.fileName = fileName;
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
