package com.mars.wang.web;

import com.mars.wang.dao.EmailDao1025;
import com.mars.wang.domain.Data1025;
import com.mars.wang.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.text.SimpleDateFormat;
import java.util.*;

public class Test {

    public static void main(String[] args) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date());
        //System.out.println(instance);
        instance.set(2,4,11);
        //int actualMaximum = instance.getActualMaximum(1);
        Date time = instance.getTime();

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

        String format1 = format.format(time);
        System.out.println(format1);
    }
}
