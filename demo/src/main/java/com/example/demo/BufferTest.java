package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName: BufferTest
 * @Description: nio缓存区
 * @Author: liu
 * @Date: 2020/12/30 15:17
 */
public class BufferTest {
    public static void main(String[] args) throws IOException {
        File file = new File("D://test.txt");
        if (!file.exists()){
            try {
                file.createNewFile();
            }catch (IOException e){
                System.out.println("创建失败！");
            }
        }
//        String s = "测试数据输出！";
//        FileOutputStream fileOutputStream = new FileOutputStream(file);
//        FileChannel outchannel = fileOutputStream.getChannel();
//        ByteBuffer b = ByteBuffer.wrap(s.getBytes());
//        outchannel.write(b);
//        System.out.println("写出");
//        outchannel.close();
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel inputStreamChannel = fileInputStream.getChannel();
        ByteBuffer allocate = ByteBuffer.allocate((int) file.length());
//        int bb = inputStreamChannel.read(allocate);
        while (inputStreamChannel.read(allocate) > 0 ){
            allocate.flip();
            while (allocate.hasRemaining()){
                    byte[] bytes = new byte[3];
                    byte[] array = allocate.get(bytes, 0, bytes.length).array();
                System.out.println("二进制字符串："+conver2HexStr(array));
                     System.out.println(new String(bytes,"UTF-8"));
//                    for (int i1 = 1; i1 < array.length+1; i1++) {
//
//                        if (i1>3){
//                            bytes[i1%3]=array[i1-1];
//                        }else {
//                            bytes[i1-1]=array[i1-1];
//                        }
//                        if (i1-1%3==0){
//                            System.out.println(new String(bytes,"UTF-8"));
//
//                    }
//
                }
            }
        }
//        byte[] bytes = new byte[3];
//        while (fileInputStream.read(bytes)!=-1){
//            System.out.println(new String(bytes));
//        }
//        System.out.println(new String(allocate.array()));
//        System.out.println("写入");
//        inputStreamChannel.close();
//    }
//    public static void main(String[] args) {
//        IntBuffer buffer = IntBuffer.allocate(10);
//        int[] array = {3, 5, 1};
//        buffer = buffer.wrap(array);
//        buffer.put(0,7);
//        for (int i=0;i<buffer.limit();i++){
//            System.out.println(buffer.get()+"\t");
//        }
//        System.out.println("------------修改缓存区数据原数组也会被改变");
//        for (int i : array){
//            System.out.println(i+"\t");
//        }
//    }

//    public static void main(String[] args) throws IOException {
//        File file = new File("D://file.txt");
//        if (!file.exists()){
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        FileOutputStream fileOutputStream = new FileOutputStream(file);
//        FileChannel channel = fileOutputStream.getChannel();
//        String s = "沈琪大傻逼！";
//        System.out.println(channel.isOpen());
//        System.out.println("byte:"+ Arrays.toString(s.getBytes()));
//        channel.write(ByteBuffer.wrap(s.getBytes()));
//        FileInputStream fileInputStream = new FileInputStream(file);
//        FileChannel inputchannel = fileInputStream.getChannel();
//        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
//        inputchannel.read(byteBuffer);
//        System.out.println(new String(byteBuffer.array()));
//        fileInputStream.close();
//    }


    /**
     * byte数组转换为二进制字符串,每个字节以","隔开
     * **/
    public static String conver2HexStr(byte [] b)
    {
        StringBuffer result = new StringBuffer();
        for(int i = 0;i<b.length;i++)
        {
            result.append(Long.toString(b[i] & 0xff, 2)+",");
        }
        return result.toString().substring(0, result.length()-1);
    }

    /**
     * 二进制字符串转换为byte数组,每个字节以","隔开
     * **/
    public static byte[] conver2HexToByte(String hex2Str)
    {
        String [] temp = hex2Str.split(",");
        byte [] b = new byte[temp.length];
        for(int i = 0;i<b.length;i++)
        {
            b[i] = Long.valueOf(temp[i], 2).byteValue();
        }
        return b;
    }
}
