package com.example.demo.NettyRPC.publicInterface;

/**
 * @ClassName: HelloService
 * @Description: rpc公共接口，服务方和消费方都需要
 * @Author: liu
 * @Date: 2021/2/4 9:34
 */
public interface HelloService {
    public String hello(String msg);
}
