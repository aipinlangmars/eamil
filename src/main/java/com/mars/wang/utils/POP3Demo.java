package com.mars.wang.utils;



import com.mars.wang.MyException;
import com.mars.wang.domain.Data1039;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
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
import javax.mail.search.*;
import javax.swing.*;


/**
 * 邮件接受测试
 *
 */
/**
 * 使用POP3协议接收邮件
 */
public class POP3Demo {

    public static void main(String[] args) throws Exception {
        resceive();
    }

    /**
     * 接收邮件
     */
    public static void resceive() throws Exception {
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
        for (Folder folder:defaultFolder){

            System.out.println(folder.getName());
        }
        Folder folder = store.getFolder("INBOX");
        /* Folder.READ_ONLY：只读权限
         * Folder.READ_WRITE：可读可写（可以修改邮件的状态）
         */

        folder.open(Folder.READ_WRITE); //打开收件箱



        // 由于POP3协议无法获知邮件的状态,所以getUnreadMessageCount得到的是收件箱的邮件总数
        System.out.println("未读邮件数: " + folder.getUnreadMessageCount());

        // 由于POP3协议无法获知邮件的状态,所以下面得到的结果始终都是为0
        System.out.println("删除邮件数: " + folder.getDeletedMessageCount());
        System.out.println("新邮件: " + folder.getNewMessageCount());

        // 获得收件箱中的邮件总数
        System.out.println("邮件总数: " + folder.getMessageCount());
        //1025
        /*SearchTerm email1025 = new OrTerm(new FromStringTerm("pre-advice"),new BodyTerm("以及PACKLIST所对应的相关信息"));
        Message[] runBow1025 = folder.search(email1025);*/
        //1027
        /*SearchTerm email1027 = new OrTerm(new FromStringTerm("RUBO-预报"),new BodyTerm("附件是1027"));
        Message[] runBow1027 = folder.search(email1027);*/
        //1039
        SearchTerm email1039 = new OrTerm(new FromStringTerm("提货预报-虹迪"),new BodyTerm("WMSShipDate"));
        Message[] runBow1039 = folder.search(email1039);
        //1028
        /*SearchTerm email1028 = new OrTerm(new FromStringTerm("RUBO Shipment Plan"),new BodyTerm("新增发货，谢谢~"));
        Message[] runBow1028 = folder.search(email1028);*/

        // 得到收件箱中的所有邮件,并解析
        Message[] messages = folder.getMessages();
        int count = 0;
        for (Message message:runBow1039){

            count++;


            parseMessage1(message);

            if(count>=1){

                break;
            }

            //System.out.println(message.getReceivedDate());
            //System.out.println("subject="+message.getFileName());
            /*System.out.println("subject="+message.getSubject());
            System.out.println("getContent="+message.getContent());
            System.out.println("getDisposition="+message.getDisposition());
            System.out.println("getFolder="+message.getFolder());
            System.out.println("getFlags()"+message.getFlags());
            System.out.println("getReceivedDate"+message.getReceivedDate());
            System.out.println("Disposition"+message.getDisposition());

            System.out.println("ContentType"+message.getContentType());
            System.out.println("ContentType"+message.getContent());*/
            //System.out.println("getSentDate=="+getSentDate((MimeMessage) message,null)+"主题="+message.getSubject());

        }
        //parseMessage(messages);

        //得到收件箱中的所有邮件并且删除邮件
        //deleteMessage(messages);

        //释放资源
        folder.close(true);
        store.close();
    }


    public static void parseMessage1(Message messages) throws MessagingException, IOException, MyException {
        if (messages ==null)
            throw new MessagingException("未找到要解析的邮件!");

        // 解析所有邮件

        MimeMessage msg = (MimeMessage) messages;
        System.out.println("------------------解析第" + msg.getMessageNumber() + "封邮件-------------------- ");
        System.out.println("主题: " + getSubject(msg));
        System.out.println("发件人: " + getFrom(msg));
        System.out.println("收件人：" + getReceiveAddress(msg, null));
        System.out.println("发送时间：" + getSentDate(msg, null));
        System.out.println("是否已读：" + isSeen(msg));
        System.out.println("邮件优先级：" + getPriority(msg));
        System.out.println("是否需要回执：" + isReplySign(msg));
        System.out.println("邮件大小：" + msg.getSize() * 1024 + "kb");
        boolean isContainerAttachment = isContainAttachment(msg);
        System.out.println("是否包含附件：" + isContainerAttachment);
        if (isContainerAttachment) {
            save(msg, "E:\\dongli\\"+msg.getSubject() + "_"+"_"); //保存附件
        }
        StringBuffer content = new StringBuffer(30);
        getMailTextContent(msg, content);
        System.out.println("邮件正文：" + (content.length() > 100 ? content.substring(0,100) + "..." : content));
        System.out.println("------------------第" + msg.getMessageNumber() + "封邮件解析结束-------------------- ");


    }


    /**
     * 解析邮件
     * @param messages 要解析的邮件列表
     */


    public static void parseMessage(Message ... messages) throws MessagingException, IOException {
        if (messages == null || messages.length < 1)
            throw new MessagingException("未找到要解析的邮件!");

        // 解析所有邮件
        for (int i = 0, count = messages.length; i < count; i++) {
            MimeMessage msg = (MimeMessage) messages[i];
            System.out.println("------------------解析第" + msg.getMessageNumber() + "封邮件-------------------- ");
            System.out.println("主题: " + getSubject(msg));
            System.out.println("发件人: " + getFrom(msg));
            System.out.println("收件人：" + getReceiveAddress(msg, null));
            System.out.println("发送时间：" + getSentDate(msg, null));
            System.out.println("是否已读：" + isSeen(msg));
            System.out.println("邮件优先级：" + getPriority(msg));
            System.out.println("是否需要回执：" + isReplySign(msg));
            System.out.println("邮件大小：" + msg.getSize() * 1024 + "kb");
            boolean isContainerAttachment = isContainAttachment(msg);
            System.out.println("是否包含附件：" + isContainerAttachment);
            if (isContainerAttachment) {
                //saveAttachment(msg, "E:\\dongli\\"+msg.getSubject() + "_"+i+"_"); //保存附件
            }
            StringBuffer content = new StringBuffer(30);
            getMailTextContent(msg, content);
            System.out.println("邮件正文：" + (content.length() > 100 ? content.substring(0,100) + "..." : content));
            System.out.println("------------------第" + msg.getMessageNumber() + "封邮件解析结束-------------------- ");
            System.out.println();

        }
    }


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

    public static void save(Part part, String destDir) throws UnsupportedEncodingException, MessagingException,
            FileNotFoundException, IOException, MyException {
        Multipart multipart = (Multipart) part.getContent();
        int count = multipart.getCount();
        System.out.println("count="+count);
        for (int i=0;i<count;i++){

            BodyPart bodyPart = multipart.getBodyPart(i);

            String type = bodyPart.getContentType();

            if (type.indexOf("name")!=-1||type.indexOf("application")!=-1){

                String disposition = bodyPart.getDisposition();



                String description = bodyPart.getDescription();

                System.out.println("disposition="+disposition);
                System.out.println("type="+type);
                System.out.println("description="+description);


                InputStream is = bodyPart.getInputStream();



                System.out.println("执行1");

                readExcel(is, destDir, decodeText(bodyPart.getFileName()));
            }else {

                continue;

            }






        }




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
    private static List<Data1039> readExcel(InputStream is, String destDir, String fileName)
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

        Workbook wb = new XSSFWorkbook(is);

        Sheet sheet = wb.getSheet("明细");

        String sheetName = sheet.getSheetName();

        int lastRowNum = sheet.getLastRowNum();

        Data1039 data1039 = null;

        List<Data1039> list = new ArrayList<>();

        String[] data = new String[14];



        for (int i=1;i<lastRowNum;i++) {
            System.out.println("---------循环第" + i + "次----------");
            Row row = sheet.getRow(i);
            data1039 = new Data1039();
            short lastCellNum = row.getLastCellNum();

            short firstCellNum = row.getFirstCellNum();
            int physicalNumberOfCells = row.getPhysicalNumberOfCells();

            System.out.println(i + "last=" + lastCellNum);

            System.out.println(i + "first=" + fileName);

            System.out.println(i + "ph=" + physicalNumberOfCells);


            for (int col = 0; col < lastCellNum; col++) {


                Cell cell = row.getCell(col);
                //Comment cellComment = cell.getCellComment();
                if (cell == null) {
                    System.out.println("单元格为空直接跳过！");
                    continue;
                }

                data[col-2] = getCellStringValue(cell);


                //System.out.println("单元格"+s+" "+stringCellValue+"  ");


            }
            //System.out.println(i-2+"data="+data.toString());

            data1039.setCarrierCode(data[0]);
            data1039.setPacklist(data[1]);
            data1039.setProvince(data[2]);
            data1039.setCity(data[3]);
            data1039.setwMSShipDate(data[4]);
            data1039.setShipTo(data[5]);
            data1039.setCrdDate(data[6]);
            data1039.setStName(data[7]);
            data1039.setShipToPhoneNbr(data[9]);
            data1039.setAddress1(data[9]);
            data1039.setDivs(data[10]);
            data1039.setVolume(data[11]);
            data1039.setcTns(data[12]);
            data1039.setUnit(data[13]);

            System.out.println(data1039.toString());

            list.add(data1039);
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

        return null;
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

                cellValue = String.valueOf(cell.getNumericCellValue());

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

        return cellValue;

    }



}
