package com.example.Observermode;

/**
 * @ClassName: beobserver
 * @Description: 被观察者接口
 * @Author: liu
 * @Date: 2021/9/3 10:15
 */
public interface beobserver {
    void add(observer obs);
    void del(observer obs);
    void notice(String msg);
}
