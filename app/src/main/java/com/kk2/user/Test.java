package com.kk2.user;

import com.kk2.user.util.Base64;

import java.io.UnsupportedEncodingException;

public class Test {
    public static void main(String[] args) {

        String message="5L2g5aW9";
        try {
         message=   new String(Base64.decode(message.getBytes("UTF-8"),Base64.DEFAULT),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(message);
    }
}
