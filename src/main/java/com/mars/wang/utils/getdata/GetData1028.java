package com.mars.wang.utils.getdata;

import com.mars.wang.MyException;
import com.mars.wang.domain.Data1028;
import com.mars.wang.domain.ParentData;
import com.mars.wang.utils.POP3ReceiveMailTest;

public class GetData1028  extends Parent{

    public Data1028 getData(String[] strings) throws MyException {

        Data1028 data1028 = POP3ReceiveMailTest.get1028(strings);

        return data1028;

    }

}
