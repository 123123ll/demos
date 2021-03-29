package com.example.Proxy;

/**
 * @ClassName: Client
 * @Description: 使用
 * @Author: liu
 * @Date: 2021/3/24 15:28
 */
public class Client {
    public static void main(String[] args) {
        //真实角色
        Host host = new Host();
        //代理角色，现在没有
        MyProxyInvactionHandler myProxyInvactionHandler = new MyProxyInvactionHandler();
        //通过调用程序处理角色来处理我们要调用的接口
        myProxyInvactionHandler.setRent(host);
        Rent proxy = (Rent) myProxyInvactionHandler.getProxy();//这里的Proxy就是动态生成的，我们并没有写！
        proxy.rent();
    }

}
