package com.mars.wang.domain.wci;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Report {

    /*private String sonumber;//SO单号
    private String cpod;//总单号
    private String packListP;//发货号
    private String loadingdate;//发货日期
    private String shipHub;//出发城市
    private String destinationCity;//目的城市
    private String province;//目的省份
    private String shipToP;//收货人代码
    private String consigneename;//收货人
    private String addressP;//送货地址
    private String ctnsP;//箱数
    private String unitP;//件数
    private String volume;//体积
    private String buP;//产品类型
    private String air;//陆运/铁运/空运
    private String leadtime;//在途时间
    private String eta;//预计到货日期
    private String carrier;//承运商
    private String shipstatus;//运输状态
    private String actualarrivaltime;//实际到货日期
    private String remark;//备注
    private String podupload;//回单是否上传
    private String shipRecourse;//是否为节能资源运输
    private String warehouse;//仓库
    private String sysCRD;//系统CRD日期
    private String updateEta;//UpdateETA
    private String problemOwner;//责任代码*/

    /*public String getSonumber() {
        return sonumber;
    }

    public void setSonumber(String sonumber) {
        this.sonumber = sonumber;
    }

    public String getCpod() {
        return cpod;
    }

    public void setCpod(String cpod) {
        this.cpod = cpod;
    }

    public String getPackListP() {
        return packListP;
    }

    public void setPackListP(String packListP) {
        this.packListP = packListP;
    }

    public String getLoadingdate() {
        return loadingdate;
    }

    public void setLoadingdate(String loadingdate) {
        this.loadingdate = loadingdate;
    }

    public String getShipHub() {
        return shipHub;
    }

    public void setShipHub(String shipHub) {
        this.shipHub = shipHub;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        destinationCity = destinationCity;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getShipToP() {
        return shipToP;
    }

    public void setShipToP(String shipToP) {
        this.shipToP = shipToP;
    }

    public String getConsigneename() {
        return consigneename;
    }

    public void setConsigneename(String consigneename) {
        this.consigneename = consigneename;
    }

    public String getAddressP() {
        return addressP;
    }

    public void setAddressP(String addressP) {
        this.addressP = addressP;
    }

    public String getCtnsP() {
        return ctnsP;
    }

    public void setCtnsP(String ctnsP) {
        this.ctnsP = ctnsP;
    }

    public String getUnitP() {
        return unitP;
    }

    public void setUnitP(String unitP) {
        this.unitP = unitP;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getBuP() {
        return buP;
    }

    public void setBuP(String buP) {
        this.buP = buP;
    }

    public String getAir() {
        return air;
    }

    public void setAir(String air) {
        this.air = air;
    }

    public String getLeadtime() {
        return leadtime;
    }

    public void setLeadtime(String leadtime) {
        this.leadtime = leadtime;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getShipstatus() {
        return shipstatus;
    }

    public void setShipstatus(String shipstatus) {
        this.shipstatus = shipstatus;
    }

    public String getActualarrivaltime() {
        return actualarrivaltime;
    }

    public void setActualarrivaltime(String actualarrivaltime) {
        this.actualarrivaltime = actualarrivaltime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPodupload() {
        return podupload;
    }

    public void setPodupload(String podupload) {
        this.podupload = podupload;
    }

    public String getShipRecourse() {
        return shipRecourse;
    }

    public void setShipRecourse(String shipRecourse) {
        this.shipRecourse = shipRecourse;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getSysCRD() {
        return sysCRD;
    }

    public void setSysCRD(String sysCRD) {
        this.sysCRD = sysCRD;
    }

    public String getUpdateEta() {
        return updateEta;
    }

    public void setUpdateEta(String updateEta) {
        this.updateEta = updateEta;
    }

    public String getProblemOwner() {
        return problemOwner;
    }
    public void setProblemOwner(String problemOwner) {
        this.problemOwner = problemOwner;
    }*/
    //private List<String> values = new ArrayList<>();
    //private List<String> methods = new ArrayList<>();
    private Class clazz;
    private Report report;

    /*public List<String> getValues() {
        return values;
    }

    public List<String> getMethods() {
        return methods;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public void setMethods(List<String> method) {
        this.methods = method;
    }*/

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public Object setAll(List<String> values,List<String> methods) throws Exception{
        //System.out.println(values.size()+"=="+methods.size());


        //获取Method对象
        Method method;
        //调用invoke方法来调用
        //System.out.println(m)
        for (int i =0;i<values.size();i++){
            String value =values.get(i);
            //System.out.println("进入循环！");
            if (i==0&&value.length()>50){
                value= value.substring(0, 50);

            }


            //方法名成
            char[] cs=methods.get(i).toCharArray();
            cs[0]-=32;
            String method1 ="set"+String.valueOf(cs);
            //System.out.println(method1);
            method = clazz.getMethod(method1, String.class);
            method.invoke(report, value);
        }

        //System.out.println("setAll父类执行！");

          return report;

    }







}
