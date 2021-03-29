package com.example.factory.abstract1;

/**
 * @ClassName: Client
 * @Description: 客户
 * @Author: liu
 * @Date: 2021/3/24 20:09
 */
public class Client {
    public static void main(String[] args) {
        System.out.println("=====小米系列产品=======");
        //小米工厂
        XiaomiFactory xiaomiFactory = new XiaomiFactory();
        IphoneProduct iphoneProduct = xiaomiFactory.iphoneProduct();
        iphoneProduct.callup();
        iphoneProduct.sendSMS();


        IRoutProduct iRoutProduct = xiaomiFactory.iRoutProduct();
        iRoutProduct.openWife();


        System.out.println("=====华为系列产品======");
        HuaweiFactory huaweiFactory = new HuaweiFactory();
        IphoneProduct iphoneProduct1 = huaweiFactory.iphoneProduct();
        iphoneProduct1.sendSMS();
        IRoutProduct iRoutProduct1 = huaweiFactory.iRoutProduct();
        iRoutProduct1.openWife();
    }
}
