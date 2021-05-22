package com.mars.wang.domain;

import com.mars.wang.MyException;
import com.mars.wang.utils.DataExu;
import com.mars.wang.utils.Fomat;
import com.mars.wang.utils.getdata.Parent;

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
    //private Customer customer = getCus();
    //private City city = getCityClass();
    //private Boolean flag = DataExu.isAir(this.typeCode);


    //todo
    public Customer getCus()  {
        String[] strings = new String[3];
        strings[0] = address2;
        strings[1] = address3;
        strings[2] = address4;


        try {
            Customer cus = DataExu.getCus(this.shipTo,this.stName,"%"+address2+"%",this.phone,strings,this.provice);
            return cus;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("获取客户信息失败");
            return null;
        }


    }

    public City getCityClass() throws MyException {


        City city = DataExu.getCity(getCus().getCity(), DataExu.isAir(this.carrier));

        return city;


    }

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
        super.dataPrediction.setShipHub(this.hubCode);
    }

    @Override
    public void setDestinationCity() {


        super.dataPrediction.setDestinationCity(getCus().getCity());
    }

    @Override
    public void setShipToP() {

        super.dataPrediction.setShipToP(shipTo);
    }
    //收货地址
    @Override
    public void setAddressP()  {
        String address ;

        if (getCus().getAddress()==null){
            address = address2+address3+address4;

        }else {
            address = getCus().getAddress();
        }

        super.dataPrediction.setAddressP(address);
    }
    //箱数
    @Override
    public void setCtnsP() {
        int num = Integer.parseInt(this.ctns);

        super.dataPrediction.setCtnsP(num);
    }
    //件数
    @Override
    public void setUnitP() {
        int num = Integer.parseInt(this.unit);

        super.dataPrediction.setUnitP(num);
    }

    @Override
    public void setTransportType() throws MyException {
        String type;
        boolean air = DataExu.isAir(this.carrier);
        try{
            type = air?"空运":"公路";
            super.dataPrediction.setTransportType(type);

        }catch (Exception e){

            e.printStackTrace();
        }

    }

    @Override
    public void setLead()  {
        try {
            String lead1 = DataExu.getLead(this.carrier, getCus().getCity());
            super.dataPrediction.setLead(lead1);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("时效设置失败");

        }

    }

    @Override
    public void setEta() throws MyException, ParseException {
        String formatEta = DataExu.getFormatEta(this.shipDate, DataExu.getLead(this.carrier, getCus().getCity()));


        super.dataPrediction.setEta(formatEta);
    }

    @Override
    public void setStatus() throws MyException {
        String tra;
        boolean air = DataExu.isAir(this.carrier);

        if (air){

            tra = "已提货";
        }else {

            tra = "干线运输";
        }

        super.dataPrediction.setStatus(tra);
    }

    @Override
    public void setCarrierP() throws MyException {

        String carrier = DataExu.getCarrier(DataExu.isAir(this.carrier), getCus().getCity());


        super.dataPrediction.setCarrierP(carrier);
    }

    @Override
    public void setNoteRemark() throws MyException, ParseException {
        String formatEta = DataExu.getFormatEta(this.shipDate, DataExu.getLead(this.carrier, getCus().getCity()));
        //todo
        String crdRemark = DataExu.getCrdRemark(this.psst, this.crdDate, formatEta);

        super.dataPrediction.setNoteRemark(crdRemark);
    }

    @Override
    public void setAbnormalIssue() {
        //todo
        super.dataPrediction.setAbnormalIssue("");
    }

    @Override
    public void setShortN() throws MyException {

        super.dataPrediction.setShortN(getCus().getAbbreviation());
    }

    @Override
    public void setCusName() throws MyException {

        super.dataPrediction.setCusName(getCus().getC_Name());
    }

    @Override
    public void setConsignee() throws MyException {

        super.dataPrediction.setConsignee(getCus().getContact());
    }

    @Override
    public void setTelephoneP() throws MyException {


        super.dataPrediction.setTelephoneP(getCus().getTelephone());
    }

    @Override
    public void setPhoneP() throws MyException {

        super.dataPrediction.setPhoneP(getCus().getPhone());
    }

    
    @Override
    public DataPrediction getINSTANCE() throws ParseException, MyException {
        try {
            if (!getCus().getFlag()){
                return null;
            }else {

                getCus();
                setCreateDate();
                setBuP();
                setPackListP();
                setShipDateP();
                setShipHub();
                setDestinationCity() ;
                setShipToP();
                setAddressP();
                setCtnsP();
                setUnitP();
                setTransportType() ;
                setLead() ;
                setEta() ;
                setStatus();
                setCarrierP();
                setNoteRemark();
                setAbnormalIssue();
                setShortN() ;
                setCusName() ;
                setConsignee();
                setTelephoneP();
                setPhoneP() ;
                return super.dataPrediction;
            }

        }catch (Exception e){


            e.printStackTrace();

            return null;
        }


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
                ", hubCode='" + hubCode + '\'' +
                ", type='" + typeCode + '\'' +
                ", packList='" + packList + '\'' +
                ", log_trailer='" + log_trailer + '\'' +
                ", carrier='" + carrier + '\'' +
                ", saphub='" + saphub + '\'' +
                ", provice='" + provice + '\'' +
                ", shipDate='" + shipDate + '\'' +
                ", ctns='" + ctns + '\'' +
                ", unit='" + unit + '\'' +
                ", window='" + window + '\'' +
                ", shipTo='" + shipTo + '\'' +
                ", crdDate='" + crdDate + '\'' +
                ", phDate='" + phDate + '\'' +
                ", psst='" + psst + '\'' +
                ", stName='" + stName + '\'' +
                ", phone='" + phone + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", address3='" + address3 + '\'' +
                ", address4='" + address4 + '\'' +
                '}';
    }


}
