package com.mars.wang.utils.getdata;

import com.mars.wang.MyException;
import com.mars.wang.domain.Data1039;
import com.mars.wang.domain.ParentData;
import com.mars.wang.utils.POP3ReceiveMailTest;

public class GetData1039  extends Parent{

    public Data1039 getData(String[] strings) throws MyException {

        Data1039 data1039 = POP3ReceiveMailTest.get1039(strings);

        return data1039;

    }

}
