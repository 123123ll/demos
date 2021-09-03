package com.example;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @ClassName: Shorttest
 * @Description: 测试short存储大小
 * @Author: liu
 * @Date: 2021/7/22 18:36
 */
public class Shorttest {
    public static void main(String[] args) throws IOException {
        String name = "TM_01_1";
        int index = name.lastIndexOf("_");
        String sensordir = name.substring(0,index);
        String probedir = name.substring(index,name.length());
        System.out.println(sensordir+probedir);
//        FileOutputStream file = new FileOutputStream("D://bytes//a.data");
//        FileOutputStream file1 = new FileOutputStream("D://bytes//a.txt");
//        Random random = new Random(1);
//        ArrayList<Short> arrs = new ArrayList<Short>();
//        for (int i = 0; i < 60*100*60; i++) {
//            short value = (short) random.nextInt(10000);
//            arrs.add(value);
//        }
//        String s = arrs.toString();
//        byte[] bytes = listToByte(arrs);
//        file1.write(s.getBytes());
//        file.write(bytes);
//        file.close();


        //存储byte与string文件
//        FileOutputStream file = new FileOutputStream("D://bytes//a.data",true);
//        FileOutputStream file1 = new FileOutputStream("D://bytes//a.txt",true);
//        Random random = new Random(1);
//        short[] shortarr = new short[60*100*60];
//        for (int i = 0; i < 60*100*60; i++) {
//            short value = (short) random.nextInt(10000);
//            shortarr[i]=value;
//        }
//        String s = Arrays.toString(shortarr);
//        byte[] bytes = shortToByte(shortarr);
//        file1.write(s.getBytes());
//        file.write(bytes);
//        file.close();

//        //读取byte文件
//        FileInputStream fileInputStream = new FileInputStream("D:\\bytes\\2021\\07\\29\\1627546919786.data");
//        //获取文件大小字节
//        int length=fileInputStream.available();
//
//        //读取文件字节到一个数组中
//        int bytesRead=0;
//        int bytesToRead=length;
//        byte[] input=new byte[bytesToRead];
//        while(bytesRead<bytesToRead) {
//            int result=fileInputStream.read(input,bytesRead,bytesToRead-bytesRead);
//            if(result==-1)
//                break;
//            bytesRead+=result;
//        }
//        short[] shorts = byteToShort(input);
//        System.out.println(Arrays.toString(shorts));
//        fileInputStream.close();
//        System.out.println((bytesRead==length)+"数组长度"+shorts.length);
    }

    /**
     * byte转short
     * @param data
     * @return
     */
    public static short[] byteToShort(byte[] data) {
        short[] shortValue = new short[data.length / 2];
        for (int i = 0; i < shortValue.length; i++) {
            shortValue[i] = (short) ((data[i * 2] & 0xff) | ((data[i * 2 + 1] & 0xff) << 8));
        }
        return shortValue;
    }

    /**
     * short转byte
     * @param data
     * @return
     */
    public static byte[] shortToByte(short[] data) {
        byte[] byteValue = new byte[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            byteValue[i * 2] = (byte) (data[i] & 0xff);
            byteValue[i * 2 + 1] = (byte) ((data[i] & 0xff00) >> 8);
        }
        return byteValue;
    }

    /**
     * list转byte
     * @param data
     * @return
     */
    public static byte[] listToByte(ArrayList data) {
        byte[] byteValue = new byte[data.size() * 2];
        for (int i = 0; i < data.size(); i++) {
            byteValue[i * 2] = (byte) ((short)data.get(i) & 0xff);
            byteValue[i * 2 + 1] = (byte) (((short)data.get(i) & 0xff00) >> 8);
        }
        return byteValue;
    }
}
