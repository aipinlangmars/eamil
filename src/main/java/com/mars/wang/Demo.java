package com.mars.wang;

import com.mars.wang.domain.*;
import com.mars.wang.utils.POP3ReceiveMailTest;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.text.ParseException;
import java.util.*;

public class Demo {

    public static void main(String[] args) throws ParseException, MessagingException, IOException {
       /* UserService service = (UserService)ServiceFactory.getService(new UserServiceImpl());

       *//* SqlSession sqlSession = SqlSessionUtil.getSqlSession();

        UserDao mapper = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);*//*

            String md5 = MD5Util.getMD5("12").replace("-","");

            System.out.println("md5="+md5);
            String username ="mars";

            User user = new User(username,md5);
            try {
                User login = service.login(user);

                System.out.println(login);
            }catch (Exception e){

                String message = e.getMessage();
                System.out.println(message);
                e.printStackTrace();
            }*/

        /*List<Data1039> list = getList("1039");

        String address1 = list.get(0).getAddress1();
        System.out.println(address1);*/

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
        folder.open(Folder.READ_WRITE); //打开收件箱
        /*SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd 01:00:00");
        Date parse = sf.parse(sf.format(new Date()));
        System.out.println(sf.format(new Date()));*/



        //SearchTerm st=new SentDateTerm(6,sf.parse(sf.format(new Date())));//6 代表大于等于这时间之后

        Message[] search = folder.getMessages();

        int emails = search.length;


        for (int i=emails-1;i>emails-10;i--){
            MimeMessage mimeMessage =(MimeMessage)search[i];

            String subject = POP3ReceiveMailTest.getSubject(mimeMessage);

            System.out.println(subject);
        }

        System.out.println(search.length);

/*
        String[] data = {"1","2","3"};

        Parent parent =new GetData1028();

        Data1028 data1 = (Data1028)parent.getData(data);

        System.out.println(data1.toString());*/

        /*InputStream inputStream = new FileInputStream(new File("C:\\Users\\Mars\\Desktop\\3B RUBO预报(15).xlsx"));



        Workbook sheets = new XSSFWorkbook(inputStream);

        Sheet sheetAt = sheets.getSheetAt(1);



        int lastRowNum = sheetAt.getLastRowNum();*/




    }

    public static void clan(){








    }
    public static List getList(String string) throws MyException {

        List list = null;

        if ("1039".equals(string)){
            Data1039 data1039 = new Data1039();
            data1039.setAddress1("123");
            list = new ArrayList<Data1039>();
            list.add(data1039);
            return list;


        }else
        {

            return null;
        }



    }

}
