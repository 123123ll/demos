package com.example.factory.abstract1;

/**
 * @ClassName: IProductFactory
 * @Description: 抽象产品工厂
 * @Author: liu
 * @Date: 2021/3/24 20:02
 */
public interface IProductFactory {
    //生产手机
    IphoneProduct iphoneProduct();
    //生产路由器
    IRoutProduct iRoutProduct();
}
