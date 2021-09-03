package com.example.Observermode;

/**
 * @ClassName: observerimpl
 * @Description: 观察者实现
 * @Author: liu
 * @Date: 2021/9/3 10:16
 */
public class observerimpl implements observer {
    private String name;

    public observerimpl(String name){
        this.name=name;
    }

    @Override
    public void update(String msg){
        System.out.println(name+"收到通知:"+msg);
    }

    private class test{
        void test(){
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

    public static void main(String[] args) {
        observerimpl observerimpl = new observerimpl("tset");
        test test = observerimpl.new test();
        test.test();
    }
}
