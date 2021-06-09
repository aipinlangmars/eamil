package com.mars.wang.domain;

public class CarCode {
    private String code;//车牌号
    private String fuelType;//燃料种类
    private String model;//车型(M)
    private String loads;//载重(T)
    private String maximumVolume;//最大容积(CBM)
    private String vehicleBrand;//车辆品牌
    private String mod;//型号
    private String years;//已经使用年限
    private String scrap;//强制报废期
    private String information;//国标信息
    private String oilConsumption;//平均油耗
    private String distance;//短途&长途
    private String line;//线路
    private boolean available;//是否可用
    private int ctnsP;//箱数
    private int unitP;//件数
    private String date;//日期

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLoads() {
        return loads;
    }

    public void setLoads(String loads) {
        this.loads = loads;
    }

    public String getMaximumVolume() {
        return maximumVolume;
    }

    public void setMaximumVolume(String maximumVolume) {
        this.maximumVolume = maximumVolume;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getScrap() {
        return scrap;
    }

    public void setScrap(String scrap) {
        this.scrap = scrap;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getOilConsumption() {
        return oilConsumption;
    }

    public void setOilConsumption(String oilConsumption) {
        this.oilConsumption = oilConsumption;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getCtnsP() {
        return ctnsP;
    }

    public void setCtnsP(int ctnsP) {
        this.ctnsP = ctnsP;
    }

    public int getUnitP() {
        return unitP;
    }

    public void setUnitP(int unitP) {
        this.unitP = unitP;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CarCode{" +
                "code='" + code + '\'' +
                ",wzr fuelType='" + fuelType + '\'' +
                ",wzr model='" + model + '\'' +
                ",wzr load='" + loads + '\'' +
                ",wzr maximumVolume='" + maximumVolume + '\'' +
                ",wzr vehicleBrand='" + vehicleBrand + '\'' +
                ",wzr models='" + mod + '\'' +
                ",wzr years='" + years + '\'' +
                ",wzr scrap='" + scrap + '\'' +
                ",wzr information='" + information + '\'' +
                ",wzr oilConsumption='" + oilConsumption + '\'' +
                ",wzr distance='" + distance + '\'' +
                ",wzr line='" + line + '\'' +
                ",wzr available=" + available +
                ",wzr ctnsP=" + ctnsP +
                ",wzr unitP=" + unitP +
                ",wzr date='" + date + '\'' +
                '}';
    }


}
