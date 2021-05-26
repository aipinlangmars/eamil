package com.mars.wang.utils;



import com.mars.wang.MyException;
import com.mars.wang.PoiDemo;
import com.mars.wang.domain.*;
import com.mars.wang.utils.getdata.*;
import com.mars.wang.vo.DataVo;
import com.mars.wang.vo.POIVo;
import com.mars.wang.vo.ReadVo;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;

import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFCell;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;



/**
 * 邮件接受测试
 *
 */
/**
 * 使用POP3协议接收邮件
 */
public class POP3ReceiveMailTest {

    public static void main(String[] args) throws Exception {
        resceive("123");
    }

    /**
     * 接收邮件
     */
    public static Message[] resceive(String type) throws Exception {
    /**
         * 因为现在使用的是163邮箱 而163的 pop地址是pop3.163.com 端口是110
         * 比如使用好未来企业邮箱 就需要换成 好未来邮箱的 pop服务器地址 pop.263.net  和   端口 110
          */
    String duankou = "110";   // 端口号
    String servicePath = "pop.qiye.163.com";   // 服务器地址pop3.163.com


        // 准备连接服务器的会话信息
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "pop3");       // 使用pop3协议
        props.setProperty("mail.pop3.port", duankou);           // 端口
        props.setProperty("mail.pop3.host", servicePath);


        // 创建Session实例对象
        Session session = Session.getInstance(props);
        Store store = session.getStore("pop3");
        System.out.println(store);
        store.connect("mars.wang@runbow.com.cn", "Runbow-199109103"); //163邮箱程序登录属于第三方登录所以这里的密码是163给的授权密码而并非普通的登录密码



        // 获得收件箱
        Folder[] defaultFolder = store.getDefaultFolder().list();
        System.out.println(defaultFolder.length);

        Folder folder = store.getFolder("INBOX");
        /* Folder.READ_ONLY：只读权限
         * Folder.READ_WRITE：可读可写（可以修改邮件的状态）
         */

        folder.open(Folder.READ_WRITE); //打开收件箱

        //定义接收时间

        /*SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd 01:00:00");
        Date parse = sf.parse(sf.format(new Date()));
        System.out.println(sf.format(new Date()));
        SearchTerm st=new SentDateTerm(5,sf.parse(sf.format(new Date())));*/



        // 由于POP3协议无法获知邮件的状态,所以getUnreadMessageCount得到的是收件箱的邮件总数
        System.out.println("未读邮件数: " + folder.getUnreadMessageCount());

        // 由于POP3协议无法获知邮件的状态,所以下面得到的结果始终都是为0
        System.out.println("删除邮件数: " + folder.getDeletedMessageCount());
        System.out.println("新邮件: " + folder.getNewMessageCount());

        // 获得收件箱中的邮件总数
        System.out.println("邮件总数: " + folder.getMessageCount());

        Message[] runBow = folder.getMessages();


        // 得到收件箱中的所有邮件,并解析


        /*folder.close(true);
        store.close();*/
        return runBow;


    }


    public static DataVo parseMessage1(String emailType,Message messages) throws MessagingException, IOException {


        // 解析所有邮件

            MimeMessage msg = (MimeMessage) messages;
            String subject = getSubject(msg);
            System.out.println(subject);
            System.out.println(1);
            String dataType="";
            if (subject.indexOf("1039-OB-")!=-1){
                dataType="1039";
                System.out.println("data1039数据");
            }else if(subject.indexOf("RUBO Shipment Plan")!=-1){
                dataType="1028";
            }else if(subject.indexOf("pre-advice")!=-1){
                dataType="1025";
            }else if(subject.indexOf("RUBO-预报")!=-1||subject.indexOf("RUBO-下午")!=-1||subject.indexOf("RUBO-中午")!=-1){
                dataType="1027";
            }
            //boolean flag = subject.indexOf("1039-OB-")!=-1||subject.indexOf("RUBO-预报")!=-1||subject.indexOf("pre-advice")!=-1||subject.indexOf("RUBO Shipment Plan")!=-1;
            System.out.println("------------------解析第" + msg.getMessageNumber() + "封邮件-------------------- ");
            System.out.println("主题: " + getSubject(msg));
            System.out.println("发件人: " + subject);
            System.out.println("收件人：" + getReceiveAddress(msg, null));
            System.out.println("发送时间：" + getSentDate(msg, null));
            System.out.println("是否已读：" + isSeen(msg));
            System.out.println("邮件优先级：" + getPriority(msg));
            System.out.println("是否需要回执：" + isReplySign(msg));
            System.out.println("邮件大小：" + msg.getSize() * 1024 + "kb");
            boolean isContainerAttachment = isContainAttachment(msg);
            System.out.println("是否包含附件：" + isContainerAttachment);

            StringBuffer content = new StringBuffer(30);
            getMailTextContent(msg, content);
            DataVo vo =new DataVo();



            boolean y = content.toString().contains("取消发货");
            //是否取消发货

            if (y){

                System.out.println(getSubject(msg)+getSentDate(msg,null)+"取消发货");
                vo.setMessage(null);
                return vo;
            }

            System.out.println("邮件正文：" + (content.length() > 100 ? content.substring(0,100)+"..." : content));

            System.out.println("------------------第" + msg.getMessageNumber() + "封邮件解析结束-------------------- ");
        System.out.println(dataType);

        if (isContainerAttachment && dataType.length()>1 ) {


                System.out.println("进入文体解析！");

                //save(msg, "E:\\dongli\\"+msg.getSubject() + "_"+"_"); //保存附件

             vo.setDataType(dataType);
             vo.setMessage(msg);
             vo.setSendDate(getSentDate(msg, null));
            return vo;

        }

            vo.setMessage(null);
        vo.setDataType(getSubject(msg));
            return vo;

        }


    /**
     * 解析邮件
     * @param messages 要解析的邮件列表
     */






    /**
     * 解析邮件
     * @param messages 要解析的邮件列表
     */
    public static void deleteMessage(Message ...messages) throws MessagingException, IOException {
        if (messages == null || messages.length < 1)
            throw new MessagingException("未找到要解析的邮件!");

        // 解析所有邮件
        for (int i = 0, count = messages.length; i < count; i++) {

            /**
             *   邮件删除
             */
            Message message = messages[i];
            String subject = message.getSubject();
            // set the DELETE flag to true
            message.setFlag(Flags.Flag.DELETED, true);
            System.out.println("Marked DELETE for message: " + subject);


        }
    }

    /**
     * 获得邮件主题
     * @param msg 邮件内容
     * @return 解码后的邮件主题
     */
    public static String getSubject(MimeMessage msg) throws UnsupportedEncodingException, MessagingException {
        return MimeUtility.decodeText(msg.getSubject());
    }

    /**
     * 获得邮件发件人
     * @param msg 邮件内容
     * @return 姓名 <Email地址>
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public static String getFrom(MimeMessage msg) throws MessagingException, UnsupportedEncodingException {
        String from = "";
        Address[] froms = msg.getFrom();
        if (froms.length < 1)
            throw new MessagingException("没有发件人!");

        InternetAddress address = (InternetAddress) froms[0];
        String person = address.getPersonal();
        if (person != null) {
            person = MimeUtility.decodeText(person) + " ";
        } else {
            person = "";
        }
        from = person + "<" + address.getAddress() + ">";

        return from;
    }

    /**
     * 根据收件人类型，获取邮件收件人、抄送和密送地址。如果收件人类型为空，则获得所有的收件人
     * <p>Message.RecipientType.TO  收件人</p>
     * <p>Message.RecipientType.CC  抄送</p>
     * <p>Message.RecipientType.BCC 密送</p>
     * @param msg 邮件内容
     * @param type 收件人类型
     * @return 收件人1 <邮件地址1>, 收件人2 <邮件地址2>, ...
     * @throws MessagingException
     */
    public static String getReceiveAddress(MimeMessage msg, Message.RecipientType type) throws MessagingException {
        StringBuffer receiveAddress = new StringBuffer();
        Address[] addresss = null;
        if (type == null) {
            addresss = msg.getAllRecipients();
        } else {
            addresss = msg.getRecipients(type);
        }

        if (addresss == null || addresss.length < 1)
            throw new MessagingException("没有收件人!");
        for (Address address : addresss) {
            InternetAddress internetAddress = (InternetAddress)address;
            receiveAddress.append(internetAddress.toUnicodeString()).append(",");
        }

        receiveAddress.deleteCharAt(receiveAddress.length()-1); //删除最后一个逗号

        return receiveAddress.toString();
    }

    /**
     * 获得邮件发送时间
     * @param msg 邮件内容
     * @return yyyy年mm月dd日 星期X HH:mm
     * @throws MessagingException
     */
    public static String getSentDate(MimeMessage msg, String pattern) throws MessagingException {
        Date receivedDate = msg.getSentDate();
        if (receivedDate == null)
            return "";

        if (pattern == null || "".equals(pattern))
            pattern = "yyyy年MM月dd日 E HH:mm ";

        return new SimpleDateFormat(pattern).format(receivedDate);
    }

    /**
     * 判断邮件中是否包含附件
     * @return 邮件中存在附件返回true，不存在返回false
     * @throws MessagingException
     * @throws IOException
     */
    public static boolean isContainAttachment(Part part) throws MessagingException, IOException {
        boolean flag = false;

        if (part.isMimeType("multipart/*")) {
            MimeMultipart multipart = (MimeMultipart) part.getContent();
            int partCount = multipart.getCount();
            for (int i = 0; i < partCount; i++) {

                BodyPart bodyPart = multipart.getBodyPart(i);

                String disp = bodyPart.getDisposition();
                if (disp != null && (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp.equalsIgnoreCase(Part.INLINE))) {
                    flag = true;
                } else if (bodyPart.isMimeType("multipart/*")) {
                    flag = isContainAttachment(bodyPart);
                } else {
                    String contentType = bodyPart.getContentType();
                    if (contentType.indexOf("application") != -1) {
                        flag = true;
                    }

                    if (contentType.indexOf("name") != -1) {
                        flag = true;
                    }
                }

                if (flag) break;
            }
        } else if (part.isMimeType("message/rfc822")) {
            flag = isContainAttachment((Part)part.getContent());
        }
        return flag;
    }


    /**
     * 判断邮件是否已读
     * @param msg 邮件内容
     * @return 如果邮件已读返回true,否则返回false
     * @throws MessagingException
     */

    public static boolean isSeen(MimeMessage msg) throws MessagingException {
        return msg.getFlags().contains(Flags.Flag.SEEN);
    }

    /**
     * 判断邮件是否需要阅读回执
     * @param msg 邮件内容
     * @return 需要回执返回true,否则返回false
     * @throws MessagingException
     */
    public static boolean isReplySign(MimeMessage msg) throws MessagingException {
        boolean replySign = false;
        String[] headers = msg.getHeader("Disposition-Notification-To");
        if (headers != null)
            replySign = true;
        return replySign;
    }

    /**
     * 获得邮件的优先级
     * @param msg 邮件内容
     * @return 1(High):紧急  3:普通(Normal)  5:低(Low)
     * @throws MessagingException
     */
    public static String getPriority(MimeMessage msg) throws MessagingException {
        String priority = "普通";
        String[] headers = msg.getHeader("X-Priority");
        if (headers != null) {
            String headerPriority = headers[0];
            if (headerPriority.indexOf("1") != -1 || headerPriority.indexOf("High") != -1)
                priority = "紧急";
            else if (headerPriority.indexOf("5") != -1 || headerPriority.indexOf("Low") != -1)
                priority = "低";
            else
                priority = "普通";
        }
        return priority;
    }

    /**
     * 获得邮件文本内容
     * @param part 邮件体
     * @param content 存储邮件文本内容的字符串
     * @throws MessagingException
     * @throws IOException
     */
    public static void getMailTextContent(Part part, StringBuffer content) throws MessagingException, IOException {
        //如果是文本类型的附件，通过getContent方法可以取到文本内容，但这不是我们需要的结果，所以在这里要做判断
        boolean isContainTextAttach = part.getContentType().indexOf("name") > 0;
        if (part.isMimeType("text/*") && !isContainTextAttach) {
            content.append(part.getContent().toString());
        } else if (part.isMimeType("message/rfc822")) {
            getMailTextContent((Part)part.getContent(),content);
        } else if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();
            int partCount = multipart.getCount();
            for (int i = 0; i < partCount; i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                getMailTextContent(bodyPart,content);
            }
        }
    }
    private static String remark;
    public static POIVo save(DataVo dataVo, String destDir) throws UnsupportedEncodingException, MessagingException,
            FileNotFoundException, IOException {
        Multipart multipart = (Multipart) dataVo.getMessage().getContent();
        int count = multipart.getCount();
        System.out.println("count="+count);
        InputStream is =null;
        POIVo vo = null;
        remark = getSubject(dataVo.getMessage())+getSentDate(dataVo.getMessage(),null);
        String fileName="";
        for (int i=0;i<count;i++){

                BodyPart bodyPart = multipart.getBodyPart(i);

                String type = bodyPart.getContentType();

            int i1 = type.indexOf('"');

            if (i1!=-1){
                String substring = type.substring(type.indexOf('"') + 1, type.lastIndexOf('"'));

                fileName = MimeUtility.decodeText(substring);

                System.out.println("文件名="+substring);

                System.out.println("解析后2="+fileName);


            }
            int flag=-1;
            if (fileName.indexOf(".xlsx")!=-1){
                flag=1;
            }



                if ((type.indexOf("name")!=-1||type.indexOf("application")!=-1)&&flag!=-1){

                    String disposition = bodyPart.getDisposition();

                    Object content = bodyPart.getContent();
                    System.out.println("disposition="+disposition);
                    System.out.println("type="+type);
                    //System.out.println("description="+description);
                    System.out.println("content="+content);
                    is = bodyPart.getInputStream();
                    vo = new POIVo(is,dataVo.getDataType(),remark);
                    System.out.println("查到附件");
                    return vo;

                    //List<Data1039> list = readExcel("", is, destDir, decodeText(bodyPart.getFileName()));

                }else {

                    continue;

                }



            }
        return null;




        /*if (part.isMimeType("multipart/*")) {
            System.out.println("one");
            Multipart multipart = (Multipart) part.getContent();    //复杂体邮件
            //复杂体邮件包含多个邮件体
            int partCount = multipart.getCount();
            for (int i = 0; i < partCount; i++) {
                //获得复杂体邮件中其中一个邮件体
                BodyPart bodyPart = multipart.getBodyPart(i);

                //某一个邮件体也有可能是由多个邮件体组成的复杂体
                String disp = bodyPart.getDisposition();
                if (disp != null && (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp.equalsIgnoreCase(Part.INLINE))) {
                    InputStream is = bodyPart.getInputStream();
                    System.out.println("执行1");

                    readExcel(is, destDir, decodeText(bodyPart.getFileName()));
                } else if (bodyPart.isMimeType("multipart/*")) {
                    System.out.println("执行2");
                    //save(bodyPart,destDir);
                } else {
                    String contentType = bodyPart.getContentType();
                    if (contentType.indexOf("name") != -1 || contentType.indexOf("application") != -1) {
                        System.out.println("执行3");
                        //saveFile(bodyPart.getInputStream(), destDir, decodeText(bodyPart.getFileName()));
                    }
                }
            }
        } else if (part.isMimeType("message/rfc822")) {
            System.out.println("two");
            save((Part) part.getContent(),destDir);
        }*/
    }

    /**
     * 保存附件递归
     * @param part 邮件中多个组合体中的其中一个组合体
     * @param destDir  附件保存目录
     * @throws UnsupportedEncodingException
     * @throws MessagingException
     * @throws FileNotFoundException
     * @throws IOException
     */
   /* public static void saveAttachment(Part part, String destDir) throws UnsupportedEncodingException, MessagingException,
            FileNotFoundException, IOException {
        if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();    //复杂体邮件
            //复杂体邮件包含多个邮件体
            int partCount = multipart.getCount();
            for (int i = 0; i < partCount; i++) {
                //获得复杂体邮件中其中一个邮件体

                BodyPart bodyPart = multipart.getBodyPart(i);

                System.out.println("FileName"+bodyPart.getFileName());
                //某一个邮件体也有可能是由多个邮件体组成的复杂体
                String disp = bodyPart.getDisposition();
                if (disp != null && (disp.equalsIgnoreCase(Part.ATTACHMENT) || disp.equalsIgnoreCase(Part.INLINE))) {
                    InputStream is = bodyPart.getInputStream();
                    saveFile(is, destDir, decodeText(bodyPart.getFileName()));
                } else if (bodyPart.isMimeType("multipart/*")) {
                    saveAttachment(bodyPart,destDir);
                } else {
                    String contentType = bodyPart.getContentType();
                    if (contentType.indexOf("name") != -1 || contentType.indexOf("application") != -1) {
                        saveFile(bodyPart.getInputStream(), destDir, decodeText(bodyPart.getFileName()));
                    }
                }
            }
        } else if (part.isMimeType("message/rfc822")) {
            saveAttachment((Part) part.getContent(),destDir);
        }
    }*/

    /**
     * 读取输入流中的数据保存至指定目录
     * @param is 输入流
     * @param fileName 文件名
     * @param destDir 文件存储目录
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void saveFile(InputStream is, String destDir, String fileName)
            throws FileNotFoundException, IOException {
        BufferedInputStream bis = new BufferedInputStream(is);
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(new File(destDir + fileName)));
        int len = -1;
        while ((len = bis.read()) != -1) {
            bos.write(len);
            bos.flush();
        }
        bos.close();
        bis.close();
    }

    public static Map<String,List> readExcel(InputStream is, String destDir, String fileName)
            throws FileNotFoundException, IOException, MyException {

        /*
        4、取得sheet的数目
            wb.getNumberOfSheets()
        5、  根据index取得sheet对象
            HSSFSheet sheet = wb.getSheetAt(0);
        6、取得有效的行数
        int rowcount = sheet.getLastRowNum();
        7、取得一行的有效单元格个数

        row.getLastCellNum();

        11、保存Excel文件

        FileOutputStream fileOut = new FileOutputStream(path);
            wb.write(fileOut);
         */
        ReadVo readVo = new ReadVo(fileName);

        Map<String,List> map = new HashMap();

        Workbook wb = new XSSFWorkbook(is);

        Sheet sheet = wb.getSheetAt(readVo.getSheetIndex());



        String hubType = sheet.getSheetName();

        int lastRowNum = sheet.getLastRowNum();

        Row row1 = sheet.getRow(0);

        short lastCellNum1 = row1.getLastCellNum();

        List list = null;

        String[] data= null;


        Parent parent = null;

        if ("1039".equals(fileName)){
            hubType ="1039";


             list= new ArrayList<Data1039>();

             data = new String[lastCellNum1];

             parent = new GetData1039();
        }else

        if ("1028".equals(fileName)){
            hubType ="1028";
            list= new ArrayList<Data1028>();
            data = new String[lastCellNum1];
            parent = new GetData1028();
        }else
        if ("1027".equals(fileName)){
            hubType ="1027";
            list= new ArrayList<Data1027>();
            data = new String[lastCellNum1];
            parent = new GetData1027();
        }else
        if ("1025".equals(fileName)){
            hubType ="1025";
            list= new ArrayList<Data1025>();

            data = new String[lastCellNum1];
            parent = new GetData1025();
        }

        for (int i=readVo.getStartRow();i<=lastRowNum;i++) {

            System.out.println("---------循环第" + i + "次----------");

            Row row = sheet.getRow(i);

            int physicalNumberOfCells = row.getPhysicalNumberOfCells();

            System.out.println(i + "lastRowNum=" + lastRowNum);

            System.out.println(i + "first=" + fileName);

            System.out.println(i + "ph=" + physicalNumberOfCells);


            int nullCount=0;


            for (int col = 0; col < lastCellNum1; col++) {


                Cell cell = row.getCell(col);

               if (cell==null||cell.toString().length()==0){

                   nullCount+=1;

                   data[col] ="";

                   continue;
               }


                //获取是否是日期
                String dataFormatString = cell.getCellStyle().getDataFormatString();

               int type = PoiDemo.getType((XSSFCell)cell);

               if (dataFormatString.indexOf("yy")!=-1 && type==0 ){

                   data[col]= getDateString(cell);

               }else {

                   data[col] = getCellStringValue(cell);
               }



                System.out.println(getCellStringValue(cell));

                //System.out.println(data[col]);


                //System.out.println("单元格"+s+" "+stringCellValue+"  ");


            }
            //System.out.println(i-2+"data="+data.toString());
            System.out.println("nullCount="+nullCount);

            if (nullCount>10&& i ==1) {

                //TODO
                System.out.println(remark + "附件空！");
                map.put("nullFile", list);
                return map;

            }
            if (nullCount>10){

                System.out.println(remark+"读取完成！");
                break;

            }

            ParentData data1039 = parent.getData(data);

            list.add(data1039);


            //System.out.println(data1039.toString());


        }





        /*BufferedInputStream bis = new BufferedInputStream(is);
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(new File(destDir + fileName)));
        int len = -1;
        while ((len = bis.read()) != -1) {
            bos.write(len);
            bos.flush();
        }
        bos.close();
        bis.close();*/

        map.put(fileName,list);

        return map;
    }






    /**
     * 文本解码
     * @param encodeText 解码MimeUtility.encodeText(String text)方法编码后的文本
     * @return 解码后的文本
     * @throws UnsupportedEncodingException
     */
    public static String decodeText(String encodeText) throws UnsupportedEncodingException {
        if (encodeText == null || "".equals(encodeText)) {
            return "";
        } else {
            return MimeUtility.decodeText(encodeText);
        }
    }
    public static String getCellStringValue(Cell cell) {





        String cellValue = "";

        switch (cell.getCellType()) {

            case XSSFCell.CELL_TYPE_STRING://字符串类型

                cellValue = cell.getStringCellValue();

                if(cellValue.trim().equals("")||cellValue.trim().length()<=0)

                    cellValue=" ";

                break;

            case XSSFCell.CELL_TYPE_NUMERIC: //数值类型
                try {
                    String poi = PoiDemo.poi(cell);
                    cellValue = poi;
                }catch (Exception e){

                    //科学计数法转换

                    BigDecimal bigDecimal =new BigDecimal( String.valueOf(cell.getNumericCellValue()));


                    cellValue = bigDecimal.toPlainString();
                }





                break;

            case XSSFCell.CELL_TYPE_FORMULA: //公式

                cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);

                cellValue = String.valueOf(cell.getNumericCellValue());

                break;

            case XSSFCell.CELL_TYPE_BLANK:

                cellValue=" ";

                break;

            case XSSFCell.CELL_TYPE_BOOLEAN:

                break;

            case XSSFCell.CELL_TYPE_ERROR:

                break;

            default:

                break;

        }
        String replace = cellValue.replace(".0", "");

        return replace;

    }

    public static Data1039 get1039(String[] data) throws MyException {
        Data1039 data1039 = new Data1039();
        data1039.setTrailerNumber(data[0]);
        data1039.setTrailerType(data[1]);
        data1039.setCarrierCode(data[2]);
        data1039.setPacklist(data[3]);
        data1039.setProvince(data[4]);
        data1039.setCity(data[5]);
        data1039.setwMSShipDate(data[6]);
        data1039.setShipTo(data[7]);
        data1039.setCrdDate(data[8]);
        data1039.setStName(data[9]);
        data1039.setShipToPhoneNbr(data[10]);
        data1039.setAddress1(data[11]);
        data1039.setDivs(data[12]);
        data1039.setVolume(data[13]);
        data1039.setcTns(data[14]);
        data1039.setUnit(data[15]);
        return data1039;
    }

    public static Data1025 get1025(String[] data) throws MyException {
        Data1025 data1025 = new Data1025();
        data1025.setBu(data[0]);
        data1025.setHubCode(data[1]);
        data1025.setTypeCode(data[2]);
        data1025.setPackList(data[3]);
        data1025.setLog_trailer(data[4]);
        data1025.setCarrier(data[5]);
        data1025.setSaphub(data[6]);
        data1025.setProvice(data[7]);
        data1025.setShipDate(data[8]);
        data1025.setCtns(data[9]);
        data1025.setUnit(data[10]);
        data1025.setWindow(data[11]);
        data1025.setShipTo(data[12]);
        data1025.setCrdDate(data[13]);
        data1025.setPhDate(data[14]);
        data1025.setPsst(data[15]);
        data1025.setStName(data[16]);
        data1025.setPhone(data[17]);
        data1025.setAddress1(data[18]);
        data1025.setAddress2(data[19]);
        data1025.setAddress3(data[20]);
        data1025.setAddress4(data[21]);
        return data1025;

    }

    public static Data1028 get1028(String[] data) throws MyException {
        Data1028 data1028 = new Data1028();
        data1028.setInfoDate(data[0]);
        data1028.setBu(data[1]);
        data1028.setLoadkey(data[2]);
        data1028.setHub(data[3]);
        data1028.setConsigneekey(data[4]);
        data1028.setC_city(data[5]);
        data1028.setCustomerName (data[6]);
        data1028.setC_address3(data[7]);
        data1028.setC_address2(data[8]);
        data1028.setUnit(data[9]);
        data1028.setCtns(data[10]);
        data1028.setCarrier(data[11]);
        data1028.setOrderCoder(data[12]);
        data1028.setShipDate(data[13]);
        data1028.setAtaDate(data[14]);
        data1028.setRemar(data[15]);
        data1028.setRequestDate(data[16]);


        return data1028;

    }
    public static Data1027 get1027(String[] data) throws MyException {
        Data1027 data1027 = new Data1027();
        data1027.setOrdersrtewavedate(data[0]);
        data1027.setOrdersrtewavenbr(data[1]);
        data1027.setWaveparmdesc(data[2]);
        data1027.setShipwavenbr(data[3]);
        data1027.setShipwavedesc(data[4]);
        data1027.setFulfillmentstatusdesc(data[5]);
        data1027.setDivdesc(data[6]);
        data1027.setDistriborderid(data[7]);
        data1027.setShipmentnbr(data[8]);
        data1027.setAppointmentnbr(data[9]);
        data1027.setRequesteddttm(data[10]);
        data1027.setAsgndcarriercd(data[11]);
        data1027.setAssignedshipvia(data[12]);
        data1027.setTpcompanynm(data[13]);
        data1027.setCarrhubcd(data[14]);
        data1027.setOrdersorderdt(data[15]);
        data1027.setShipmentdlvryenddttm(data[16]);
        data1027.setPickupstartdttm(data[17]);
        data1027.setPickupenddttm(data[18]);
        data1027.setTotallpns(data[19]);
        data1027.setTotalunits(data[20]);
        data1027.setPsstflag(data[21]);
        data1027.setShiptocustnbr(data[22]);
        data1027.setPhonenumber(data[23]);
        data1027.setDestfacilitynm(data[24]);
        data1027.setDestaddr1(data[25]);
        data1027.setDestaddr2(data[26]);
        data1027.setDestaddr3(data[27]);
        data1027.setDestcity(data[28]);
        data1027.setDestctrycd(data[29]);
        data1027.setDestpostalcd(data[30]);
        data1027.setDeststateprov(data[31]);

        return data1027;

    }
    public static City getCity(String[] data){
        City city = new City();
        city.setCity(data[0]);
        city.setProvince (data[1]);
        city.setLeadTime (data[2]);
        city.setAirTime (data[3]);
        city.setCarrier (data[4]);
        city.setAirCarrier(data[5]);
        return city;
    }
    public static Customer getCustomer(String[] data){

        Customer city = new Customer();
        city.setC_Code(data[0]);
        city.setC_Name(data[1]);
        city.setAbbreviation(data[2]);
        city.setAddress(data[3]);
        city.setCity(data[4]);
        city.setProvince(data[5]);
        city.setContact(data[6]);
        city.setTelephone(data[7]);
        city.setPhone(data[8]);
        return city;

    }


    public static String getDateString(Cell cell){

        Date javaDate = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String format = dateFormat.format(javaDate);

        return format;


    }



}
