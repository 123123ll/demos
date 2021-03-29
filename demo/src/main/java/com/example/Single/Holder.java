package com.example.Single;

/**
 * @ClassName: Holder
 * @Description: 内部类
 * @Author: liu
 * @Date: 2021/3/24 17:42
 */
//静态内部类实现
public class Holder {
    private static final int a=0;
    private static final int b=0;
    private Holder(){

    }

    public static Holder getInstance(){
        return InnerClass.HOLDER;
    }
    public static class InnerClass{
        private static final Holder HOLDER = new Holder();
    }

    public static void main(String[] args) {
        System.out.println("1"+a);
    }
}
