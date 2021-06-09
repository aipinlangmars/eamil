package com.mars.wang.service.impl;

import com.mars.wang.dao.RemarkDao;
import com.mars.wang.dao.TReportDao;
import com.mars.wang.domain.Remark;
import com.mars.wang.domain.RemarkPl;
import com.mars.wang.domain.wci.TReport;
import com.mars.wang.service.WCIDataService;
import com.mars.wang.utils.DataExu;
import com.mars.wang.utils.POI;
import com.mars.wang.utils.POP3ReceiveMailTest;
import com.mars.wang.utils.SqlSessionUtil;
import com.mars.wang.vo.DataVo;
import com.mars.wang.vo.POIVo;
import org.apache.ibatis.session.SqlSession;

import javax.mail.Message;
import java.io.InputStream;
import java.util.*;

public class WCIData implements WCIDataService {
    @Override
    public void readExcelData() throws Exception{

    }

    //
    public static void readExcelDat() throws Exception {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();

        RemarkDao remarkDao = sqlSession.getMapper(RemarkDao.class);
        TReportDao tReportDao = sqlSession.getMapper(TReportDao.class);


        Message[] messages = POP3ReceiveMailTest.resceive("");
        System.out.println(messages.length);
        int emails = messages.length;
        System.out.println("收件箱=" + emails);
        int count = 0;
        POIVo vo;
        for (int i = emails - 1; i > emails - 25; i--) {

            //System.out.println(message.getSubject());
            DataVo mimeMessage = POP3ReceiveMailTest.getMessage("WCI数据推送mars",messages[i]);
            if (mimeMessage.getMessage() == null) {

                System.out.println("==无附件");

                continue;
            }
            //处理邮件附件

            InputStream excelInputStream = POP3ReceiveMailTest.getExcelInputStream(messages[i]);

            List<List<String>> lists = POI.readExcel(new TReport(), excelInputStream, 2);

            List<TReport> tReports = new ArrayList<>();

            if (excelInputStream!=null&&lists.size()>0){

               tReports = (List) POI.getTReport("TReport",lists);

            }else {

                System.out.println("未正确取得类集合！");
            }
            try {
                for (TReport tReport:tReports){

                    try{
                        tReportDao.insertTReportByOne(tReport);
                        //System.out.println(tReport);
                    }catch (Exception e){
                        e.printStackTrace();
                        System.out.println("TReport数据重复选择更新!");
                        tReportDao.updateTReport(tReport);
                    }

                }
                sqlSession.commit();
            }catch (Exception e){
                sqlSession.rollback();

            }finally {
                sqlSession.close();
            }





        }

    }

    public static void main(String[] args) throws Exception {
        readExcelDat();

    }
}