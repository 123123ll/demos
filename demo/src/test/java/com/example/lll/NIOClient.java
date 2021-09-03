package com.example.lll;

/**
 * @ClassName: NIOClient
 * @Description: 客户端
 * @Author: liu
 * @Date: 2021/1/6 18:11
 */
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;


/**
 * channel 注册到selector 上 必须制定关注的事件类型。具体的事件承载于Selectored上。
 */

public class NIOClient {

    public static String IP = "127.0.0.1";
    static Selector selector;
    final static int port = 9000;
    static Charset charset = Charset.forName("UTF-8");
    private static SocketChannel socketChannel;

    NIOClient() throws IOException {
        selector = Selector.open();
        socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(IP, port));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    private static void receiveRead(SelectionKey selectiontKey) throws IOException {
        try {
            if (selectiontKey.isValid() && selectiontKey.isReadable()) {

                SocketChannel socket = (SocketChannel) selectiontKey.channel();

                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                int readByte = socket.read(byteBuffer);// 返回独到的字节数
                System.out.println(readByte + "****");
                if (readByte > 0) {// 读取数据

                    byteBuffer.flip();

                    byte[] bytes = new byte[byteBuffer.remaining()];

                    byteBuffer.get(bytes);// 将缓冲区数据读到byte[]中

                    String result = new String(bytes, "UTF-8");
                    System.out.println("客户端接收到的数据：" + result);
                    byteBuffer.clear();
                } else if (readByte < 0) {
                    selectiontKey.cancel();
                    socketChannel.close();
                }
                selectiontKey.interestOps(SelectionKey.OP_READ);
            }
        } catch (Exception e) {
            System.out.println("服务端连接已关闭");
        }
    }

    static void sendm(String message) throws IOException {
        socketChannel.write(charset.encode(message));
    }

    public static void main(String[] args) throws IOException {

        NIOClient.Th th = new NIOClient().new Th();
        th.start();

        Scanner scan = new Scanner(System.in);// 这里向服务端发送数据，同时启动了一个键盘监听器
        while (scan.hasNextLine()) {
            System.out.println("输入数据:\n");
            // 读取键盘的输入
            String line = scan.nextLine();
            // 将键盘的内容输 到SocketChanenel中
            sendm(line);
        }
        scan.close();
    }

    private class Th extends Thread {

        @Override
        public void run() {
            try {
                while (selector.select() > 0) {
                    Set<SelectionKey> set = selector.selectedKeys();
                    Iterator<SelectionKey> iter = set.iterator();
                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();
                        try {
                            receiveRead(key);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        iter.remove();

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
