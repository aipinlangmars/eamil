
package com.mars.wang.service.impl;


import com.mars.wang.MyException;
import com.mars.wang.dao.EmailDao1025;
import com.mars.wang.dao.EmailDao1027;
import com.mars.wang.dao.EmailDao1028;
import com.mars.wang.dao.EmailDao1039;
import com.mars.wang.domain.*;

import com.mars.wang.utils.POI;
import com.mars.wang.utils.SqlSessionUtil;

import org.apache.ibatis.session.SqlSession;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PredictionDataImpl  {

    public static void main(String[] args) throws ParseException, MyException {
        Map map = new HashMap();
        predictionData(map);
    }


    public static void predictionData(Map map) throws ParseException, MyException {
        List<Object> customers = new ArrayList<>();
        String date ="2021/05/21";
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmailDao1025 mapper1025 = sqlSession.getMapper(EmailDao1025.class);
        EmailDao1027 mapper1027 = sqlSession.getMapper(EmailDao1027.class);
        EmailDao1028 mapper1028 = sqlSession.getMapper(EmailDao1028.class);
        EmailDao1039 mapper1039 = sqlSession.getMapper(EmailDao1039.class);

        List<ParentData> data1025s =  mapper1025.getList1025(date);
        List<ParentData> data1027s = mapper1027.getList1027(date);
        List<ParentData> data1039s = mapper1039.getList1039(date);
        List<ParentData> data1028s = mapper1028.getList1028(date);
        System.out.println(data1027s.size());

        List<ParentData> predictions = new ArrayList<>();

        predictions.addAll(data1025s);
        predictions.addAll(data1027s);
        predictions.addAll(data1028s);
        predictions.addAll(data1039s);



        for (ParentData data1025:predictions){


            DataPrediction instance = data1025.getINSTANCE();
            if (instance==null){

                Customer cus = data1025.getCus();

                customers.add(cus);

            }else {

                System.out.println(instance);

            }






        }
        System.out.println("集合==="+customers.size());
        try {
            if(customers.size()>0){

                String s = POI.writeExcel("", customers, new Customer());

            }
            for (Object customer:customers){

                System.out.println("不通过=="+customer.toString());

            }
        }catch (Exception e){


        }



        /*System.out.println("集合数量="+customers.size());
        String path="";
        for (Customer customer:customers){
            System.out.println("不同过");
            System.out.println(customer.toString());
        }
        try {
            path = POI.writeExcel("",customers);
            if (path==null){

                System.out.println("创建失败=无附件！");
                return;
            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("附件创建失败");

        }*/





        ///1027
        //ist<Data1027> data1027s =  mapper1027.getList1027(map);
        ///1025
        //ist<Data1028> data1028s =  mapper1028.getList1028(map);
        ///1025
        //ist<Data1039> data1039s =  mapper1039.getList1039(map);




    }

    public  void  foreach(ParentData data1025) throws ParseException, MyException {


        DataPrediction instance = data1025.getINSTANCE();


        if (instance==null){



        }else {


        }



    }




}
