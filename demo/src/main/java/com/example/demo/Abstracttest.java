package com.example.demo;

/**
 * @ClassName: Abstracttest
 * @Description: 抽象类
 * @Author: liu
 * @Date: 2021/3/19 10:16
 */
public abstract class Abstracttest {
    public abstract void get();

    public void set() {
        System.out.println(1);
    }

    static class ab extends Abstracttest {

        @Override
        public void get() {
            set();
        }
    }
    static class abb{
        void acc(Abstracttest abstracttest) {
            abstracttest.get();
        }
    }
    public static void main(String[] args) {
        ab ab = new ab();
        ab.get();
        abb abb = new abb();
        abb.acc(new Abstracttest() {
            @Override
            public void get() {
                System.out.println(222);
            }
        });
    }
}
