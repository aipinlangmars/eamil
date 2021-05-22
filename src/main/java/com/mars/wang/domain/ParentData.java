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
 public abstract void setDestinationCity() throws MyException;
 public abstract void setShipToP();
 public abstract void setAddressP() throws MyException;
 public abstract void setCtnsP();
 public abstract void setUnitP();
 public abstract void setTransportType() throws MyException;
 public abstract void setLead() throws MyException;
 public abstract void setEta() throws MyException, ParseException;
 public abstract void setStatus() throws MyException;
 public abstract void setCarrierP() throws MyException;
 public abstract void setNoteRemark() throws MyException, ParseException;
 public abstract void setAbnormalIssue();
 public abstract void setShortN() throws MyException;
 public abstract void setCusName() throws MyException;
 public abstract void setConsignee() throws MyException;
 public abstract void setTelephoneP() throws MyException;
 public abstract void setPhoneP() throws MyException;

}
