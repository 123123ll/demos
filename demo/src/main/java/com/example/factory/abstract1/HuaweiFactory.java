package com.example.factory.abstract1;

/**
 * @ClassName: HuaweiFactory
 * @Description: 华为工厂
 * @Author: liu
 * @Date: 2021/3/24 20:07
 */
public class HuaweiFactory implements IProductFactory {
    @Override
    public IphoneProduct iphoneProduct() {
        return new HuaweiPhone();
    }

    @Override
    public IRoutProduct iRoutProduct() {
        return new HuaweiiRouter();
    }
}
