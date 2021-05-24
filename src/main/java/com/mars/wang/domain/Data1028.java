package com.mars.wang.domain;

import com.mars.wang.MyException;
import com.mars.wang.utils.DataExu;
import com.mars.wang.utils.Fomat;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

public class Data1028 extends  ParentData implements Serializable {

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
    /*private Customer customer = getCus();
    private City city = getCityClass();
    private Boolean flag = DataExu.isAir(this.remar);*/



    public Data1028() throws MyException {
    }

    //todo
    public Customer getCus()  {
        String[] strings = new String[3];
        strings[0] = c_address2;
        strings[1] = c_address3;

        Customer cus=null;
        try {
            cus = DataExu.getCus(this.consigneekey,this.customerName,"%"+c_address3+"%",this.customerName,strings,this.c_city);
        }catch (Exception e){

            cus = null;

        }



        return cus;


    }

    public City getCityClass() throws MyException {

        City city = DataExu.getCity(getCus().getCity(), DataExu.isAir(this.remar));

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
        super.dataPrediction.setShipHub("太仓3RD");
    }

    @Override
    public void setDestinationCity() throws MyException {

        super.dataPrediction.setDestinationCity(getCus().getCity());
    }

    @Override
    public void setShipToP() {


        super.dataPrediction.setShipToP(this.consigneekey);
    }
    //收货地址
    @Override
    public void setAddressP() throws MyException {



        super.dataPrediction.setAddressP(getCus().getAddress());
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

        boolean air = DataExu.isAir(this.remar);
        try{
            type = air?"空运":"公路";
            super.dataPrediction.setTransportType(type);

        }catch (Exception e){

            e.printStackTrace();
        }

    }

    @Override
    public void setLead() throws MyException {
        String lead1 = DataExu.getLead(this.remar, getCus().getCity());
        super.dataPrediction.setLead(lead1);
    }

    @Override
    public void setEta() throws MyException, ParseException {

        String formatEta = DataExu.getFormatEta(this.shipDate, DataExu.getLead(this.remar, getCus().getCity()));


        super.dataPrediction.setEta(formatEta);
    }

    @Override
    public void setStatus() throws MyException {
        boolean air = DataExu.isAir(this.remar);
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
        DataExu.getCarrier(DataExu.isAir(this.remar),getCus().getCity());


        super.dataPrediction.setCarrierP(carrier);
    }

    @Override
    public void setNoteRemark() throws MyException, ParseException {
        String formatEta = DataExu.getFormatEta(this.shipDate, DataExu.getLead(this.remar, getCus().getCity()));
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
                ", bu='" + bu + '\'' +
                ", loadkey='" + loadkey + '\'' +
                ", hub='" + hub + '\'' +
                ", consigneekey='" + consigneekey + '\'' +
                ", c_city='" + c_city + '\'' +
                ", customerName='" + customerName + '\'' +
                ", c_address3='" + c_address3 + '\'' +
                ", c_address2='" + c_address2 + '\'' +
                ", unit='" + unit + '\'' +
                ", ctns='" + ctns + '\'' +
                ", carrier='" + carrier + '\'' +
                ", orderCoder='" + orderCoder + '\'' +
                ", shipDate='" + shipDate + '\'' +
                ", ataDate='" + ataDate + '\'' +
                ", remar='" + remar + '\'' +
                ", requestDate='" + requestDate + '\'' +
                '}';
    }
}
