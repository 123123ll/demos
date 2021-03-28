package com.example.demo.NettyRPC.provider;

import com.example.demo.NettyRPC.publicInterface.HelloService;

/**
 * @ClassName: HelloServiceImpl
 * @Description: 公共接口实现类
 * @Author: liu
 * @Date: 2021/2/4 9:37
 */
public class HelloServiceImpl implements HelloService {
    /*
     *
     * @param msg
     * @return java.lang.String
     * @Description:当有消费方调用该方法时就返回一个结果
     * @author liu
     * @date 2021/2/4 9:39
     * @date 2021/2/4 9:39
     *
     */
    private int count = 0;
    @Override
    public String hello(String msg) {
        System.out.println("收到消费方消息="+msg);
        //根据msg返回不同的结果
        if (msg != null){
            return "你好客户端，我已收到你的消息 【"+msg+"】第"+(++count)+"次";
        }else {
            return "你好客户端，我已收到你的消息";
        }

    }
}
