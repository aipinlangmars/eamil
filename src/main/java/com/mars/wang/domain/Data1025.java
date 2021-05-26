package com.mars.wang.domain;

import com.mars.wang.MyException;
import com.mars.wang.utils.DataExu;
import com.mars.wang.utils.Fomat;
import com.mars.wang.utils.getdata.Parent;
import com.mars.wang.vo.OB;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

public class Data1025  extends ParentData implements Serializable{

    private String bu;
    private String hubCode;
    private String typeCode;
    private String packList;
    private String log_trailer;
    private String carrier;
    private String saphub;
    private String provice;
    private String shipDate;
    private String ctns;
    private String unit;
    private String window;
    private String shipTo;
    private String crdDate;
    private String phDate;
    private String psst;
    private String stName;
    private String phone;
    private String address1;
    private String address2;
    private String address3;
    private String address4;


    //创建时间
    @Override
    public void setCreateDate() {
        String date = Fomat.getString(new Date().getTime());

        super.dataPrediction.setCreateDate(date);

    }
    //产品类型
    @Override
    public void setBuP() {
        String bu = DataExu.getBu(this.bu);

        super.dataPrediction.setBuP(bu);
    }
    //客户单号
    @Override
    public void setPackListP() {
        String code = "0"+this.packList;
        super.dataPrediction.setPackListP(code);
    }
    //发货时间
    @Override
    public void setShipDateP() throws ParseException {

        String date = DataExu.getSameDate(this.shipDate);

        super.dataPrediction.setShipDateP(date);
    }

    @Override
    public void setShipHub() {
        super.dataPrediction.setShipHub("太仓CLC");
    }

    @Override
    public void setDestinationCity(Customer cus) {


        super.dataPrediction.setDestinationCity(cus.getCity());
    }


    //箱数
    @Override
    public void setCtnsP() {
        //int num = Integer.parseInt();

        super.dataPrediction.setCtnsP(this.ctns);
    }
    //件数
    @Override
    public void setUnitP() {
        //int num = Integer.parseInt();

        super.dataPrediction.setUnitP(this.unit);
    }



    @Override
    public void setAbnormalIssue() {
        //todo
        super.dataPrediction.setAbnormalIssue("");
    }

    @Override
    public OB getOB() {
        OB ob = new OB();
        ob.setCarrierCode(this.carrier);
        ob.setCity(this.provice);
        ob.setCrd(this.crdDate);
        ob.setPsst(this.psst);
        ob.setShipDate(this.shipDate);

        return ob;
    }


    @Override
    public Customer getOldCus() {
        String addresses;
        if (super.isFlag()){
            addresses = this.address2+" "+"aka"+" "+this.address3+"aka"+" "+this.address4;
        }else {

            addresses = address2+address3+address4;
        }
        Customer customer = new Customer();
        customer.setC_Code(this.shipTo);
        customer.setC_Name(this.stName);
        customer.setContact(this.stName);
        customer.setCity(this.provice);
        customer.setAddress(addresses);
        customer.setTelephone(this.phone);
        customer.setPhone(this.phone);
        return customer;
    }

    @Override
    public DataPrediction getINSTANCE() throws MyException, ParseException {
        Customer customer = getCus(getOldCus());
        if (!customer.getFlag()){

            return null;
        }
        return super.getINSTANCE();
    }

    @Override
    public void setLead(City city, OB ob) throws MyException, ParseException {

        ob = getOB();
        Customer cus = getCus(getOldCus());
        ob.setCity(cus.getCity());
        city = getCityLead(cus.getCity());
        super.setLead(city, ob);
    }

    @Override
    public City getCityLead(String city) throws MyException {
        Customer customer = getCus(getOldCus());

        return super.getCityLead(customer.getCity());
    }

    @Override
    public Customer getCus(Customer customer) throws MyException {
        customer = getOldCus();
        return super.getCus(customer);
    }

    @Override
    public void setShipToP(Customer cus) throws MyException {
        cus = getCus(getOldCus());
        super.setShipToP(cus);
    }

    @Override
    public void setAddressP(Customer cus) throws MyException {
        cus = getCus(getOldCus());
        super.setAddressP(cus);
    }

    @Override
    public void setShortN(Customer cus) throws MyException {
        cus = getCus(getOldCus());
        super.setShortN(cus);
    }

    @Override
    public void setCusName(Customer cus) throws MyException {
        cus = getCus(getOldCus());
        super.setCusName(cus);
    }

    @Override
    public void setConsignee(Customer cus) throws MyException {
        cus = getCus(getOldCus());
        super.setConsignee(cus);
    }

    @Override
    public void setTelephoneP(Customer cus) throws MyException {
        cus = getCus(getOldCus());
        super.setTelephoneP(cus);
    }

    @Override
    public void setPhoneP(Customer cus) throws MyException {
        cus = getCus(getOldCus());
        super.setPhoneP(cus);
    }

    public void setBu(String bu) {
        this.bu = bu;
    }

    public void setHubCode(String hubCode) {
        this.hubCode = hubCode;
    }

    public void setTypeCode(String type) {
        this.typeCode = type;
    }

    public void setPackList(String packList) {
        this.packList = packList;
    }

    public void setLog_trailer(String log_trailer) {
        this.log_trailer = log_trailer;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public void setSaphub(String saphub) {
        this.saphub = saphub;
    }

    public void setProvice(String provice) {
        this.provice = provice;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public void setCtns(String ctns) {
        this.ctns = ctns;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setWindow(String window) {
        this.window = window;
    }

    public void setShipTo(String shipTo) {
        this.shipTo = shipTo;
    }

    public void setCrdDate(String crdDate) {
        this.crdDate = crdDate;
    }

    public void setPhDate(String phDate) {
        this.phDate = phDate;
    }

    public void setPsst(String psst) {
        this.psst = psst;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public void setAddress4(String address4) {
        this.address4 = address4;
    }

    public String getBu() {
        return bu;
    }

    public String getHubCode() {
        return hubCode;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public String getPackList() {
        return packList;
    }

    public String getLog_trailer() {
        return log_trailer;
    }

    public String getCarrier() {
        return carrier;
    }

    public String getSaphub() {
        return saphub;
    }

    public String getProvice() {
        return provice;
    }

    public String getShipDate() {
        return shipDate;
    }

    public String getCtns() {
        return ctns;
    }

    public String getUnit() {
        return unit;
    }

    public String getWindow() {
        return window;
    }

    public String getShipTo() {
        return shipTo;
    }

    public String getCrdDate() {
        return crdDate;
    }

    public String getPhDate() {
        return phDate;
    }

    public String getPsst() {
        return psst;
    }

    public String getStName() {
        return stName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getAddress3() {
        return address3;
    }

    public String getAddress4() {
        return address4;
    }


    @Override
    public String toString() {
        return "Data1025{" +
                "bu='" + bu + '\'' +
                ",wzr hubCode='" + hubCode + '\'' +
                ",wzr typeCode='" + typeCode + '\'' +
                ",wzr packList='" + packList + '\'' +
                ",wzr log_trailer='" + log_trailer + '\'' +
                ",wzr carrier='" + carrier + '\'' +
                ",wzr saphub='" + saphub + '\'' +
                ",wzr provice='" + provice + '\'' +
                ",wzr shipDate='" + shipDate + '\'' +
                ",wzr ctns='" + ctns + '\'' +
                ",wzr unit='" + unit + '\'' +
                ",wzr window='" + window + '\'' +
                ",wzr shipTo='" + shipTo + '\'' +
                ",wzr crdDate='" + crdDate + '\'' +
                ",wzr phDate='" + phDate + '\'' +
                ",wzr psst='" + psst + '\'' +
                ",wzr stName='" + stName + '\'' +
                ",wzr phone='" + phone + '\'' +
                ",wzr address1='" + address1 + '\'' +
                ",wzr address2='" + address2 + '\'' +
                ",wzr address3='" + address3 + '\'' +
                ",wzr address4='" + address4 + '\'' +
                '}';
    }
}
