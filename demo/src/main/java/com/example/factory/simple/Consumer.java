package com.example.factory.simple;

/**
 * @ClassName: Consumer
 * @Description: 客户
 * @Author: liu
 * @Date: 2021/3/24 18:51
 */
public class Consumer {
    public static void main(String[] args) {
        //接口，所有实现类！工厂
//        Car car = new WuLing();
//        Car car2 = new Tesla();

        //2.使用工厂创建
        Car car = CarFactory.getCar("五菱");
        Car car2 = CarFactory.getCar("特斯拉");
        car.name();
        car2.name();
    }
}
