package com.example.factory.abstract1;

/**
 * @ClassName: XiaomiPhone
 * @Description: 小米手机
 * @Author: liu
 * @Date: 2021/3/24 19:56
 */
public class XiaomiPhone implements IphoneProduct {
    @Override
    public void start() {
        System.out.println("开启小米手机");
    }

    @Override
    public void shundown() {
        System.out.println("关闭小米手机");
    }

    @Override
    public void callup() {
        System.out.println("小米打电话");
    }

    @Override
    public void sendSMS() {
        System.out.println("小米发短信");
    }
}
