package com.example.demo;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName: NioClient
 * @Description: NIO客户端
 * @Author: liu
 * @Date: 2021/1/6 11:32
 */

public class NioClient {
    private static int flag = 1;
    private static  int blockSize = 4096;
    private static ByteBuffer sendbuffer = ByteBuffer.allocate(blockSize);
    private static  ByteBuffer receivebuffer = ByteBuffer.allocate(blockSize);
//    private final  static InetSocketAddress serverAdress = new InetSocketAddress("127.0.0.1",8555);
    static Selector selector;
    static SocketChannel socketChannel;
    public static void main(String[] args) throws IOException {

        NioClient nioClient = new NioClient("127.0.0.1",9000);
        nioClient.start();
    }

    public NioClient(String ip,int port) throws IOException {
//        socketChannel = SocketChannel.open();
//        socketChannel.configureBlocking(false);
//        selector = Selector.open();
//        //客户端监听连接事件
//        socketChannel.register(selector, SelectionKey.OP_CONNECT);
//        socketChannel.connect(new InetSocketAddress(ip, port));
        //打开选择器
        selector = Selector.open();
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress(ip,port));
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
    }
    public void start() throws IOException{
        Set<SelectionKey> selectionKeys;
        Iterator<SelectionKey> iterator;
        SelectionKey selectionKey;
        SocketChannel client;
        String receiveText;
        String sendText;
        int count = 0;
        while (true){
            selector.select(1000);
            selectionKeys = selector.selectedKeys();
            iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                selectionKey = iterator.next();
                iterator.remove();
                client = (SocketChannel) selectionKey.channel();
                if (selectionKey.isConnectable()){
                    System.out.println("客户端开始连接");
                    if (socketChannel.isConnectionPending()){
                        socketChannel.finishConnect();
                        System.out.println("客户端完成连接");
                        sendbuffer.clear();
                        sendbuffer.put("Hello , 服务端！".getBytes());
                        sendbuffer.flip();
                        client.write(sendbuffer);
                    }
                    client.register(selector,SelectionKey.OP_READ);
                }
                if (selectionKey.isReadable()){
                    client = (SocketChannel) selectionKey.channel();
                    receivebuffer.clear();
                    count = client.read(receivebuffer);
                    if (count>0){
                        receiveText = new String(receivebuffer.array(),0,count);
                        System.out.println("客户端接收到服务端数据："+receiveText);
                        client.register(selector,SelectionKey.OP_WRITE);
                    }
                }
                if (selectionKey.isWritable()){
                    sendbuffer.clear();
                    client = (SocketChannel) selectionKey.channel();
                    sendText = "Msg to Server！"+flag++;
                    sendbuffer.put(sendText.getBytes());
                    sendbuffer.flip();
                    client.write(sendbuffer);
                    System.out.println("客户端发送数据给服务端："+sendText);
                    client.register(selector,SelectionKey.OP_READ);
                }
                iterator.remove();
            }
//            selectionKeys.clear();
        }
    }
}
