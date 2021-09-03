package com.example.InnerClass;

/**
 * @ClassName: Methodinnerclass
 * @Description: 方法内部类
 * @Author: liu
 * @Date: 2021/9/2 17:13
 */
public class Methodinnerclass {


    public void show(){
        final int a = 1;
        int b = 2;
        class Minner{
            int c = 2;
            private void test(){
                System.out.println("访问外部类方法的常量a:"+a);
                System.out.println("访问外部类方法的常量b:"+b);
                System.out.println("访问内部类方法的变量c:"+c);
            }
        }
        Minner minner = new Minner();
        minner.test();
    }

    public static void main(String[] args) {
        Methodinnerclass methodinnerclass = new Methodinnerclass();
        methodinnerclass.show();
    }
}
