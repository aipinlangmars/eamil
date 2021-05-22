package com.mars.wang.domain;

import com.mars.wang.MyException;
import com.mars.wang.utils.DataExu;
import com.mars.wang.utils.Fomat;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

public class Data1039  extends  ParentData implements Serializable {

    private String trailerNumber ;
    private String trailerType;
    private String carrierCode;
    private String packlist;
    private String province;
    private String city;
    private String wMSShipDate;
    private String shipTo;
    private String crdDate;
    private String stName;
    private String shipToPhoneNbr;
    private String address1;
    private String divs;
    private String volume;
    private String cTns;
    private String unit;

   /* private Customer customer = getCus();
    private City cityC = getCityClass();
    private Boolean flag = DataExu.isAir(this.carrierCode);*/


    //todo
    public Customer getCus() throws MyException {
        String[] strings = new String[3];
        strings[0] = address1;



        Customer cus=null;
        try {
           cus = DataExu.getCus(this.shipTo,stName,"%"+address1+"%",shipToPhoneNbr,strings,this.city);
        }catch (Exception e){

            cus = null;

        }

        return cus;


    }

    public City getCityClass() throws MyException {



        return null;
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
        String bu = DataExu.getBu(this.divs);

        super.dataPrediction.setBuP(bu);
    }
    //客户单号
    @Override
    public void setPackListP() {

        super.dataPrediction.setPackListP(this.packlist);
    }
    //发货时间
    @Override
    public void setShipDateP() throws ParseException {

        String dateFormat = DataExu.getSameDate(this.packlist);


        super.dataPrediction.setShipDateP(dateFormat);
    }

    @Override
    public void setShipHub() {
        super.dataPrediction.setShipHub("太仓3RD");
    }

    @Override
    public void setDestinationCity() throws MyException {

        super.dataPrediction.setDestinationCity(getCus().getCity());
    }

    @Override
    public void setShipToP() {

        super.dataPrediction.setShipToP(this.shipTo);
    }
    //收货地址
    @Override
    public void setAddressP() throws MyException {
        String address ;

        if (getCus().getAddress()==null){
            address = this.address1;

        }else {
            address = getCus().getAddress();
        }

        super.dataPrediction.setAddressP(address);
    }
    //箱数
    @Override
    public void setCtnsP() {
        int num = Integer.parseInt(this.cTns);

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
        boolean air = DataExu.isAir(this.carrierCode);
        try{
            type = air?"空运":"公路";
            super.dataPrediction.setTransportType(type);

        }catch (Exception e){

            e.printStackTrace();
        }

    }

    @Override
    public void setLead() throws MyException {
        String lead1 = DataExu.getLead(this.carrierCode, getCus().getCity());
        super.dataPrediction.setLead(lead1);
    }

    @Override
    public void setEta() throws MyException, ParseException {
        String formatEta = DataExu.getFormatEta(this.wMSShipDate, DataExu.getLead(this.carrierCode, getCus().getCity()));


        super.dataPrediction.setEta(formatEta);
    }

    @Override
    public void setStatus() throws MyException {
        boolean air = DataExu.isAir(this.carrierCode);
        String tra;
        if (air){

            tra = "已提货";
        }else {

            tra = "干线运输";
        }

        super.dataPrediction.setStatus(tra);
    }

    @Override
    public void setCarrierP() throws MyException {

        String carrier = DataExu.getCarrier(DataExu.isAir(this.carrierCode), this.carrierCode);


        super.dataPrediction.setCarrierP(carrier);
    }

    @Override
    public void setNoteRemark() throws MyException, ParseException {
        String formatEta = DataExu.getFormatEta(this.wMSShipDate, DataExu.getLead(this.carrierCode, getCus().getCity()));
        //todo
        String crdRemark = DataExu.getCrdRemark("", "", formatEta);

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

        super.dataPrediction.setTelephoneP(getCus().getTelephone());
    }


    @Override
    public DataPrediction getINSTANCE() throws ParseException, MyException {
        if (!getCus().getFlag()){

            return null;
        }
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

    public void setTrailerNumber(String trailerNumber) {
        this.trailerNumber = trailerNumber;
    }

    public void setTrailerType(String trailerType) {
        this.trailerType = trailerType;
    }

    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    public void setPacklist(String packlist) {
        this.packlist = packlist;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setwMSShipDate(String wMSShipDate) {
        this.wMSShipDate = wMSShipDate;
    }

    public void setShipTo(String shipTo) {
        this.shipTo = shipTo;
    }

    public void setCrdDate(String crdDate) {
        this.crdDate = crdDate;
    }

    public void setStName(String stName) {
        this.stName = stName;
    }

    public void setShipToPhoneNbr(String shipToPhoneNbr) {
        this.shipToPhoneNbr = shipToPhoneNbr;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public void setDivs(String divs) {
        this.divs = divs;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public void setcTns(String cTns) {
        this.cTns = cTns;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTrailerNumber() {
        return trailerNumber;
    }

    public String getTrailerType() {
        return trailerType;
    }

    public String getCarrierCode() {
        return carrierCode;
    }

    public String getPacklist() {
        return packlist;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getwMSShipDate() {
        return wMSShipDate;
    }

    public String getShipTo() {
        return shipTo;
    }

    public String getCrdDate() {
        return crdDate;
    }

    public String getStName() {
        return stName;
    }

    public String getShipToPhoneNbr() {
        return shipToPhoneNbr;
    }

    public String getAddress1() {
        return address1;
    }

    public String getDivs() {
        return divs;
    }

    public String getVolume() {
        return volume;
    }

    public String getcTns() {
        return cTns;
    }

    public String getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return "Data1039{" +
                "carrierCode='" + carrierCode + '\'' +
                ", packlist='" + packlist + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", wMSShipDate='" + wMSShipDate + '\'' +
                ", shipTo='" + shipTo + '\'' +
                ", crdDate='" + crdDate + '\'' +
                ", stName='" + stName + '\'' +
                ", shipToPhoneNbr='" + shipToPhoneNbr + '\'' +
                ", address1='" + address1 + '\'' +
                ", divs='" + divs + '\'' +
                ", volume='" + volume + '\'' +
                ", cTns='" + cTns + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
