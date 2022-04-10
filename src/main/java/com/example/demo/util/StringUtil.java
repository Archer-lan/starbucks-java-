package com.example.demo.util;

public class StringUtil {
    public static boolean isNull(String str){
        if(str==null){
            return true;
        }
        String trim=str.trim();
        if("".equals(trim)){
            return true;
        }
        return false;
    }
}
