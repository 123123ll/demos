package com.example.factory.method;

/**
 * @ClassName: WuLingFactory
 * @Description: 五菱工厂
 * @Author: liu
 * @Date: 2021/3/24 19:27
 */
public class WuLingFactory implements CarFactory {
    @Override
    public Car getCar() {
        return new WuLing();
    }
}
