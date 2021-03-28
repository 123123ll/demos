package com.example.demo;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName: NioServer
 * @Description: NIO服务端
 * @Author: liu
 * @Date: 2021/1/6 10:51
 */

public class NioServer {
    private int flag = 1;
    private int blockSize = 4096;
    private ByteBuffer sendbuffer = ByteBuffer.allocate(blockSize);
    private ByteBuffer receivebuffer = ByteBuffer.allocate(blockSize);
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    public NioServer(int port) throws IOException {
        serverSocketChannel =   ServerSocketChannel.open();
        //打开选择器
        selector = Selector.open();
        ServerSocket socket = serverSocketChannel.socket();
        //绑定端口
        socket.bind(new InetSocketAddress(port));
        //设置是否阻塞
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务端启动："+port);
    }

    public void listen() throws IOException{
        while (true){
            while(selector.select()>0){
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> itetor = selectionKeys.iterator();
                while (itetor.hasNext()){
                    SelectionKey selectionKey = itetor.next();
                    itetor.remove();
                    //业务逻辑
                    handle(selectionKey);
                }
            }
        }
    }

    public void handle(SelectionKey selectionKey) throws IOException{
        ServerSocketChannel server = null;
        SocketChannel client = null;
        String reciveText;
        String sendText;
        int count = 0;
        if (selectionKey.isAcceptable()){
            server = (ServerSocketChannel) selectionKey.channel();
            client = server.accept();
            client.configureBlocking(false);
            client.register(selector,SelectionKey.OP_READ);
    }else if (selectionKey.isReadable()){
            client = (SocketChannel) selectionKey.channel();
            count = client.read(receivebuffer);
//            if (count>0){
                reciveText=new String(receivebuffer.array(),0,count);
                System.out.println("服务端接收到客户端信息："+reciveText);
                client.register(selector,SelectionKey.OP_WRITE);
//            }
        }else if (selectionKey.isWritable()){
            sendbuffer.clear();
            client   = (SocketChannel) selectionKey.channel();
            //发送的数据
            sendText = "msg send to client!"+flag++;
            sendbuffer.put(sendText.getBytes());
            sendbuffer.flip();
            client.write(sendbuffer);
            System.out.println("服务端发送数据给客户端："+sendText);
        }
        }

    public static void main(String[] args) throws IOException{
        int port = 9000;
        NioServer nioServer = new NioServer(port);
        nioServer.listen();

    }
}
