package com.mars.wang.domain;

import java.io.Serializable;

public class City  implements Serializable {

    private String city      ;
    private String province ;
    private String leadTime ;
    private String airTime ;
    private String carrier ;
    private String airCarrier;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(String leadTime) {
        this.leadTime = leadTime;
    }

    public String getAirTime() {
        return airTime;
    }

    public void setAirTime(String airTime) {
        this.airTime = airTime;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getAirCarrier() {
        return airCarrier;
    }

    public void setAirCarrier(String airCarrier) {
        this.airCarrier = airCarrier;
    }
}
