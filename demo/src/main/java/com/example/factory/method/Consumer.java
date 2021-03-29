package com.example.factory.method;

/**
 * @ClassName: Consumer
 * @Description: 客户
 * @Author: liu
 * @Date: 2021/3/24 18:51
 */
public class Consumer {
    public static void main(String[] args) {
        Car car = new WuLingFactory().getCar();
        Car car2 = new TeslaFactory().getCar();
        car.name();
        car2.name();
        //结构复杂度： simple
        //代码复杂度： simple
        //编程复杂度： simple
        //管理上的复杂度： simple

        //根据设计原则： 工厂方法模式！
        //根据实际业务： 简单工厂模式！
    }
}
