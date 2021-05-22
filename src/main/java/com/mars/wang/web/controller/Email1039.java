package com.mars.wang.web.controller;



import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

public class Email1039 extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath();

        System.out.println("url="+url);

        if ("/login/login.do".equals(url)){

            login(request,response);

        }

    }

    protected void login(ServletRequest request, ServletResponse response){

        System.out.println("loginSuccess!");

    }
}
