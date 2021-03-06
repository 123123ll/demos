package com.example.factory.abstract1;

/**
 * @ClassName: HuaweiPhone
 * @Description: 华为手机
 * @Author: liu
 * @Date: 2021/3/24 19:57
 */
public class HuaweiPhone implements IphoneProduct {
    @Override
    public void start() {
        System.out.println("开启华为手机");
    }

    @Override
    public void shundown() {
        System.out.println("关闭华为手机");
    }

    @Override
    public void callup() {
        System.out.println("华为打电话");
    }

    @Override
    public void sendSMS() {
        System.out.println("华为发短信");
    }
}
