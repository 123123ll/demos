package com.example;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @ClassName: lonandlat
 * @Description: 经纬度
 * @Author: liu
 * @Date: 2021/3/23 11:21
 */
public class lonandlat {
    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Double lon = 120.003614;
        Double lat = 30.281957;
        String newlon = randomLonLat(lon,lon+0.01,lat,lat+0.01,"Lon");
        String newlat = randomLonLat(lon,lon+0.01,lat,lat+0.01,"Lat");
        System.out.println(newlon);
        System.out.println(newlat);
        String address = LngAndLatUtil.getLngAndLat(newlon,newlat);
        System.out.println(address);
    }

    /**
     * @Title: randomLonLat
     * @Description: 在矩形内随机生成经纬度
     * @param MinLon：最新经度  MaxLon： 最大经度   MinLat：最新纬度   MaxLat：最大纬度    type：设置返回经度还是纬度
     * @return
     * @throws
     */
    public static String randomLonLat(double MinLon, double MaxLon, double MinLat, double MaxLat, String type) {
        Random random = new Random();
        BigDecimal db = new BigDecimal(Math.random() * (MaxLon - MinLon) + MinLon);
        String lon = db.setScale(6, BigDecimal.ROUND_HALF_UP).toString();// 小数后6位
        db = new BigDecimal(Math.random() * (MaxLat - MinLat) + MinLat);
        String lat = db.setScale(6, BigDecimal.ROUND_HALF_UP).toString();
        if (type.equals("Lon")) {
            return lon;
        } else {
            return lat;
        }
    }
}
