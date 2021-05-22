package com.mars.wang.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MySqlSession {
   private static SqlSession sqlSession;

   public static SqlSession getSqlSession() throws IOException {

       if (sqlSession==null){
           InputStream in = Resources.getResourceAsStream("mybatis-configuration.xml");
           SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
           SqlSessionFactory factory = builder.build(in);
           sqlSession = factory.openSession();

       }
       return sqlSession;

   }




}
