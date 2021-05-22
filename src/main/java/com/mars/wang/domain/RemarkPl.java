package com.mars.wang.domain;

public class RemarkPl {

    private String id;

    private String packList;

    private String remarkId;

    private String createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPackList() {
        return packList;
    }

    public void setPackList(String packList) {
        this.packList = packList;
    }

    public String getRemarkId() {
        return remarkId;
    }

    public void setRemarkId(String remarkId) {
        this.remarkId = remarkId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public RemarkPl(String id, String packList, String remarkId, String createTime) {
        this.id = id;
        this.packList = packList;
        this.remarkId = remarkId;
        this.createTime = createTime;
    }

}
