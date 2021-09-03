package com.example.Observermode;

import java.util.ArrayList;

/**
 * @ClassName: beobserverimpl
 * @Description: 被观察者实现
 * @Author: liu
 * @Date: 2021/9/3 10:23
 */
public class beobserverimpl implements beobserver{
    private ArrayList<observer> observers = new ArrayList<>();

    @Override
    public void add(observer obs) {
        observers.add(obs);
    }

    @Override
    public void del(observer obs) {
        observers.remove(obs);
    }

    @Override
    public void notice(String msg) {
        observers.stream().forEach(it->{
            it.update(msg);
        });
    }
}
