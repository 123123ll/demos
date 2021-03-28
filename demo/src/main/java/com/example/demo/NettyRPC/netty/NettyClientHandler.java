package com.example.demo.NettyRPC.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * @ClassName: NettyClientHandler
 * @Description: 客户端handler
 * @Author: liu
 * @Date: 2021/2/4 10:59
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {
    private  ChannelHandlerContext channelHandlerContext;//上下文
    private  String result;//返回的结果
    private  String param;//客户端调用方法时传入的参数

    //(1)
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        channelHandlerContext = ctx;//因为在其他方法会使用到ctx
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();

    }

    //收到服务器数据后调用方法
    //(4)
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            result = msg.toString();
            notify();//唤醒等待的线程

    }

    //被代理对象调用，发送数据给服务器，->wait->等待被唤醒->返回结果
    //(3)->等待->(5)
    @Override
    public synchronized Object call() throws Exception {
        channelHandlerContext.writeAndFlush(param);
        //进行wait
        wait();//等待channelread方法获取到服务器的结果后，唤醒
        return result;//服务器方返回的结果
    }

    //(2)
    void setParam(String param){
        this.param = param;
    }
}
