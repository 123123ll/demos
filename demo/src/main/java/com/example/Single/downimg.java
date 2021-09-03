package com.example.Single;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName: downimg
 * @Description: url下载图片
 * @Author: liu
 * @Date: 2021/6/10 11:00
 */
public class downimg {
    public static String downloadFromUrl(String url,String day) {

        try {
            URL httpurl = new URL(url);
            File f = new File("D:/北斗GNSS/"+day + UUID.randomUUID().toString()+".png");
            FileUtils.copyURLToFile(httpurl, f);
        } catch (Exception e) {
            e.printStackTrace();
            return "Fault!";
        }
        return "Successful!";
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");
        String day = simpleDateFormat.format(date);
        String url = "http://api.tianditu.gov.cn/staticimage?center=120.014092,30.289765&width=500&height=500&markers=120.014092,30.289765&markerStyles=-1,,%E9%9C%87%E6%BA%90%E4%BD%8D%E7%BD%AE&zoom=11&layers=img_c,cva_c&tk=7c52acd400d9d901035dbcd899b83d48";
        String s = downloadFromUrl(url, day);
        System.out.println(s);
    }
}
