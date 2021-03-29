package com.example.factory.simple;

/**
 * @ClassName: CarFactory
 * @Description:
 * @Author: liu
 * @Date: 2021/3/24 19:09
 */
//静态工厂模式
//增加一个新的产品，如果不修改代码，做不到！
//没有满足开闭原则
public class CarFactory {
    //方法一
    public static Car getCar(String car){
        if (car.equals("五菱")){
            return new WuLing();
        }else if (car.equals("特斯拉")){
            return new Tesla();
        }else {
            return null;
        }
    }

    //方法二
    public static Car getWuLing(){
        return new WuLing();
    }
}
