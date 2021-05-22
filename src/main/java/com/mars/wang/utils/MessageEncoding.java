package com.mars.wang.utils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MessageEncoding {
    public static String getISOFileName(Part body){
//设置一个标志，判断文件名从Content-Disposition中获取还是从Content-Type中获取

        boolean flag=true;

        if(body==null){
            return null;

        }

        String[] cdis;

        try{
            cdis=body.getHeader("Content-Disposition");

        }

        catch(Exception e){
            return null;

        }

        if(cdis==null){
            flag=false;

        }

        if(!flag){
            try{
                cdis=body.getHeader("Content-Type");

            }

            catch(Exception e){
                return null;

            }

        }

        if(cdis==null){
            return null;

        }

        if(cdis[0]==null){
            return null;

        }

//从Content-Disposition中获取文件名

        if(flag){
            int pos=cdis[0].indexOf("filename=");

            if(pos<0){
                return null;

            }

//如果文件名带引号

            if(cdis[0].charAt(cdis[0].length()-1)=='"'){
                return cdis[0].substring(pos+10,cdis[0].length()-1);

            }

            return cdis[0].substring(pos+9,cdis[0].length());

        }else{
            int pos=cdis[0].indexOf("name=");

            if(pos<0){
                return null;

            }

//如果文件名带引号

            if(cdis[0].charAt(cdis[0].length()-1)=='"'){
                return cdis[0].substring(pos+6,cdis[0].length()-1);

            }

            return cdis[0].substring(pos+5,cdis[0].length());

        }

    }

}
