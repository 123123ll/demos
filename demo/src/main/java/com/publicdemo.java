package com;

import com.Utils.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * @ClassName: publicdemo
 * @Description: 测试
 * @Author: liu
 * @Date: 2021/3/31 17:59
 */
public class publicdemo {
    private int a;
    private String s;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public static void main(String[] args) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parse = simpleDateFormat.parse("2004-01-01 00:00:00");
        }catch (Exception e){
            System.out.println("异常");
        }

        
//        Double[] sub = sub("120.212455587544", "30.2554499885554");
//        System.out.println(sub);
//        System.out.println(System.getProperty("java.io.tmpdir"));
    }
//    public static Double[] sub(String lat, String lon){
//        Double[] doubles = new Double[2];
//        int latindex = lat.lastIndexOf(".");
//        Double newlat = Double.valueOf(lat.substring(0,latindex+7));
//        int lonindex = lon.lastIndexOf(".");
//        Double newlon = Double.valueOf(lon.substring(0,lonindex+7));
//        doubles[0] = newlat;
//        doubles[1] = newlon;
//        return doubles;
//    }
}
