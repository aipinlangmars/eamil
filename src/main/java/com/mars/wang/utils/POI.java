package com.mars.wang.utils;

import com.mars.wang.domain.Customer;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.util.*;

public class POI {
    public static void main(String[] args) throws IOException, MessagingException {
        //writeExcel("e:\\powernode\\Excel\\mars.xlsx",new ArrayList<Customer>());

        //sendMessage();

        Customer customer;
        List<Object> list = new ArrayList();
        for (int i = 0 ;i<10;i++){
            customer = new Customer();
            customer.setC_Code("000"+i);
            list.add(customer);

        }
        //List<List<String>> classMap = getClassMap(list, new Customer());
        writeExcel("",list,new Customer());











        /*String[] excelHeads = getExcelHeads(customer);
        for (int i = 0;i<excelHeads.length;i++){

            System.out.println(excelHeads[i]);

        }*/
    }
    public static List<List<String>> getClassMap(List<Object> list,Object object){
        String[] excelHeads = getExcelHeads(object);
        List<List<String>> listS= new ArrayList<>();


            for(Object o:list){

                List<String> value = getValue(o);

                listS.add(value);


            }



        return listS;
    }
    public static List<String> getValue(Object object){
        List<String> list = new ArrayList<>();
        String[] split = object.toString().split("'");
        for (int i = 0;i<split.length;i++){
            //过滤其他值
            if (split[i].indexOf("=")!=-1||split[i].indexOf("}")!=-1){
                continue;
            }
            list.add(split[i]);

        }

        return list;

    }
    public static String[] getExcelHeads(Object object){

        String string = object.toString();
        String title;
        String[] split = string.split("=");
        String[] strings = new String[split.length-1];
        for (int i =0;i<split.length;i++){
            title = split[i];

            if (title.indexOf(",")!=-1){
                strings[i]=title.split(",")[1];

            }else if (title.indexOf("{")!=-1){
                strings[i]=title.split("\\{")[1];
            }

        }

        return strings;

    }


    public static String writeExcel(String filePath, List<Object> list,Object target) throws IOException {


        //定义表头
        String path = "E:\\powernode\\Excel\\bodyPart";

        String[] title=getExcelHeads(target);

//创建excel工作簿
        XSSFWorkbook workbook=new XSSFWorkbook();
//创建工作表sheet
        XSSFSheet sheet=workbook.createSheet();
//创建第一行
        XSSFRow row=sheet.createRow(0);
        XSSFCell cell=null;
//插入第一行数据的表头
        for(int i=0;i<title.length;i++){
            cell=row.createCell(i);
            cell.setCellValue(title[i]);
        }
//写入数据
        List<List<String>> classMap = getClassMap(list, new Customer());
        for (int rows=1;rows<=classMap.size();rows++){

            XSSFRow row1 = sheet.createRow(rows);
            List<String> list1 = classMap.get(rows - 1);
            for (int col = 0;col<list1.size();col++){
                row1.createCell(col).setCellValue(list1.get(col));
            }

        }
        UUID uuid = UUID.randomUUID();
        if (!new File(path).exists()){
            new File(path).mkdirs();
        }
        String fileName = "\\"+ uuid.toString().replace("-", "")+".xlsx";

//创建excel文件

        File file=new File(path+fileName);


        FileOutputStream stream;
        try {
            file.createNewFile();
            //将excel写入
            stream= FileUtils.openOutputStream(file);
            workbook.write(stream);
            stream.close();
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();

        }finally {

        }
        return null;

    }


        /**
         * 生成一份本地的邮件
         * @param
         * @throws MessagingException
         * @throws IOException
         */
      /*  public static void writeMessage() throws MessagingException, IOException {
            //环境
            Session session = Session.getDefaultInstance(new Properties());

            //邮件
            MimeMessage msg = new MimeMessage(session);
            //设置主题
            msg.setSubject("test123456");
            //发件人，注意中文的处理
            msg.setFrom(new InternetAddress("\"" + MimeUtility.encodeText("mars") + "\"<mars.wang@runbow.com.cn>"));
            //设置邮件回复人
            msg.setReplyTo(new Address[]{new InternetAddress("2722918483@qq.com")});

            //整封邮件的MINE消息体
            MimeMultipart msgMultipart = new MimeMultipart("mixed");//混合的组合关系
            //设置邮件的MINE消息体
            msg.setContent(msgMultipart);

            //附件1
            MimeBodyPart attch1 = new MimeBodyPart();
            //附件2
            MimeBodyPart attch2 = new MimeBodyPart();
            //正文内容
            MimeBodyPart content = new MimeBodyPart();

            //把内容，附件1，附件2加入到 MINE消息体中
            msgMultipart.addBodyPart(attch1);
            msgMultipart.addBodyPart(attch2);
            msgMultipart.addBodyPart(content);

            //把文件，添加到附件1中
            //数据源
            DataSource ds1 = new FileDataSource(new File("E:\\powernode\\Excel\\mars.xlsx"));
            //数据处理器
            DataHandler dh1 = new DataHandler(ds1 );
            //设置第一个附件的数据
            attch1.setDataHandler(dh1);
            //设置第一个附件的文件名
            attch1.setFileName("mars.xlsx");

            //把文件，添加到附件2中
            DataSource ds2 = new FileDataSource(new File("E:\\powernode\\Excel\\mars.xlsx"));
            DataHandler dh2 = new DataHandler(ds2 );
            attch2.setDataHandler(dh2);
            attch2.setFileName(MimeUtility.encodeText( "mars2.xlsx"));

            //正文（图片和文字部分）
            MimeMultipart bodyMultipart  = new MimeMultipart("related");
            //设置内容为正文
            content.setContent(bodyMultipart);

            //html代码部分
            MimeBodyPart htmlPart = new MimeBodyPart();
            //html中嵌套的图片部分
            MimeBodyPart imgPart = new MimeBodyPart();

            //正文添加图片和html代码
            bodyMultipart.addBodyPart(htmlPart);
            bodyMultipart.addBodyPart(imgPart);

            //把文件，添加到图片中
            DataSource imgds = new FileDataSource(new File("E:\\powernode\\Excel\\mars.png"));
            DataHandler imgdh = new DataHandler(imgds );
            imgPart.setDataHandler(imgdh);
            //说明html中的img标签的src，引用的是此图片
            imgPart.setHeader("Content-Location", "http://sunteam.cc/logo.jsg");

            //html代码
            htmlPart.setContent("<span style='color:red'>中文呵呵</span><img src=\"http://sunteam.cc/logo.jsg\">","text/html;charset=utf-8");

            //生成文件邮件
            msg.saveChanges();

            //输出
            OutputStream os = new FileOutputStream("E:\\powernode\\Excel\\demo.eml");
            msg.writeTo(os);
            os.close();
        }*/



    public static void sendMessage() throws FileNotFoundException, MessagingException, UnsupportedEncodingException {
       /* String duankou = "110";   // 端口号
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
        store.connect("mars.wang@runbow.com.cn", "Runbow-199109103");
*/

        // 属性对象
        Properties properties = new Properties();
        // 开启debug调试 ，打印信息
        properties.setProperty("mail.debug", "true");
        // 发送服务器需要身份验证
        properties.setProperty("mail.smtp.auth", "true");
        // 发送服务器端口，可以不设置，默认是25
        properties.setProperty("mail.smtp.port", "25");
        // 发送邮件协议名称
        properties.setProperty("mail.transport.protocol", "smtp");
        // 设置邮件服务器主机名
        properties.setProperty("mail.host", "smtp.qiye.163.com");
        /*String duankou = "25";   // 端口号
        String servicePath = "pop.qiye.163.com";   // 服务器地址pop3.163.com*/

        // 准备连接服务器的会话信息
        /*Properties props = new Properties();
        props.setProperty("mail.store.protocol", "pop3");       // 使用pop3协议
        props.setProperty("mail.pop3.port", duankou);           // 端口
        props.setProperty("mail.pop3.host", servicePath);*/
        /*Session session = Session.getInstance(properties);
        Store store = session.getStore("pop3");
        System.out.println(store);
        store.connect("mars.wang@runbow.com.cn", "Runbow-199109103");*/
        // 环境信息
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 在session中设置账户信息，Transport发送邮件时会使用
                return new PasswordAuthentication( "mars.wang@runbow.com.cn", "Runbow-199109103");
            }
        });
        System.out.println(session.getStore("pop3"));
        Transport pop3 = session.getTransport("smtp");
        /**
         * Message对象将存储我们实际发送的电子邮件信息，
         * Message对象被作为一个MimeMessage对象来创建并且需要知道应当选择哪一个JavaMail session。
         */


        /**
         * Session类代表JavaMail中的一个邮件会话。
         * 每一个基于JavaMail的应用程序至少有一个Session（可以有任意多的Session）。
         *
         * JavaMail需要Properties来创建一个session对象。
         * 寻找"mail.smtp.host"    属性值就是发送邮件的主机
         * 寻找"mail.smtp.auth"    身份验证，目前免费邮件服务器都需要这一项
         */


        /***
         * 邮件是既可以被发送也可以被受到。JavaMail使用了两个不同的类来完成这两个功能：Transport 和 Store。
         * Transport 是用来发送信息的，而Store用来收信。对于这的教程我们只需要用到Transport对象。
         */
        Transport transport;

        String mailHost="";
        String sender_username="";
        String sender_password="";
        String receiveUser="2722918483@qq.com";
        String subject="subject";
        String sendHtml="sendHtml";
        File attachment = new File("E:\\powernode\\Excel\\mars.xlsx");

        //编辑邮件
         MimeMessage message = new MimeMessage(session);
        //InternetAddress from = new InternetAddress(MimeUtility.encodeWord("幻影")+" <"+"mars.wang@runbow.com.cn"+">");
        //message.setFrom(from);


        /*// 收件人
        InternetAddress to = new InternetAddress(receiveUser);
        message.setRecipient(Message.RecipientType.TO, to);//还可以有CC、BCC

        // 邮件主题
        message.setSubject(subject);

        String content = sendHtml.toString();
        // 邮件内容,也可以使纯文本"text/plain"
        message.setContent(content, "text/html;charset=UTF-8");

        // 保存邮件
        message.saveChanges();*/


        InternetAddress from = new InternetAddress("mars.wang@runbow.com.cn");
        message.setFrom(from);

        // 收件人
        InternetAddress to = new InternetAddress(receiveUser);
        message.setRecipient(Message.RecipientType.TO, to);

        // 邮件主题
        message.setSubject(subject);

        // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
        Multipart multipart = new MimeMultipart();

        // 添加邮件正文
        BodyPart contentPart = new MimeBodyPart();
        contentPart.setContent(sendHtml, "text/html;charset=UTF-8");
        multipart.addBodyPart(contentPart);

        // 添加附件的内容
        if (attachment != null) {
            BodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachment);
            attachmentBodyPart.setDataHandler(new DataHandler(source));

            // 网上流传的解决文件名乱码的方法，其实用MimeUtility.encodeWord就可以很方便的搞定
            // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
            //sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
            //messageBodyPart.setFileName("=?GBK?B?" + enc.encode(attachment.getName().getBytes()) + "?=");

            //MimeUtility.encodeWord可以避免文件名乱码
            attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
            multipart.addBodyPart(attachmentBodyPart);
        }

        // 将multipart对象放到message中
        message.setContent(multipart);
        // 保存邮件
        message.saveChanges();






        //读取本地邮件
        //essage message = new MimeMessage(session, new FileInputStream(new File("E:\\powernode\\Excel\\demo.eml")));

        //发送邮件



        pop3.send(message, InternetAddress.parse(receiveUser) );


    }
    /*public void doSendHtmlEmail(String subject, String sendHtml, String receiveUser, File attachment) {
        MimeMessage message;
        Session session;
        Transport transport=null;

        String mailHost = "";
        String sender_username = "";
        String sender_password = "";

        Properties properties = new Properties();
        try {
            session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // 在session中设置账户信息，Transport发送邮件时会使用
                    return new PasswordAuthentication( "mars.wang@runbow.com.cn", "Runbow-199109103");
                }
            });
            message = new MimeMessage(session);
            // 发件人
            InternetAddress from = new InternetAddress(sender_username);
            message.setFrom(from);

            // 收件人
            InternetAddress to = new InternetAddress(receiveUser);
            message.setRecipient(Message.RecipientType.TO, to);

            // 邮件主题
            message.setSubject(subject);

            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();

            // 添加邮件正文
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setContent(sendHtml, "text/html;charset=UTF-8");
            multipart.addBodyPart(contentPart);

            // 添加附件的内容
            if (attachment != null) {
                BodyPart attachmentBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(attachment);
                attachmentBodyPart.setDataHandler(new DataHandler(source));

                // 网上流传的解决文件名乱码的方法，其实用MimeUtility.encodeWord就可以很方便的搞定
                // 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
                //sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
                //messageBodyPart.setFileName("=?GBK?B?" + enc.encode(attachment.getName().getBytes()) + "?=");

                //MimeUtility.encodeWord可以避免文件名乱码
                attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
                multipart.addBodyPart(attachmentBodyPart);
            }

            // 将multipart对象放到message中
            message.setContent(multipart);
            // 保存邮件
            message.saveChanges();

            transport = session.getTransport("smtp");
            // smtp验证，就是你用来发邮件的邮箱用户名密码
            transport.connect(mailHost, sender_username, sender_password);
            // 发送
            transport.sendMessage(message, message.getAllRecipients());

            System.out.println("send success!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/






}
