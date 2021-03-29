package com.example.factory.simple;

/**
 * @ClassName: Tesla
 * @Description: 特斯拉
 * @Author: liu
 * @Date: 2021/3/24 18:50
 */
public class Tesla implements Car {
    @Override
    public void name() {
        System.out.println("特斯拉");
    }
}
