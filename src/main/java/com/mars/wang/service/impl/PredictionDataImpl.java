
package com.mars.wang.service.impl;


import com.mars.wang.MyException;
import com.mars.wang.dao.*;
import com.mars.wang.domain.*;

import com.mars.wang.utils.DataExu;
import com.mars.wang.utils.POI;
import com.mars.wang.utils.SqlSessionUtil;

import org.apache.ibatis.session.SqlSession;


import java.text.ParseException;
import java.util.*;

public class PredictionDataImpl  {

    public static void main(String[] args) throws ParseException, MyException {
        Map map = new HashMap();
        predictionData(map);
    }



    public static void predictionData(Map map) throws ParseException, MyException {
        //SqlSession sqlSession = SqlSessionUtil.getSqlSession();

        List<Prediction> dataPredictions = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        String date ="2021/06/09";
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmailDao1025 mapper1025 = sqlSession.getMapper(EmailDao1025.class);
        EmailDao1027 mapper1027 = sqlSession.getMapper(EmailDao1027.class);
        EmailDao1028 mapper1028 = sqlSession.getMapper(EmailDao1028.class);
        EmailDao1039 mapper1039 = sqlSession.getMapper(EmailDao1039.class);
        DataPredictionDao dataPredictionDao = sqlSession.getMapper(DataPredictionDao.class);

        List<ParentData> data1025s =  mapper1025.getList1025(date);
        List<ParentData> data1027s = mapper1027.getList1027(date);
        List<ParentData> data1039s = mapper1039.getList1039(date);
        List<ParentData> data1028s = mapper1028.getList1028(date);
        System.out.println(data1027s.size());

        List<ParentData> parentData = new ArrayList<>();

        List<Object> predictions = new ArrayList<>();

        parentData.addAll(data1025s);
        parentData.addAll(data1027s);
        parentData.addAll(data1028s);
        parentData.addAll(data1039s);

        for (ParentData data1025:parentData){

            Prediction instance = data1025.getINSTANCE();
            if (instance==null){

                Customer oldCus = data1025.getOldCus();
                //System.out.println("oldcus=="+oldCus);
                Customer cus = data1025.getCus(oldCus);
                //System.out.println("newcus=="+cus);
                customers.add(cus);

            }else {
                //预报发送
                predictions.add(instance);
                /*try {
                    //dataPredictionDao.insertOneValue(instance);

                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("记录更新="+instance.getBuP());
                    dataPredictionDao.updatePre(instance);
                }*/

            }

        }
        System.out.println("集合==="+customers.size());
        List<Object> privateCus =new ArrayList<>();

        List<Object> disCus =new ArrayList<>();

        PrivateCity privateCity;


        try {
            Date date1 = new Date();
            String mMss = DataExu.getMMss(date1).replaceAll(":","-").replaceAll("/","-");
            if(customers.size()>0){
                for (Customer customer:customers){

                    if (customer.getAbbreviation()=="个人客户"){
                        privateCity=new PrivateCity();
                        privateCity.setCode(customer.getC_Code());
                        privateCity.setAddress(customer.getAddress());
                        privateCity.setCity(customer.getProvince());
                        privateCus.add(privateCity);
                        System.out.println(customer);
                    }else {

                        disCus.add(customer);

                    }

                }

                String excelIdP ;
                String excelIdC;
                excelIdC = mMss+"经销商";
                excelIdP = mMss+"个人客户";
                //发送个人客户维护和经销商维护信息
                if (disCus.size()>0&&privateCus.size()>0){

                    POI.writeExcel(excelIdC,disCus,new Customer());
                    POI.writeExcel(excelIdP,privateCus,new PrivateCity());

                }else if (disCus.size()>0){
                    System.out.println("经销商代码未维护");

                    POI.writeExcel(excelIdC,disCus,new Customer());
                }else if (privateCus.size()>0){
                    System.out.println("个人客户未维护");
                    for (Object ci:privateCus){

                        System.out.println(ci);
                    }
                    POI.writeExcel(excelIdP,privateCus,new PrivateCity());
                }else {


                }




            }else {
                //发送预报
                String obShip = "OB出货预报"+mMss;
                POI.writeExcel(obShip,predictions,new Prediction());

                System.out.println("数据插入成功==发送预报！");


            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("发送异常！");
        }


    }

   /* public  void  foreach(ParentData data1025) throws ParseException, MyException {


        DataPrediction instance = data1025.getINSTANCE();


        if (instance==null){



        }else {


        }



    }

*/


}
