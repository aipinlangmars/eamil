package com.mars.wang;

import com.mars.wang.dao.CityDao;
import com.mars.wang.dao.CustomerDao;
import com.mars.wang.domain.City;
import com.mars.wang.domain.Customer;
import com.mars.wang.domain.Data1039;
import com.mars.wang.domain.ParentData;
import com.mars.wang.utils.POP3ReceiveMailTest;
import com.mars.wang.utils.SqlSessionUtil;

import com.mars.wang.utils.getdata.Parent;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.sound.midi.SoundbankResource;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PoiDemo {

    public static void main(String[] args) throws IOException {
        InputStream is = new FileInputStream(new File("C:\\Users\\Mars\\Desktop\\1.xlsx"));
        Workbook wb = new XSSFWorkbook(is);

        Sheet sheet = wb.getSheetAt(0);

        String sheetName = sheet.getSheetName();

        int lastRowNum = sheet.getLastRowNum();

        int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();

        System.out.println("p="+physicalNumberOfRows);

        System.out.println(lastRowNum);

        List<Customer> list = new ArrayList();
        ParentData data =null;
        Parent parent =null;

        Customer cus = null;








        for (int i=1;i<=10;i++){

            Row row = sheet.getRow(i);

            short lastCellNum = row.getLastCellNum();

            System.out.println("第"+i+"行"+lastCellNum+" p"+row.getPhysicalNumberOfCells());

            //String[] strings = new String[lastCellNum];
            for (int count=0;count<lastCellNum;count++){

                Cell cell = row.getCell(count);
                if (cell==null||cell.toString().length()==0){
                    System.out.print("异常");
                    //strings[count]="";
                    continue;
                }

                try {
                    //short dataFormat = cell.getCellStyle().getDataFormat();
                    //String stringCellValue = cell.toString();
                    int type = getType((XSSFCell) cell);

                    String dataFormatString = cell.getCellStyle().getDataFormatString();
                    System.out.println(dataFormatString);
                    String value = null;
                    System.out.print( cell.getStringCellValue()+"类型=("+type+")");

                    if (dataFormatString.indexOf("yy")>=0 && type==0){


                        Date javaDate = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());



                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

                        String format = dateFormat.format(javaDate);
                        value = format;

                    }else {
                        value = cell.getStringCellValue();
                    }
                    System.out.print(value);





                    /*String poi = poi(cell);



                    System.out.print(poi+" ");*/
                }catch (Exception e){

                    System.out.print("类型不符===");
                }





                int cellType = cell.getCellType();

                //strings[count] = POP3ReceiveMailTest.getCellStringValue(cell);
                //System.out.print(cellDateFormatted+"="+cellType+"="+POP3ReceiveMailTest.getCellStringValue(cell)+"   ");


            }
            //cus = POP3ReceiveMailTest.getCustomer(strings);

            list.add(cus);



        }
        /*SqlSession  session = SqlSessionUtil.getSqlSession();
        CustomerDao mapper = session.getMapper(CustomerDao.class);




        try {
            mapper.insertCustomer(list);

            session.commit();
        }catch (Exception e){

            e.printStackTrace();
            session.rollback();
        }finally {
            session.close();
        }*/


    }
    public static int getType(XSSFCell cell){
        int type=100;
        switch (cell.getCellType()){

            case XSSFCell.CELL_TYPE_STRING:
                type = 1;
                break;
            case XSSFCell.CELL_TYPE_NUMERIC: //数值类型



                type=0;

                break;

            case XSSFCell.CELL_TYPE_FORMULA: //公式


                type=2;
                break;

            case XSSFCell.CELL_TYPE_BLANK:


                type=3;
                break;

            case XSSFCell.CELL_TYPE_BOOLEAN:
                type=4;
                break;

            case XSSFCell.CELL_TYPE_ERROR:
                type=5;
                break;

            default:

                break;

        }




        return type;

    }
    public static String poi(Cell cell){
        String format1=null;

        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
            short format = cell.getCellStyle().getDataFormat();
            SimpleDateFormat sdf = null;
            if(format == 14 || format == 31 || format == 57 || format == 58){
                //日期
                sdf = new SimpleDateFormat("yyyy-MM-dd");
            }else if (format == 20 || format == 32) {
                //时间
                sdf = new SimpleDateFormat("HH:mm");
            }
            double value = cell.getNumericCellValue();
            Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
            format1 = sdf.format(date);
        }
        System.out.print(format1);

        return format1;

    }


}
