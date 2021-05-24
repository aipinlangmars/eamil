package com.mars.wang.domain;

import com.mars.wang.MyException;
import com.mars.wang.utils.DataExu;
import com.mars.wang.utils.getdata.Parent;

import java.text.ParseException;
import java.util.List;

public abstract class ParentData {


 DataPrediction dataPrediction = new DataPrediction();



 public DataPrediction getINSTANCE() throws MyException, ParseException {


  return this.dataPrediction;
 }

 public abstract Customer  getCus() throws MyException;
 public abstract void setCreateDate();
 public abstract void setBuP();
 public abstract void setPackListP();
 public abstract void setShipDateP() throws ParseException;
 public abstract void setShipHub();

 public abstract void setCtnsP();
 public abstract void setUnitP();
 public abstract void setTransportType() throws MyException;
 public abstract void setLead() throws MyException;
 public abstract void setEta() throws MyException, ParseException;
 public abstract void setStatus() throws MyException;
 public abstract void setCarrierP() throws MyException;
 public abstract void setNoteRemark() throws MyException, ParseException;
 public abstract void setAbnormalIssue();
 //customer
 //目的城市
 public void setDestinationCity(Customer cus){
  dataPrediction.setDestinationCity(cus.getCity());

 }
 //客户代码
 public void setShipToP(Customer cus){
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
