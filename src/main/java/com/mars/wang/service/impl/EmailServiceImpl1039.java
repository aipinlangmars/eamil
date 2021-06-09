package com.mars.wang.service.impl;

import com.mars.wang.dao.*;
import com.mars.wang.domain.*;
import com.mars.wang.service.EmailService1039;
import com.mars.wang.utils.DataExu;
import com.mars.wang.utils.POP3ReceiveMailTest;
import com.mars.wang.utils.SqlSessionUtil;
import com.mars.wang.vo.DataVo;
import com.mars.wang.vo.POIVo;

import org.apache.ibatis.session.SqlSession;

import javax.mail.Message;

import java.text.ParseException;
import java.util.*;


public class EmailServiceImpl1039  {

    public static void main(String[] args) {
        receiveEmail1039("123","","");
    }

    public static void receiveEmail1039(String hub, String startDate, String endDate) {

        SqlSession sqlSession =SqlSessionUtil.getSqlSession();

        RemarkDao remarkDao = sqlSession.getMapper(RemarkDao.class);

        try {
            Message[] messages = POP3ReceiveMailTest.resceive("");
            System.out.println(messages.length);
            int emails = messages.length;
            System.out.println("收件箱="+emails);
            int count=0;
            POIVo vo;
            for (int i = emails-1;i>emails-50; i-- ){

                //System.out.println(message.getSubject());

                DataVo mimeMessage = POP3ReceiveMailTest.parseMessage1("", messages[i]);
                if (mimeMessage.getMessage()==null){

                    System.out.println("==无附件");

                    continue;
                }

                //判断邮件是否已读过

                vo = POP3ReceiveMailTest.save(mimeMessage,"");
                if (vo==null){

                    continue;
                }
                //邮件备注
                String remark = vo.getRemark();

                int sum = remarkDao.select(remark);
                //判断邮件是否已读过或无附件
                if (sum>0){

                   System.out.println("remark===="+remark+"已读");

                    continue;
                }
                System.out.println(mimeMessage);

                Map map = POP3ReceiveMailTest.readExcel( vo.getInputStream(), "",vo.getFileName());
                //数据插入并返回备注列表
                List o = (List) map.get("nullFile");
                try {
                    o.size();

                    System.out.println(remark+"附件无数据！");
                    continue;
                }catch (Exception e){

                    System.out.println("有数据！");
                }

                List<String> listId = getRemarkList(map, sqlSession);
                RemarkPl remarkPl;
                List<RemarkPl> remarks = new ArrayList<>();
                //生成ID
                String string = UUID.randomUUID().toString();

                String createTime = DataExu.getMMss(new Date());

                System.out.println("createTime========"+createTime);

                String id = string.replace("-","");
                //通过主题单号查询邮件
                System.out.println("id="+id.length());

                Remark remark1= new Remark(id,remark,createTime);
                //客户单号对应的邮件主题
                for (String s:listId){

                    remarkPl = new RemarkPl(id,s,remark,createTime);

                    remarks.add(remarkPl);
                }

                remarkDao.insertRemark(remarks);

                remarkDao.insertRemarkOnly(remark1);

                System.out.println(remark+"插入完成！");

                sqlSession.commit();

            }


        }catch (Exception e){
                e.printStackTrace();
            sqlSession.rollback();



        }finally {
            sqlSession.close();
        }


    }

    private static List<String> getRemarkList(Map map, SqlSession sqlSession) throws ParseException {
        List<String> listId = new ArrayList<>();



        if (map.get("1025")!=null){
            EmailDao1025 dao1025 = sqlSession.getMapper(EmailDao1025.class);
            List<Data1025> list = (List)map.get("1025");
            List<Data1025> list1 = null;

                for (Data1025 data1025:list) {
                    String packList = data1025.getPackList();
                    //统一发货日期
                    data1025.setShipDate(DataExu.getSameDate(data1025.getShipDate()));
                    try{
                        list1 = new ArrayList<>();

                        list1.add(data1025);

                        dao1025.insert1025(list1);

                    }catch (Exception e){

                        System.out.println("1025更新数据id="+packList);
                        dao1025.update1025(data1025);

                    }finally {
                        listId.add(packList);
                    }

                }



        }
        if (map.get("1028")!=null){
            EmailDao1028 dao1028 = sqlSession.getMapper(EmailDao1028.class);
            List<Data1028> list = (List)map.get("1028");
            List<Data1028> list1 = null;

            for (Data1028 data1028:list) {
                //统一发货日期

                String sameDate = DataExu.getSameDate(data1028.getShipDate());
                data1028.setShipDate(sameDate);
                String packList = data1028.getLoadkey();
                try {

                    list1 = new ArrayList<>();
                    list1.add(data1028);

                    dao1028.insert1028(list1);

                } catch (Exception e) {
                    //e.printStackTrace();
                    System.out.println("1028更新数据id=" + packList);
                    dao1028.update1028(data1028);

                } finally {
                    listId.add(packList);
                }


            }

        }
        if (map.get("1039")!=null){
            EmailDao1039 dao1039 = sqlSession.getMapper(EmailDao1039.class);
            List<Data1039> list = (List)map.get("1039");
            List<Data1039> list1 = null;

            for (Data1039 data1039:list) {

                String packList = data1039.getPacklist();
                String date = DataExu.getSameDate(data1039.getwMSShipDate());

                data1039.setwMSShipDate(date);
                try{
                    //统一发货时间
                    list1 = new ArrayList<>();


                    list1.add(data1039);

                    dao1039.insert1039(list1);

                }catch (Exception e){
                    //e.printStackTrace();
                    System.out.println("1039更新数据id="+packList);
                    dao1039.update1039(data1039);

                }finally {
                    listId.add(packList);
                }

            }


        }
        if (map.get("1027")!=null){
            EmailDao1027 dao1027 = sqlSession.getMapper(EmailDao1027.class);
            List<Data1027> list = (List)map.get("1027");
            List<Data1027> list1;

            for (Data1027 data1027:list) {
                String packList = data1027.getDistriborderid();
                //统一发货时间
                data1027.setRequesteddttm(DataExu.getSameDate(data1027.getRequesteddttm()));
                try{

                    list1 = new ArrayList<>();
                    list1.add(data1027);

                    dao1027.insert1027(list1);
                    System.out.println("1027插入id="+packList);

                }catch (Exception e){
                    //e.printStackTrace();
                    System.out.println("1027更新数据id="+packList);
                    dao1027.update1027(data1027);

                }finally {
                    listId.add(packList);
                }

            }

        }
        return listId;


    }
}
