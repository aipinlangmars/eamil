package com.mars.wang.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Fomat {

    public static void main(String[] args) {
        String string = getString(0);

    }

    public static String getString(long  l){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");




        String format = dateFormat.format(new Date(l));


        System.out.println(format);

        return format;
    }

}
