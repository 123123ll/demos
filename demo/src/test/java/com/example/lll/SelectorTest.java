package com.example.lll;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Selector;

/**
 * @ClassName: SelectorTest
 * @Description: NIO选择器
 * @Author: liu
 * @Date: 2021/1/5 15:48
 */
public class SelectorTest {
    private static FileInputStream fileInputStream;
    private static FileReader fileReader;
    private static FileWriter fileWriter;
    private static FileOutputStream fileOutputStream;

    static  void in() throws IOException {
        fileReader = new FileReader("D://test.txt");
        char[] chars = new char[1024];
        fileReader.read(chars);
        System.out.println("读字符:"+new String(chars));
        fileReader.close();
//        {
//            try {
//                fileInputStream = new FileInputStream("D://test.txt");
////                ByteBuffer allocate = ByteBuffer.allocate(1024);
//                byte[] bytes = new byte[1024];
//                int len;
//                while ((len=fileInputStream.read(bytes))!=-1){
//                    System.out.println("输出："+new String(bytes,"UTF-8"));
//                }
//                fileInputStream.close();
////                FileChannel channel = fileInputStream.getChannel();
////                channel.read(allocate);
////                System.out.println("输出："+new String(allocate.array(),"UTF-8"));
////                channel.close();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    static void  out() throws IOException {
//        fileWriter = new FileWriter("D://test.txt",true);
//        fileWriter.write("我是谁！");
//        fileWriter.close();
        fileOutputStream = new FileOutputStream("D://test.txt",true);
        fileOutputStream.write("我是谁".getBytes());
        fileOutputStream.close();
    }

    public static void main(String[] args) throws IOException {
        in();
        out();
    }
}
