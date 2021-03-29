package com.example.factory.method;

/**
 * @ClassName: TeslaFactory
 * @Description: 特斯拉工厂
 * @Author: liu
 * @Date: 2021/3/24 19:29
 */
public class TeslaFactory implements CarFactory {

    @Override
    public Car getCar() {
        return new Tesla();
    }
}
