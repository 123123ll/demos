package com.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * @ClassName: bytetest
 * @Description: 二进制存储传输
 * @Author: liu
 * @Date: 2021/7/13 17:11
 */
public class bytetest {
    public static void main(String[] args) throws IOException {
        FileOutputStream file = new FileOutputStream("D://bytes//a.data",true);
        int[] ints = {1,2,3,4,5,6,7,8,9};
        System.out.println("tostring:"+ints.toString());
        for (int i = 0; i < ints.length; i++) {
            file.write(getBytes(ints[i]));
        }
        file.close();

        FileInputStream fileInputStream = new FileInputStream("D://bytes//a.data");
        byte[] bytes = new byte[1024];
        int i = 0;
        if ((i = fileInputStream.read(bytes))!=-1){
            byte[] b = new byte[i];
            System.arraycopy(bytes,0,b,0,i);
            System.out.println(Arrays.toString(bytesToInts(b)));
        }
    }

    public static int[] bytesToInts(byte[] bytes){
        int bytesLength=bytes.length;
        int[] ints=new int[bytesLength%4==0? bytesLength/4:bytesLength/4+1];
        int lengthFlag=4;
        while (lengthFlag<=bytesLength){
            ints[lengthFlag/4-1]=(bytes[lengthFlag-4]<<24)|(bytes[lengthFlag-3]&0xff)<<16|
                    (bytes[lengthFlag-2]&0xff)<<8|(bytes[lengthFlag-1]&0xff);
            lengthFlag+=4;
        }
        for (int i=0;i<bytesLength+4-lengthFlag;i++){
            if (i==0) ints[lengthFlag/4-1]|=bytes[lengthFlag-4+i]<<8*(bytesLength+4-lengthFlag-i-1);
            else ints[lengthFlag/4-1]|=(bytes[lengthFlag-4+i]&0xff)<<8*(bytesLength+4-lengthFlag-i-1);
        }
        return ints;
    }

    /**
     * 将整型数值转换为字节数组
     *
     * @param data
     * @return
     */
    public static byte[] getBytes(int data) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) ((data & 0xff000000) >> 24);
        bytes[1] = (byte) ((data & 0xff0000) >> 16);
        bytes[2] = (byte) ((data & 0xff00) >> 8);
        bytes[3] = (byte) (data & 0xff);
        return bytes;
    }
}
