package com.example.demo.NettyRPC.customer;

import com.example.demo.NettyRPC.netty.NettyClient;
import com.example.demo.NettyRPC.publicInterface.HelloService;

/**
 * @classname: ClientBootstarp
 * @description:消费者
 * @author: liulin
 * @time: 2021/2/8 18:42
 */
public class ClientBootstrap {
    //定义协议头
    public static final String providerName = "HelloService#hello#";

    public static void main(String[] args) throws Exception{
        //创建一个消费者
        NettyClient  customer = new NettyClient();
        //创建代理对象
        HelloService service = (HelloService) customer.getBean(HelloService.class,providerName);
        for (int i = 0; i < 10; i++) {//每次都会new一个代理对象
            Thread.sleep(10*1000);
        //通过代理对象调用服务提供者的方法（服务）
        String res = service.hello("您好 dubbo~");
        System.out.println("调用的结果 res="+res);
        }
    }
}
