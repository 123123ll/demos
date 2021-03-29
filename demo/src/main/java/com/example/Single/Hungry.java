package com.example.Single;

/**
 * @ClassName: Hungry
 * @Description: 饿汉模式
 * @Author: liu
 * @Date: 2021/3/24 16:58
 */
//饿汉式单例
public class Hungry {

    private Hungry(){
    }

    private final static Hungry HUNGRY = new Hungry();
    public static Hungry getInstance(){
        return HUNGRY;
    }

}
