package com.mars.wang.domain;

import com.mars.wang.MyException;
import com.mars.wang.utils.DataExu;
import com.mars.wang.utils.Fomat;
import com.mars.wang.vo.OB;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

public class Data1028 extends ParentData implements Serializable {

    private String infoDate;
    private String bu;
    private String loadkey;
    private String  hub;
    private String consigneekey;
    private String c_city;
    private String customerName;
    private String c_address3;
    private String c_address2;
    private String unit;
    private String ctns;
    private String carrier;
    private String orderCoder;
    private String shipDate;
    private String ataDate;
    private String remar;
    private String requestDate;






    @Override
    public OB getOB() {
        OB ob = new OB();
        ob.setCarrierCode(this.carrier);
        ob.setCity(this.c_city);
        ob.setCrd(this.remar);
        ob.setPsst("");
        ob.setShipDate(this.shipDate);

        return ob;
    }

    @Override
    public Customer getOldCus() {
        String addresses;
        if (super.isFlag()){
            addresses = " "+c_address2+"aka"+c_address3+" ";
        }else {

            addresses = c_address2+c_address3;
        }

        Customer customer = new Customer();
        customer.setC_Code(this.consigneekey);
        customer.setC_Name(this.customerName);
        customer.setContact(this.customerName);
        customer.setCity(this.c_city);
        customer.setAddress(addresses);
        customer.setTelephone(this.customerName);
        customer.setPhone(this.customerName);
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
        String bu = DataExu.getBu(this.bu);

        super.dataPrediction.setBuP(bu);
    }
    //客户单号
    @Override
    public void setPackListP() {

        super.dataPrediction.setPackListP(this.loadkey);
    }
    //发货时间
    @Override
    public void setShipDateP() throws ParseException {

        String dateFormat = DataExu.getSameDate(this.shipDate);


        super.dataPrediction.setShipDateP(dateFormat);
    }

    @Override
    public void setShipHub() {
        super.dataPrediction.setShipHub(this.hub);
    }


    //箱数
    @Override
    public void setCtnsP() {
        //int num = Integer.parseInt(this.ctns);

        super.dataPrediction.setCtnsP(this.ctns);
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
        //super.dataPrediction.setAbnormalIssue("");
    }





    public void setInfoDate(String infoDate) {
        this.infoDate = infoDate;
    }

    public void setBu(String bu) {
        this.bu = bu;
    }

    public void setLoadkey(String loadkey) {
        this.loadkey = loadkey;
    }

    public void setHub(String hub) {
        this.hub = hub;
    }

    public void setConsigneekey(String consigneekey) {
        this.consigneekey = consigneekey;
    }

    public void setC_city(String c_city) {
        this.c_city = c_city;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setC_address3(String c_address3) {
        this.c_address3 = c_address3;
    }

    public void setC_address2(String c_address2) {
        this.c_address2 = c_address2;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setCtns(String ctns) {
        this.ctns = ctns;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public void setOrderCoder(String orderCoder) {
        this.orderCoder = orderCoder;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public void setAtaDate(String ataDate) {
        this.ataDate = ataDate;
    }

    public void setRemar(String remar) {
        this.remar = remar;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getInfoDate() {
        return infoDate;
    }

    public String getBu() {
        return bu;
    }

    public String getLoadkey() {
        return loadkey;
    }

    public String getHub() {
        return hub;
    }

    public String getConsigneekey() {
        return consigneekey;
    }

    public String getC_city() {
        return c_city;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getC_address3() {
        return c_address3;
    }

    public String getC_address2() {
        return c_address2;
    }

    public String getUnit() {
        return unit;
    }

    public String getCtns() {
        return ctns;
    }

    public String getCarrier() {
        return carrier;
    }

    public String getOrderCoder() {
        return orderCoder;
    }

    public String getShipDate() {
        return shipDate;
    }

    public String getAtaDate() {
        return ataDate;
    }

    public String getRemar() {
        return remar;
    }

    public String getRequestDate() {
        return requestDate;
    }

    @Override
    public String toString() {
        return "Data1028{" +
                "infoDate='" + infoDate + '\'' +
                ",wzr bu='" + bu + '\'' +
                ",wzr loadkey='" + loadkey + '\'' +
                ",wzr hub='" + hub + '\'' +
                ",wzr consigneekey='" + consigneekey + '\'' +
                ",wzr c_city='" + c_city + '\'' +
                ",wzr customerName='" + customerName + '\'' +
                ",wzr c_address3='" + c_address3 + '\'' +
                ",wzr c_address2='" + c_address2 + '\'' +
                ",wzr unit='" + unit + '\'' +
                ",wzr ctns='" + ctns + '\'' +
                ",wzr carrier='" + carrier + '\'' +
                ",wzr orderCoder='" + orderCoder + '\'' +
                ",wzr shipDate='" + shipDate + '\'' +
                ",wzr ataDate='" + ataDate + '\'' +
                ",wzr remar='" + remar + '\'' +
                ",wzr requestDate='" + requestDate + '\'' +
                '}';
    }
}
