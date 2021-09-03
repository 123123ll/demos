package com.example.InnerClass;

/**
 * @ClassName: Staticinnerclass
 * @Description: 静态内部类
 * @Author: liu
 * @Date: 2021/9/2 16:58
 */
public class Staticinnerclass {
    int a = 1;
    static int b = 2;
    private static class inner{
        int c =3;
        private void test(){
            System.out.println("访问外部类的b:"+b);
            System.out.println("访问外部类的a:"+new Staticinnerclass().a);
            System.out.println("访问内部类的c:"+c);
        }
    }

    public static void main(String[] args) {
        inner inner = new inner();
        inner.test();
    }
}
