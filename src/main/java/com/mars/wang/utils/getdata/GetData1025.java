package com.mars.wang.utils.getdata;

import com.mars.wang.MyException;
import com.mars.wang.domain.Data1025;
import com.mars.wang.domain.Data1039;
import com.mars.wang.domain.ParentData;
import com.mars.wang.utils.POP3ReceiveMailTest;

public class GetData1025 extends Parent{

    public Data1025 getData(String[] strings) throws MyException {

        Data1025 data1025 = POP3ReceiveMailTest.get1025(strings);

        return data1025;

    }

}
