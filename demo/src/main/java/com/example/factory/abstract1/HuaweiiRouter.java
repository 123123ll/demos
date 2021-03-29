package com.example.factory.abstract1;

/**
 * @ClassName: XiaomiRouter
 * @Description: 华为路由器
 * @Author: liu
 * @Date: 2021/3/24 19:59
 */
public class HuaweiiRouter implements IRoutProduct {
    @Override
    public void start() {
        System.out.println("启动华为路由器");
    }

    @Override
    public void shutdown() {
        System.out.println("关闭华为路由器");
    }

    @Override
    public void openWife() {
        System.out.println("打卡华为wifi");
    }

    @Override
    public void seting() {
        System.out.println("华为设置");
    }
}
