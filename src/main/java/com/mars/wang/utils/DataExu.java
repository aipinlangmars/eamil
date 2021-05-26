package com.mars.wang.utils;

import com.mars.wang.MyException;

import com.mars.wang.dao.*;
import com.mars.wang.domain.*;
import org.apache.ibatis.session.SqlSession;


import java.math.BigDecimal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataExu {

    private static SqlSession sqlSession = SqlSessionUtil.getSqlSession();

    private static CityDao cityDao = sqlSession.getMapper(CityDao.class);

    private static CustomerDao customerDao = sqlSession.getMapper(CustomerDao.class);
    //电话头查询
    private static TelephoneHeadDao telephoneHeadDao = sqlSession.getMapper(TelephoneHeadDao.class);

    //个人客户模糊查询
    private static PrivateDao privateDao = sqlSession.getMapper(PrivateDao.class);

    //个人客户城市查询
    private static PrivateCityDao privateCityDao = sqlSession.getMapper(PrivateCityDao.class);

    //好孩子等经销商

    private static DistributorNameDao distributorNameDao = sqlSession.getMapper(DistributorNameDao.class);

    public static String getBu(String bu) {
        String type = null;
        if (bu == "" || bu == null) {

            return "bu异常";
        }
        if ("10".equals(bu) || bu.indexOf("AP")!=-1) {

            type = "APP";

        } else if ("20".equals(bu) || bu.indexOf("FW")!=-1) {

            type = "FW";

        } else if ("30".equals(bu) || bu.indexOf("EQ")!=-1) {

            type = "EQ";

        }

        return type;
    }

    //在途时效
    public static String getLead(String runType, String city) throws MyException {
        String lead;
        if (isAir(runType)) {

            //todo 空运
            lead = cityDao.getAriLead(city);



        } else {

            //陆运
            lead = cityDao.getNormalLead(city);


        }
        return lead;
    }
    //承运商
    public static String getCarrier(boolean isAir,String city){
        String carrier;
        if (isAir){

            carrier = cityDao.getAirCarrier(city);

        }else {


            carrier = cityDao.getNormalCarrier(city);
        }

        return carrier;

    }

    //城市

    //是否是空运
    public static boolean isAir(String runType) throws MyException {

        boolean flag;
        if (runType == null || runType == "") {

            throw new MyException("运输类型异常！");
        }
        if ("RB33".equals(runType) || "RUBE".equals(runType) || runType.indexOf("空运") != -1) {

            //todo 空运
            flag = true;
        } else {
            flag = false;
            //陆运

        }

        return flag;
    }

    //目的城市
    public static City getCity(String city) throws MyException {

        City cityC = cityDao.searchByCity(city);
        //todo
        return cityC;

    }



    //个人客户处理
    public static Customer getPrivateCus(String code,String stName, String address, String telephone,String addr,String province) throws MyException {

        Customer privateCus = new Customer();

        String prediction = telephone.trim().replace(" ","");

        String cusName;
        String addressP;
        String stTelephone;
        String SName;
        String privateName;
        String city2;
        String name = stName.trim();//姓名
        int stNameLen = name.length();
        boolean y;


        //判断stname是否有电话号码
        try{
            //todo
            cusName = name.substring(stNameLen-8,stNameLen);

            Integer.parseInt(cusName);

            y=true;


        }catch (Exception e){
            //todo 发送预报维护信息

            y= false;

        }

        List<String> citys = privateCityDao.getCity("%"+address.replaceAll(" ","")+"%");
        if (citys.size()>0){
            city2 = citys.get(0);

                privateCus.setCity(city2);
                privateCus.setAbbreviation(city2+"个人客户");
                privateCus.setC_Name("个人客户");
                privateCus.setAddress(addr);
                privateCus.setC_Code(code);
                privateCus.setFlag(true);
                //电话客户列分开
                if (prediction.length()>=9&&!y){

                    //个人客户名称
                    privateCus.setContact(name);
                    privateCus.setPhone(telephone);
                    privateCus.setTelephone(telephone);

                    //System.out.println("//电话客户列分开"+telephone);
                    return privateCus;

                //客户且有电话号码 todo
                }else if(prediction.length()<9&&y){
                    stTelephone = name.substring(stNameLen-11,stNameLen);
                    //个人客户名称
                    stName = name.substring(0,stNameLen-11);
                    privateCus.setContact(stName);
                    privateCus.setPhone(stTelephone);
                    privateCus.setTelephone(stTelephone);
                    //System.out.println("//客户且有电话号码 电话列也有信息"+stTelephone);
                    return privateCus;
                    //两列均有电话号码
                }else if(prediction.length()>=9&&y){
                    stName = name.substring(0,stNameLen-11);
                    privateCus.setContact(stName);
                    privateCus.setPhone(telephone);
                    privateCus.setTelephone(telephone);
                    //System.out.println("//两列均有电话号码"+telephone);
                    return privateCus;

                }else {

                    throw new MyException("不匹配");
                }

        }else {
            if (y||stName.length()<=7){

                privateCus.setAbbreviation("个人客户");
            }else {
                privateCus.setAbbreviation("经销商");
            }
            //todo
            //System.out.println("此地址无法查询");

            privateCus.setC_Code(code);
            privateCus.setContact(stName);
            privateCus.setC_Name(stName);
            privateCus.setPhone(telephone);
            privateCus.setAddress(addr);
            privateCus.setFlag(false);
            privateCus.setCity(province);

            return privateCus;

        }





    }
    public static void sendMessage(String cusCode,String address,String stName,String telephone){

        System.out.println("发送个人客户维护信息！");



    }


    //客户信息表是否有
    public static Customer getCus(String cusCode,String stName,String address,String telephone,String[] addressP,String province) throws MyException {
        //替换客户代码的三个零

        String replaceCode = cusCode;
        if (cusCode.indexOf("000")==0){

           replaceCode = cusCode.replaceFirst("000", "");


        }

        int length = addressP.length;
        String addressNsp;

        if (cusCode == null || cusCode == "") {

            throw new MyException("客户代码异常！");

        }

        Customer privateCus = customerDao.searchByCode(replaceCode);

        DistributorName distributorName = distributorNameDao.searchByCode(replaceCode);
        //未查询到客户信息 可能为个人客户
        if (privateCus==null&&distributorName==null){

            if (length==3){

                addressNsp = addressP[0]+addressP[1]+addressP[2];
            }else if(length==2){
                addressNsp = addressP[1]+addressP[0];
            }else {

                addressNsp = addressP[0];
            }

            privateCus = getPrivateCus(replaceCode,stName, address, telephone,addressNsp.trim(),province);
        //好孩子等经销商
        }else if (distributorName!=null){
            if (length==3){

                addressNsp = addressP[0]+addressP[1]+addressP[2];
            }else if(length==2){
                addressNsp = addressP[0]+addressP[1];
            }else {

                addressNsp = addressP[0];
            }
            privateCus = new Customer();

            privateCus.setAddress(addressNsp.trim());
            privateCus.setAbbreviation("");
            privateCus.setC_Code(replaceCode);
            privateCus.setC_Name(distributorName.getCusName());
            privateCus.setContact(stName);
            privateCus.setCity(distributorName.getCity());
            privateCus.setPhone(telephone);
            privateCus.setTelephone(telephone);
            privateCus.setFlag(true);
        }else {
            //客户信息已维护
            privateCus.setFlag(true);

        }


        return privateCus;


    }

    //预计到货日期
    public static String getFormatEta(String shipDate, String lead) throws ParseException {
        String ship = getSameDate(shipDate);

        SimpleDateFormat air = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

        int leadTime = Integer.parseInt(lead);

        int day;

        Date parse = format.parse(ship);

        String date = null;


        if (leadTime > 20) {



            date = air.format(new Date(parse.getTime() + leadTime  * 60 * 60 * 1000));

        } else {

            date = format.format(new Date(parse.getTime() + leadTime * 24 * 60 * 60 * 1000));
        }

        return date;

    }

    public static boolean isPsst(String psst) {
        String trim = psst.trim();

        return "Y".equals(trim) ? true : false;


    }
    //拼接CRD
    public static String getCRDString(String date,String psst){
        String crd;

        if (date!=""&&psst==""){
            crd = "CRD要求"+date+"送货";
        }else if (date!=""&& psst!=""){

            crd = psst+"/CRD要求"+date+"送货";
        }else if (date==""&&psst!=""){

            crd = psst;
        }else {
            crd = "";
        }

        return crd;


    }
    public static String getCrdRemark(String psst, String crdDate, String eta) throws ParseException {
        String crd1028;
        String remark;
        String crd = crdDate.trim();
        boolean flag = isPsst(psst);
        String sameDate = getSameDate(eta);
        //1028
        if (crd.indexOf("202") != -1 && crd.indexOf("/") != -1) {

            crd1028 = crd.substring(crd.indexOf("202"), crd.length());
            crd1028 = getSameDate(crd1028);
            //返回备注
            return getCRDString(equalsDate(crd1028,sameDate),"");

        }
        if (crdDate.length()==8&&flag){
            String s = equalsDate(crd, sameDate);

           return getCRDString(s,"PSST");


        }
        if (crdDate.length()==8 && !flag){
            String s = equalsDate(crd, sameDate);

            return getCRDString(s,"");


        }
        if (flag){

            return getCRDString("","PSST");

        }

        return "";




    }
    public static String getMMss(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {



            String format1 = format.format(date);

            return format1;
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }

    }


    public static String getDateFormat(String date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date parse = format.parse(date);

            String format1 = format.format(parse);

            return format1;
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }

    }
    //统一日期格式
    public static  String getSameDate(String crd) throws ParseException {
        int length;
        //月份
        int month;
        if(crd.indexOf("-")!=-1){

            crd = crd.replace("-","/");
        }
        if (crd.indexOf("/")!=-1){

           length = crd.length();
           month = crd.substring(crd.indexOf("/")+1, crd.lastIndexOf("/")).length();

        }else if(crd.indexOf("-")!=-1){
            length = crd.length();
            month = crd.substring(crd.indexOf("-")+1, crd.lastIndexOf("-")).length();

        }else {
            System.out.println("发货日期无法解析！");
            return null;
        }
        SimpleDateFormat same = new SimpleDateFormat("YYYY/MM/dd");
        SimpleDateFormat format;
        switch (length){
            case 8:
                if ("21".equals(crd.substring(0,2))){

                    format = new SimpleDateFormat("yy/MM/dd");

                }else {

                    format = new SimpleDateFormat("yyyy/M/d");

                }
                break;
            case 9:
                if (month == 1){

                    format = new SimpleDateFormat("yyyy/M/dd");

                } else {


                    format = new SimpleDateFormat("yyyy/MM/d");
                }
                break;
            case 10:
                format = new SimpleDateFormat("yyyy/MM/dd");
                break;
            default:

                return null;
        }
        String format1 = same.format(new Date(format.parse(crd).getTime()));


        return format1;
    }

    //取得CRD备注
    public static String equalsDate(String crd,String eta) throws ParseException {
        int length = crd.length();
        if(length==0){
            return eta;
        }


        SimpleDateFormat etaFormat = new SimpleDateFormat("yyyy/MM/dd");

        String sameEta = getSameDate(eta);
        String sameCrd = getSameDate(crd);




        try{
            Long crdL = etaFormat.parse(sameCrd).getTime();

            Long etaL = etaFormat.parse(sameEta).getTime();

            System.out.println("crd"+crdL+"=====eta"+etaL);

            //有CRD
            if (crdL-etaL>0) {

                return getSameDate(sameCrd);
            }else {


                return "";
            }

        }catch (Exception e){
            e.printStackTrace();
            return "";

        }

    }

    public static void main(String[] args) throws ParseException {
        BigDecimal data1  = new BigDecimal(1);
        BigDecimal data2 = new BigDecimal(24);
        String mMss = getMMss(new Date());
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");

        Date parse = format.parse("2021/05/19 00:00");



        System.out.println(parse.getTime()+parse.getTime() + (1 / 24)  *24* 60 * 60 * 1000);

       // String format1 = format.format(new Date(parse.getTime() +  * 60 * 60 * 1000));

        int scale = 5;
        double v = data1.divide(data2,10,BigDecimal.ROUND_HALF_UP).doubleValue();

        System.out.println(v);

        String sameDate = getSameDate("21/07/12");
        String substring = "21/07/12".substring(1, 1);
        System.out.println(substring);
        System.out.println(sameDate);
        String createTime = getMMss(new Date());

        System.out.println("c="+createTime);
        String sameDate1 = getSameDate("2021-05-20");
        System.out.println(sameDate1);

        Object customer = new Customer();

        System.out.println(customer.toString());



    }

    public static List<Customer> getCustomerList(){

        List<Customer> customers=null;
        if (customers==null){

            customers = new ArrayList<>();

        }

        return customers;


    }


}