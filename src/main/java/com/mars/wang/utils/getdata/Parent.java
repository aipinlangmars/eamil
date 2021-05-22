package com.mars.wang.utils.getdata;

import com.mars.wang.MyException;
import com.mars.wang.domain.ParentData;

import java.io.Serializable;

public abstract class Parent  {

    public abstract ParentData getData(String[] strings) throws MyException;



}
