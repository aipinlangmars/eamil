package com.mars.wang.domain;

import java.io.Serializable;

public class Customer implements Serializable {

    private String c_Code;
    private String c_Name;
    private String abbreviation;
    private String address;
    private String city;
    private String province;
    private String contact;
    private String telephone;
    private String phone;
    private boolean flag;

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getC_Code() {
        return c_Code;
    }

    public void setC_Code(String c_Code) {
        this.c_Code = c_Code;
    }

    public String getC_Name() {
        return c_Name;
    }

    public void setC_Name(String c_Name) {
        this.c_Name = c_Name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "c_Code='" + c_Code + '\'' +
                ",wzr c_Name='" + c_Name + '\'' +
                ",wzr abbreviation='" + abbreviation + '\'' +
                ",wzr address='" + address + '\'' +
                ",wzr city='" + city + '\'' +
                ",wzr province='" + province + '\'' +
                ",wzr contact='" + contact + '\'' +
                ",wzr telephone='" + telephone + '\'' +
                ",wzr phone='" + phone + '\'' +
                '}';
    }
}
