package com.example.demo.NettyRPC.provider;

import com.example.demo.NettyRPC.netty.NettyServer;

/**
 * @ClassName: ServerBootstrap
 * @Description: 服务提供方启动类
 * @Author: liu
 * @Date: 2021/2/4 9:43
 */
public class ServerBootstrap {
    public static void main(String[] args) {
        NettyServer.startServer("127.0.0.1",7000);
    }
}
