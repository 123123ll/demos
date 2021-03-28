package com.example.lll;

/**
 * @ClassName: NIOServer
 * @Description: 服务端
 * @Author: liu
 * @Date: 2021/1/6 18:11
 */
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class NIOServer {

    private ServerSocketChannel serverSocketChannel;
    public String IP = "127.0.0.1";

    private Selector selector;

    static int port = 8989;

    public NIOServer() {
        try {
            selector = Selector.open();

            serverSocketChannel = ServerSocketChannel.open();

            serverSocketChannel.socket().bind(new InetSocketAddress(IP, port));

            serverSocketChannel.configureBlocking(false);

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("服务器已启动，端口号：" + port);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void receiceMessage() {
        while (true) {
            try {
                selector.select();
                Set<SelectionKey> selectors = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectors.iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    try {
                        heandle(key);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    iterator.remove();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    void heandle(SelectionKey selectionKey) throws IOException {

        if (selectionKey.isValid() && selectionKey.isAcceptable()) { // 有效的連接

            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
            // 通过ServerSocketChannel的accept创建SocketChannel实例
            // 完成该操作意味着完成TCP三次握手，TCP物理链路正式建立
            SocketChannel scoketCl = serverSocketChannel.accept();
            // 设置飞阻塞模式
            scoketCl.configureBlocking(false);

            scoketCl.register(selector, SelectionKey.OP_READ); // 注册读 事件

        } else if (selectionKey.isValid() && selectionKey.isReadable()) {// 是否可读
            SocketChannel sc = (SocketChannel) selectionKey.channel();

            String handelResult = null;
            // 创建缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            // 通道 read into byteBuffer
            int byteCounts = sc.read(byteBuffer);

            if (byteCounts > 0) {
                // 接受数据 处理数据 返回数据
                byteBuffer.flip(); // 转换为刻度 模式

                byte[] bytes = new byte[byteBuffer.remaining()];

                byteBuffer.get(bytes);

                String result = new String(bytes, "UTF-8");

                System.out.println("服务端接收的数据:" + result);
                // 处理
                handelResult = "处理后的数据是server" + result;

                System.out.println("处理后的数据是:" + handelResult);

                serverSendMessage(sc, handelResult);
            } else if (byteCounts < 0) {
                selectionKey.cancel();
                sc.close();
            }

        }

    }

    static void serverSendMessage(SocketChannel socketChannel, String message) throws IOException {
        System.out.println("message" + message);
        byte[] bytes = message.getBytes();

        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);

        byteBuffer.put(bytes);// 添加数据到缓冲区

        byteBuffer.flip();// 模式转换

        socketChannel.write(byteBuffer); // 写入数据到socket通道
    }

    public static void main(String[] args) {
        NIOServer server = new NIOServer();
        server.receiceMessage();
    }

}
