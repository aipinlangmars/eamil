package com.mars.wang.web.controller;

import com.mars.wang.domain.User;
import com.mars.wang.service.UserService;
import com.mars.wang.service.impl.UserServiceImpl;
import com.mars.wang.utils.MD5Util;
import com.mars.wang.utils.PrintJson;
import com.mars.wang.utils.ServiceFactory;
import com.mars.wang.vo.ResultVo;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getServletPath();

        System.out.println("url="+url);

        if ("/login/login.do".equals(url)){

            login(request,response);

        }

    }

    protected void login(HttpServletRequest request, HttpServletResponse response){

        System.out.println("loginSuccess!");

        UserService userService = (UserService) ServiceFactory.getService(new UserServiceImpl());

        String username = request.getParameter("username");

        String password = request.getParameter("password");

        String md5 = MD5Util.getMD5(password);



        User user = new User(username,md5);
            try {
                User login = userService.login(user);
                System.out.println(login);

                request.getSession().setAttribute("user",login);

                    PrintJson.printJsonFlag(response,true);


            }catch (Exception e){
                e.printStackTrace();
                String message = e.getMessage();

                ResultVo resultVo = new ResultVo();

                resultVo.setMsg(message);

                resultVo.setSuccess(false);

                PrintJson.printJsonObj(response,resultVo);


            }


    }
}
