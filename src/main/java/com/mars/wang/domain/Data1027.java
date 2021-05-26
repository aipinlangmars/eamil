package com.mars.wang.domain;

import com.mars.wang.MyException;
import com.mars.wang.utils.DataExu;
import com.mars.wang.utils.Fomat;
import com.mars.wang.vo.OB;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

public class Data1027  extends ParentData implements Serializable {

    private String ordersrtewavedate;
    private String ordersrtewavenbr;
    private String waveparmdesc;
    private String shipwavenbr;
    private String shipwavedesc;
    private String fulfillmentstatusdesc;
    private String divdesc;
    private String distriborderid;
    private String shipmentnbr;
    private String appointmentnbr;
    private String requesteddttm;
    private String asgndcarriercd;
    private String assignedshipvia;
    private String tpcompanynm;
    private String carrhubcd;
    private String ordersorderdt;
    private String shipmentdlvryenddttm;
    private String pickupstartdttm;
    private String pickupenddttm;
    private String totallpns;
    private String totalunits;
    private String psstflag;
    private String shiptocustnbr;
    private String phonenumber;
    private String destfacilitynm;
    private String destaddr1;
    private String destaddr2;
    private String destaddr3;
    private String destcity;
    private String destctrycd;
    private String destpostalcd;
    private String deststateprov;



    @Override
    public OB getOB() {
        OB ob = new OB();
        ob.setCarrierCode(this.asgndcarriercd);
        ob.setCity(this.destcity);
        ob.setCrd("");
        ob.setPsst(psstflag);
        ob.setShipDate(this.requesteddttm);

        return ob;
    }

    @Override
    public Customer getOldCus() {
        String addresses;
        if (super.isFlag()){
            addresses =" "+destaddr1+"aka"+destaddr2+" "+"aka"+destaddr3+" ";
        }else {

            addresses = destaddr1+destaddr2+destaddr3;
        }

        Customer customer = new Customer();
        customer.setC_Code(this.shiptocustnbr);
        customer.setC_Name(this.destfacilitynm);
        customer.setContact(this.destfacilitynm);
        customer.setCity(this.destcity);
        customer.setAddress(addresses);
        customer.setTelephone(this.phonenumber);
        customer.setPhone(this.phonenumber);
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
        String bu = DataExu.getBu(this.waveparmdesc);

        super.dataPrediction.setBuP(bu);
    }
    //客户单号
    @Override
    public void setPackListP() {

        super.dataPrediction.setPackListP(this.distriborderid);
    }

    @Override
    public void setShipDateP() throws ParseException {
        super.dataPrediction.setShipDateP(this.requesteddttm);
    }

    @Override
    public void setShipHub() {
        super.dataPrediction.setShipHub("太仓3RD");
    }




    //箱数
    @Override
    public void setCtnsP() {
        //int num = Integer.parseInt(this.totallpns);

        super.dataPrediction.setCtnsP(this.totallpns);
    }
    //件数
    @Override
    public void setUnitP() {
        //int num = Integer.parseInt(this.totalunits);

        super.dataPrediction.setUnitP(this.totalunits);
    }





    @Override
    public void setAbnormalIssue() {
        //todo
        super.dataPrediction.setAbnormalIssue("");
    }






    public void setOrdersrtewavedate(String ordersrtewavedate) {
        this.ordersrtewavedate = ordersrtewavedate;
    }

    public void setOrdersrtewavenbr(String ordersrtewavenbr) {
        this.ordersrtewavenbr = ordersrtewavenbr;
    }

    public void setWaveparmdesc(String waveparmdesc) {
        this.waveparmdesc = waveparmdesc;
    }

    public void setShipwavenbr(String shipwavenbr) {
        this.shipwavenbr = shipwavenbr;
    }

    public void setShipwavedesc(String shipwavedesc) {
        this.shipwavedesc = shipwavedesc;
    }

    public void setFulfillmentstatusdesc(String fulfillmentstatusdesc) {
        this.fulfillmentstatusdesc = fulfillmentstatusdesc;
    }

    public void setDivdesc(String divdesc) {
        this.divdesc = divdesc;
    }

    public void setDistriborderid(String distriborderid) {
        this.distriborderid = distriborderid;
    }

    public void setShipmentnbr(String shipmentnbr) {
        this.shipmentnbr = shipmentnbr;
    }

    public void setAppointmentnbr(String appointmentnbr) {
        this.appointmentnbr = appointmentnbr;
    }

    public void setRequesteddttm(String requesteddttm) {
        this.requesteddttm = requesteddttm;
    }

    public void setAsgndcarriercd(String asgndcarriercd) {
        this.asgndcarriercd = asgndcarriercd;
    }

    public void setAssignedshipvia(String assignedshipvia) {
        this.assignedshipvia = assignedshipvia;
    }

    public void setTpcompanynm(String tpcompanynm) {
        this.tpcompanynm = tpcompanynm;
    }

    public void setCarrhubcd(String carrhubcd) {
        this.carrhubcd = carrhubcd;
    }

    public void setOrdersorderdt(String ordersorderdt) {
        this.ordersorderdt = ordersorderdt;
    }

    public void setShipmentdlvryenddttm(String shipmentdlvryenddttm) {
        this.shipmentdlvryenddttm = shipmentdlvryenddttm;
    }

    public void setPickupstartdttm(String pickupstartdttm) {
        this.pickupstartdttm = pickupstartdttm;
    }

    public void setPickupenddttm(String pickupenddttm) {
        this.pickupenddttm = pickupenddttm;
    }

    public void setTotallpns(String totallpns) {
        this.totallpns = totallpns;
    }

    public void setTotalunits(String totalunits) {
        this.totalunits = totalunits;
    }

    public void setPsstflag(String psstflag) {
        this.psstflag = psstflag;
    }

    public void setShiptocustnbr(String shiptocustnbr) {
        this.shiptocustnbr = shiptocustnbr;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setDestfacilitynm(String destfacilitynm) {
        this.destfacilitynm = destfacilitynm;
    }

    public void setDestaddr1(String destaddr1) {
        this.destaddr1 = destaddr1;
    }

    public void setDestaddr2(String destaddr2) {
        this.destaddr2 = destaddr2;
    }

    public void setDestaddr3(String destaddr3) {
        this.destaddr3 = destaddr3;
    }

    public void setDestcity(String destcity) {
        this.destcity = destcity;
    }

    public void setDestctrycd(String destctrycd) {
        this.destctrycd = destctrycd;
    }

    public void setDestpostalcd(String destpostalcd) {
        this.destpostalcd = destpostalcd;
    }

    public void setDeststateprov(String deststateprov) {
        this.deststateprov = deststateprov;
    }

    public String getOrdersrtewavedate() {
        return ordersrtewavedate;
    }

    public String getOrdersrtewavenbr() {
        return ordersrtewavenbr;
    }

    public String getWaveparmdesc() {
        return waveparmdesc;
    }

    public String getShipwavenbr() {
        return shipwavenbr;
    }

    public String getShipwavedesc() {
        return shipwavedesc;
    }

    public String getFulfillmentstatusdesc() {
        return fulfillmentstatusdesc;
    }

    public String getDivdesc() {
        return divdesc;
    }

    public String getDistriborderid() {
        return distriborderid;
    }

    public String getShipmentnbr() {
        return shipmentnbr;
    }

    public String getAppointmentnbr() {
        return appointmentnbr;
    }

    public String getRequesteddttm() {
        return requesteddttm;
    }

    public String getAsgndcarriercd() {
        return asgndcarriercd;
    }

    public String getAssignedshipvia() {
        return assignedshipvia;
    }

    public String getTpcompanynm() {
        return tpcompanynm;
    }

    public String getCarrhubcd() {
        return carrhubcd;
    }

    public String getOrdersorderdt() {
        return ordersorderdt;
    }

    public String getShipmentdlvryenddttm() {
        return shipmentdlvryenddttm;
    }

    public String getPickupstartdttm() {
        return pickupstartdttm;
    }

    public String getPickupenddttm() {
        return pickupenddttm;
    }

    public String getTotallpns() {
        return totallpns;
    }

    public String getTotalunits() {
        return totalunits;
    }

    public String getPsstflag() {
        return psstflag;
    }

    public String getShiptocustnbr() {
        return shiptocustnbr;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getDestfacilitynm() {
        return destfacilitynm;
    }

    public String getDestaddr1() {
        return destaddr1;
    }

    public String getDestaddr2() {
        return destaddr2;
    }

    public String getDestaddr3() {
        return destaddr3;
    }

    public String getDestcity() {
        return destcity;
    }

    public String getDestctrycd() {
        return destctrycd;
    }

    public String getDestpostalcd() {
        return destpostalcd;
    }

    public String getDeststateprov() {
        return deststateprov;
    }

    @Override
    public String toString() {
        return "Data1027{" +
                "ordersrtewavedate='" + ordersrtewavedate + '\'' +
                ", ordersrtewavenbr='" + ordersrtewavenbr + '\'' +
                ", waveparmdesc='" + waveparmdesc + '\'' +
                ", shipwavenbr='" + shipwavenbr + '\'' +
                ", shipwavedesc='" + shipwavedesc + '\'' +
                ", fulfillmentstatusdesc='" + fulfillmentstatusdesc + '\'' +
                ", divdesc='" + divdesc + '\'' +
                ", distriborderid='" + distriborderid + '\'' +
                ", shipmentnbr='" + shipmentnbr + '\'' +
                ", appointmentnbr='" + appointmentnbr + '\'' +
                ", requesteddttm='" + requesteddttm + '\'' +
                ", asgndcarriercd='" + asgndcarriercd + '\'' +
                ", assignedshipvia='" + assignedshipvia + '\'' +
                ", tpcompanynm='" + tpcompanynm + '\'' +
                ", carrhubcd='" + carrhubcd + '\'' +
                ", ordersorderdt='" + ordersorderdt + '\'' +
                ", shipmentdlvryenddttm='" + shipmentdlvryenddttm + '\'' +
                ", pickupstartdttm='" + pickupstartdttm + '\'' +
                ", pickupenddttm='" + pickupenddttm + '\'' +
                ", totallpns='" + totallpns + '\'' +
                ", totalunits='" + totalunits + '\'' +
                ", psstflag='" + psstflag + '\'' +
                ", shiptocustnbr='" + shiptocustnbr + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", destfacilitynm='" + destfacilitynm + '\'' +
                ", destaddr1='" + destaddr1 + '\'' +
                ", destaddr2='" + destaddr2 + '\'' +
                ", destaddr3='" + destaddr3 + '\'' +
                ", destcity='" + destcity + '\'' +
                ", destctrycd='" + destctrycd + '\'' +
                ", destpostalcd='" + destpostalcd + '\'' +
                ", deststateprov='" + deststateprov + '\'' +
                '}';
    }
}
