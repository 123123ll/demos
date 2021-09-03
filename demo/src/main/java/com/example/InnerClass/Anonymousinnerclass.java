package com.example.InnerClass;

/**
 * @ClassName: Anonymousinnerclass
 * @Description: 匿名内部类
 * @Author: liu
 * @Date: 2021/9/2 17:43
 */
abstract class classtest{
     abstract void eat();
}
interface interfacetest{
    void eat();
}

public class Anonymousinnerclass {
    public static void main(String[] args) {
        interfacetest interfacetest = new interfacetest() {
            @Override
            public void eat() {
                System.out.println("接口匿名内部类");
            }
        };
        classtest classtest = new classtest() {
            @Override
            void eat() {
                System.out.println("类匿名内部类");
            }
        };
        interfacetest.eat();
        classtest.eat();
    }
}
