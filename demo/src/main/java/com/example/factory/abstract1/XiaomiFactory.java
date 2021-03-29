package com.example.factory.abstract1;

/**
 * @ClassName: XiaomiFactory
 * @Description: 小米工厂
 * @Author: liu
 * @Date: 2021/3/24 20:05
 */
public class XiaomiFactory implements IProductFactory {
    @Override
    public IphoneProduct iphoneProduct() {
        return new XiaomiPhone();
    }

    @Override
    public IRoutProduct iRoutProduct() {
        return new XiaomiRouter();
    }
}
