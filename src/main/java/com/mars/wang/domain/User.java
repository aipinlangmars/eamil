package com.mars.wang.domain;

import java.io.Serializable;

public class User implements Serializable {

    private String username;

    private String password;

    public User(){}

    public User(String name,String pass){

        this.username = name;
        this.password = pass;



    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
