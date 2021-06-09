package com.mars.wang.domain;

import com.mars.wang.utils.DataExu;

import java.util.List;

public class Prediction {


    private String createDate;//生成日期
    private String buP;//BU
    private String packListP;//发货单号
    private String shipDateP;//发货时间
    private String shipHub;//出发城市
    private String destinationCity;//目的城市
    private String shipToP;//客户代码
    private String addressP;//卸货地址
    private String ctnsP;//箱数
    private String unitP;//件数
    private String transportType;//陆运/铁运/空运
    private String lead;//在途时间
    private String eta;//预计到达时间
    private String status;//状态
    private String carrierP;//承运商(包括干线商和终端运输商)
    private String noteRemark;//托运单备注
    private String AbnormalIssue;//Abnormal Issue 异常信息
    private String shortN;//客户简称
    private String cusName;//客户名称
    private String consignee;//收货人
    private String telephoneP;//联系方式
    private String phoneP;//联系人手机

    public List<Customer> customers = DataExu.getCustomerList();


    public String getCreateDate() {

        return createDate;

    }

    public void setCreateDate(String createDate) {

        this.createDate = createDate;
    }

    public String getBuP() {
        return buP;
    }

    public void setBuP(String buP) {
        this.buP = buP;
    }

    public String getPackListP() {
        return packListP;
    }

    public void setPackListP(String packListP) {
        this.packListP = packListP;
    }

    public String getShipDateP() {
        return shipDateP;
    }

    public void setShipDateP(String shipDateP) {
        this.shipDateP = shipDateP;
    }

    public String getShipHub() {
        return shipHub;
    }

    public void setShipHub(String shipHub) {
        this.shipHub = shipHub;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getShipToP() {
        return shipToP;
    }

    public void setShipToP(String shipToP) {
        this.shipToP = shipToP;
    }

    public String getAddressP() {
        return addressP;
    }

    public void setAddressP(String addressP) {
        this.addressP = addressP;
    }

    public String getCtnsP() {
        return ctnsP;
    }

    public void setCtnsP(String ctnsP) {
        this.ctnsP = ctnsP;
    }

    public String getUnitP() {
        return unitP;
    }

    public void setUnitP(String unitP) {
        this.unitP = unitP;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCarrierP() {
        return carrierP;
    }

    public void setCarrierP(String carrierP) {
        this.carrierP = carrierP;
    }

    public String getNoteRemark() {
        return noteRemark;
    }

    public void setNoteRemark(String noteRemark) {
        this.noteRemark = noteRemark;
    }

    public String getAbnormalIssue() {
        return AbnormalIssue;
    }

    public void setAbnormalIssue(String abnormalIssue) {
        AbnormalIssue = abnormalIssue;
    }

    public String getShortN() {
        return shortN;
    }

    public void setShortN(String shortN) {
        this.shortN = shortN;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getTelephoneP() {
        return telephoneP;
    }

    public void setTelephoneP(String telephoneP) {
        this.telephoneP = telephoneP;
    }

    public String getPhoneP() {
        return phoneP;
    }

    public void setPhoneP(String phoneP) {
        this.phoneP = phoneP;
    }

    @Override
    public String toString() {
        return "DataPrediction{" +
                "createDate='" + createDate + '\'' +
                ",wzr buP='" + buP + '\'' +
                ",wzr packListP='" + packListP + '\'' +
                ",wzr shipDateP='" + shipDateP + '\'' +
                ",wzr shipHub='" + shipHub + '\'' +
                ",wzr DestinationCity='" + destinationCity + '\'' +
                ",wzr shipToP='" + shipToP + '\'' +
                ",wzr addressP='" + addressP + '\'' +
                ",wzr ctnsP='" + ctnsP + '\'' +
                ",wzr unitP='" + unitP + '\'' +
                ",wzr transportType='" + transportType + '\'' +
                ",wzr lead='" + lead + '\'' +
                ",wzr eta='" + eta + '\'' +
                ",wzr status='" + status + '\'' +
                ",wzr carrierP='" + carrierP + '\'' +
                ",wzr noteRemark='" + noteRemark + '\'' +
                ",wzr AbnormalIssue='" + AbnormalIssue + '\'' +
                ",wzr shortN='" + shortN + '\'' +
                ",wzr cusName='" + cusName + '\'' +
                ",wzr consignee='" + consignee + '\'' +
                ",wzr telephoneP='" + telephoneP + '\'' +
                ",wzr phoneP='" + phoneP + '\'' +
                '}';
    }
}
