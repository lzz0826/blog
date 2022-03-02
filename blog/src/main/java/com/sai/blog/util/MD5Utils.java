package com.sai.blog.util;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    //
//MD5加密類
//@param str 要加密的字符串
//@return 要加密的字符串

    public static String code(String str){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] bytesDigest = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < bytesDigest.length; offset++) {
                i = bytesDigest[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
            //16位的加密
            //return buf.toString().substring(8 , 24);
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return  null;
        }
    }

    public static void main(String[] args){
        System.out.println(code("123456"));

    }
}
