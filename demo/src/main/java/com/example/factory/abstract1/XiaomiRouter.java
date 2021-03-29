package com.example.factory.abstract1;

/**
 * @ClassName: XiaomiRouter
 * @Description: 小米路由器
 * @Author: liu
 * @Date: 2021/3/24 19:59
 */
public class XiaomiRouter implements IRoutProduct {
    @Override
    public void start() {
        System.out.println("启动小米路由器");
    }

    @Override
    public void shutdown() {
        System.out.println("关闭小米路由器");
    }

    @Override
    public void openWife() {
        System.out.println("打卡小米wifi");
    }

    @Override
    public void seting() {
        System.out.println("小米设置");
    }
}
