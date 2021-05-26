package com.mars.wang.domain;

import java.io.Serializable;

public class PrivateCity implements Serializable {
    private String code;

    private String city;

    private String address;

    public String getCode() {
        return code;
    }

    public void setCode(String id) {
        this.code = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "PrivateCity{" +
                "code='" + code + '\'' +
                ",wzr city='" + city + '\'' +
                ",wzr address='" + address + '\'' +
                '}';
    }
}
