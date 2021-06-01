package com.mars.wang.domain;

import com.mars.wang.MyException;
import com.mars.wang.utils.DataExu;
import com.mars.wang.utils.getdata.Parent;
import com.mars.wang.vo.OB;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.util.List;

public abstract class ParentData {


 DataPrediction dataPrediction = new DataPrediction();
 private boolean flag = true;

 public boolean isFlag() {
  return flag;
 }

 public void setFlag(boolean flag) {
  this.flag = flag;
 }

 public abstract OB getOB();
 public abstract Customer getOldCus();


 public DataPrediction getINSTANCE() throws MyException, ParseException {
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



  return this.dataPrediction;
 }
 public City getCityLead(String city) throws MyException {
  City city1 = DataExu.getCity(city);

  return city1;

 };
 public  Customer  getCus(Customer customer) throws MyException{
  String[] akas = customer.getAddress().split("aka");
  String  address1=null;
  for (int i=0;i<akas.length;i++){
   if (akas[i].length()>8){
    address1 = akas[i];
   }
  }

  Customer cus = DataExu.getCus(customer.getC_Code(), customer.getC_Name(), address1, customer.getTelephone(), akas, customer.getProvince());

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
   dataPrediction.setStatus("已提货");
   dataPrediction.setCarrierP(city.getAirCarrier());
   dataPrediction.setTransportType("空运");
   dataPrediction.setAbnormalIssue(city.getAirCarrier());



  }else {
   leadTime = city.getLeadTime();
   lead = city.getLeadTime();
   String desCity = city.getCity();
   dataPrediction.setStatus("干线运输");
   dataPrediction.setCarrierP(city.getCarrier());
   dataPrediction.setTransportType("公路");
   if ("北京".equals(desCity)||"沈阳".equals(desCity)||"大连".equals(desCity)||"太原".equals(desCity)){
    dataPrediction.setAbnormalIssue(desCity);
   }else {
    dataPrediction.setAbnormalIssue(city.getCarrier());
   }

  }

  dataPrediction.setLead(lead);

  String s = DataExu.crdFormat(ob.getCrd());

  //预计到货时间
  eta = DataExu.getFormatEta(ob.getShipDate(),leadTime);

  String s1 = DataExu.equalsDate(s, eta);

  if (s1.length()==0){

   dataPrediction.setEta(eta);

  }else {
   dataPrediction.setEta(s1);
  }


  //托运单备注
  String crdRemark = DataExu.getCrdRemark(ob.getPsst(), ob.getCrd(), eta);
  dataPrediction.setNoteRemark(crdRemark);



 }

 //customer
 //目的城市
 public void setDestinationCity(Customer cus){
  dataPrediction.setDestinationCity(cus.getCity());

 }
 //客户代码
 public void setShipToP(Customer cus) throws MyException {
  dataPrediction.setShipToP(cus.getC_Code());
 }
 //收货地址
 public void setAddressP(Customer cus) throws MyException{
  dataPrediction.setAddressP(cus.getAddress());
 };
 public void setShortN(Customer cus) throws MyException{
  dataPrediction.setShortN(cus.getAbbreviation());

 };
 public void setCusName(Customer cus) throws MyException{

  dataPrediction.setCusName(cus.getC_Name());
 }
 public void setConsignee(Customer cus) throws MyException{
  dataPrediction.setConsignee(cus.getContact());
 };
 public void setTelephoneP(Customer cus) throws MyException{
  dataPrediction.setTelephoneP(cus.getTelephone());

 };
 public void setPhoneP(Customer cus) throws MyException{

  dataPrediction.setPhoneP(cus.getPhone());
 };

}
