package com.mars.wang.domain;

import com.mars.wang.MyException;
import com.mars.wang.utils.DataExu;
import com.mars.wang.utils.Fomat;
import com.mars.wang.vo.OB;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

public class Data1039 extends ParentData  implements Serializable {

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



    @Override
    public OB getOB() {
        OB ob = new OB();
        ob.setCarrierCode(this.carrierCode);
        ob.setCity(this.city);
        ob.setCrd(this.crdDate);
        ob.setPsst("");
        ob.setShipDate(this.wMSShipDate);

        return ob;
    }

    @Override
    public Customer getOldCus() {
        String addresses;
        if (super.isFlag()){
            addresses = address1+" "+"aka";
        }else {

            addresses = address1;
        }

        Customer customer = new Customer();
        customer.setC_Code(this.shipTo);
        customer.setC_Name(this.stName);
        customer.setContact(this.stName);
        customer.setCity(this.city);
        customer.setAddress(addresses);
        customer.setTelephone(this.shipToPhoneNbr);
        customer.setPhone(this.shipToPhoneNbr);
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

        String dateFormat = DataExu.getSameDate(this.wMSShipDate);


        super.dataPrediction.setShipDateP(dateFormat);
    }

    @Override
    public void setShipHub() {
        super.dataPrediction.setShipHub("BZ");
    }


    //箱数
    @Override
    public void setCtnsP() {
       // int num = Integer.parseInt(this.cTns);

        super.dataPrediction.setCtnsP(this.cTns);
    }
    //件数
    @Override
    public void setUnitP() {
        //int num = Integer.parseInt(this.unit);

        super.dataPrediction.setUnitP(this.unit);
    }


    @Override
    public void setAbnormalIssue() {
        //todo
        super.dataPrediction.setAbnormalIssue("");
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
                "trailerNumber='" + trailerNumber + '\'' +
                ",wzr trailerType='" + trailerType + '\'' +
                ",wzr carrierCode='" + carrierCode + '\'' +
                ",wzr packlist='" + packlist + '\'' +
                ",wzr province='" + province + '\'' +
                ",wzr city='" + city + '\'' +
                ",wzr wMSShipDate='" + wMSShipDate + '\'' +
                ",wzr shipTo='" + shipTo + '\'' +
                ",wzr crdDate='" + crdDate + '\'' +
                ",wzr stName='" + stName + '\'' +
                ",wzr shipToPhoneNbr='" + shipToPhoneNbr + '\'' +
                ",wzr address1='" + address1 + '\'' +
                ",wzr divs='" + divs + '\'' +
                ",wzr volume='" + volume + '\'' +
                ",wzr cTns='" + cTns + '\'' +
                ",wzr unit='" + unit + '\'' +
                '}';
    }
}
