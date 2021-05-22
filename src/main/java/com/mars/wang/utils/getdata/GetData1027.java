package com.mars.wang.utils.getdata;

import com.mars.wang.MyException;
import com.mars.wang.domain.Data1027;
import com.mars.wang.domain.Data1039;
import com.mars.wang.domain.ParentData;
import com.mars.wang.utils.POP3ReceiveMailTest;

public class GetData1027 extends Parent {

    public Data1027 getData(String[] strings) throws MyException {

        Data1027 data1027 = POP3ReceiveMailTest.get1027(strings);

        return data1027;

    }

}
