package com.example.InnerClass;

/**
 * @ClassName: Memberinnerclass
 * @Description: 成员内部类
 * @Author: liu
 * @Date: 2021/9/2 15:49
 */
public class Memberinnerclass {
    private int a = 1;
    private class innerclass{
        private int b=2;
        private void test(){
            System.out.println("访问外部类的a:"+a);
            System.out.println("访问内部类的b:"+b);
            System.out.println("访问外部类的a:"+Memberinnerclass.this.a);
        }
    }

    public static void main(String[] args) {
        Memberinnerclass memberinnerclass = new Memberinnerclass();
        innerclass innerclass = memberinnerclass.new innerclass();
        innerclass.test();
    }
}
