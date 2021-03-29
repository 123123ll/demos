package com.example.Proxy.demo;

import com.example.Proxy.Host;
import com.example.Proxy.Rent;

/**
 * @ClassName: Client
 * @Description: 调用
 * @Author: liu
 * @Date: 2021/3/24 15:52
 */
public class Client {
    public static void main(String[] args) {
        Host host = new Host();
        MyProxyInvactionHandler myProxyInvactionHandler = new MyProxyInvactionHandler();
        myProxyInvactionHandler.setTarget(host);
        Rent proxy = (Rent) myProxyInvactionHandler.getProxy();
        proxy.rent();
    }
}
