package com.example.demo.NettyRPC.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: NettyClient
 * @Description: 消费端
 * @Author: liu
 * @Date: 2021/2/6 14:50
 */
public class NettyClient {

    //创建线程池
    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static NettyClientHandler clientHandler;

    //编写方法使用代理模式，获取一个代理对象
    public Object getBean(final Class<?> serviveClass,final String providrName){
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),new Class<?>[]{serviveClass},
                (proxy, method, args) -> {
            //{} 部分的代码，客户端每调用一次hello，就会进入到该代码
                    if (clientHandler == null){
                        initClient();
                    }
                    //设置要发送给服务端的信息
                    //providerName 协议头  arg[0] 就是客户端调用api hello（？？？），参数
                    clientHandler.setParam(providrName+args[0]);
                    return executorService.submit(clientHandler).get();
                });
    }

    //初始化客户端
    private static void initClient(){
        clientHandler  = new NettyClientHandler();
        //创建EventLoopGroup
        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(
                        new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel socketChannel) throws Exception {
                                ChannelPipeline pipeline = socketChannel.pipeline();
                                pipeline.addLast(new StringDecoder());
                                pipeline.addLast(new StringEncoder());
                                pipeline.addLast(clientHandler);
                            }
                        }
                );
        try {
            bootstrap.connect("127.0.0.1", 7000).sync();
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
