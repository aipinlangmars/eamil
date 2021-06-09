package com.mars.wang.domain;

import com.mars.wang.MyException;
import com.mars.wang.utils.DataExu;
import com.mars.wang.vo.OB;

import java.text.ParseException;

public abstract class ParentData {


 Prediction prediction = new Prediction();
 private boolean flag = true;

 public boolean isFlag() {
  return flag;
 }

 public void setFlag(boolean flag) {
  this.flag = flag;
 }

 public abstract OB getOB();
 public abstract Customer getOldCus();


 public Prediction getINSTANCE() throws MyException, ParseException {
  Customer customer = getCus(getOldCus());
  OB ob = getOB();
  City leadCity = getCityLead(customer.getCity());
  //抽象方法执行
  setCreateDate();
  setBuP();
  setPackListP();
  setShipDateP();
  setShipHub();
  setCtnsP();
  setUnitP();
  setAbnormalIssue();
  //具体方法执行
  setDestinationCity(customer) ;
  setShipToP(customer);
  setAddressP(customer);
  setShortN(customer) ;
  setCusName(customer) ;
  setConsignee(customer);
  setTelephoneP(customer);
  setPhoneP(customer) ;
  setLead(leadCity,ob);



  return this.prediction;
 }
 public City getCityLead(String city) throws MyException {
  City city1 = DataExu.getCity(city);

  return city1;

 };
 public  Customer  getCus(Customer ob) throws MyException{
  String[] akas = ob.getAddress().split("aka");
  String  address1=null;
  for (int i=0;i<akas.length;i++){
   if (akas[i].length()>10){
    address1 = akas[i];
   }
  }

  Customer cus = DataExu.getCus(ob.getC_Code(), ob.getC_Name(), address1, ob.getTelephone(), akas, ob.getProvince());

  return cus;
 };
 public abstract void setCreateDate();
 public abstract void setBuP();
 public abstract void setPackListP();
 public abstract void setShipDateP() throws ParseException;
 public abstract void setShipHub();

 public abstract void setCtnsP();
 public abstract void setUnitP();
 public abstract void setAbnormalIssue();
 //City
 public void setLead(City city,OB ob) throws MyException, ParseException {
  boolean air = DataExu.isAir(ob.getCarrierCode());
  String lead;
  String eta;
  String leadTime;

  if (air){
   leadTime = city.getAirTime();
   lead = city.getAirTime()+"h";
   prediction.setStatus("已提货");
   prediction.setCarrierP(city.getAirCarrier());
   prediction.setTransportType("空运");
   prediction.setAbnormalIssue(city.getAirCarrier());



  }else {
   leadTime = city.getLeadTime();
   lead = city.getLeadTime();
   String desCity = city.getCity();
   prediction.setStatus("干线运输");
   prediction.setCarrierP(city.getCarrier());
   prediction.setTransportType("公路");
   if ("北京".equals(desCity)||"沈阳".equals(desCity)||"大连".equals(desCity)||"太原".equals(desCity)){
    prediction.setAbnormalIssue(desCity);
   }else {
    prediction.setAbnormalIssue(city.getCarrier());
   }

  }

  prediction.setLead(lead);

  String s = DataExu.crdFormat(ob.getCrd());

  //预计到货时间
  eta = DataExu.getFormatEta(ob.getShipDate(),leadTime);

  String s1 = DataExu.equalsDate(s, eta);
//CRD是否满足大于ETA
  if (s1.length()==0){

   prediction.setEta(eta);

  }else {
   prediction.setEta(s1);
  }


  //托运单备注
  String crdRemark = DataExu.getCrdRemark(ob.getPsst(), ob.getCrd(), eta);
  prediction.setNoteRemark(crdRemark);



 }

 //customer
 //目的城市
 public void setDestinationCity(Customer cus){
  prediction.setDestinationCity(cus.getCity());

 }
 //客户代码
 public void setShipToP(Customer cus) throws MyException {
  prediction.setShipToP(cus.getC_Code());
 }
 //收货地址
 public void setAddressP(Customer cus) throws MyException{
  prediction.setAddressP(cus.getAddress());
 };
 public void setShortN(Customer cus) throws MyException{
  prediction.setShortN(cus.getAbbreviation());

 };
 public void setCusName(Customer cus) throws MyException{

  prediction.setCusName(cus.getC_Name());
 }
 public void setConsignee(Customer cus) throws MyException{
  prediction.setConsignee(cus.getContact());
 };
 public void setTelephoneP(Customer cus) throws MyException{
  prediction.setTelephoneP(cus.getTelephone());

 };
 public void setPhoneP(Customer cus) throws MyException{

  prediction.setPhoneP(cus.getPhone());
 };

}
