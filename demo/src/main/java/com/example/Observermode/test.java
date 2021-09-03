package com.example.Observermode;

/**
 * @ClassName: test
 * @Description: 测试观察者模式
 * @Author: liu
 * @Date: 2021/9/3 10:30
 */
public class test {
    public static void main(String[] args) {
        beobserverimpl beobserver = new beobserverimpl();
        observerimpl obs1 = new observerimpl("小王");
        observerimpl obs2 = new observerimpl("小张");
        observerimpl obs3 = new observerimpl("小李");
        beobserver.add(obs1);
        beobserver.add(obs2);
        beobserver.add(obs3);
        beobserver.notice("老总来了！");
    }
}
