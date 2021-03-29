package com.example.Single;

import java.lang.reflect.Constructor;

/**
 * @ClassName: LazyMan
 * @Description: 懒汉式单例
 * @Author: liu
 * @Date: 2021/3/24 17:03
 */
public class LazyMan {
    private LazyMan(){
        synchronized (LazyMan.class){
            if (lazyMan!=null){
                throw new RuntimeException("不要试图使用反射破坏异常");
            }
        }
        System.out.println(Thread.currentThread().getName()+"OK");
    }

    //volatile防止指令重排
    private volatile static LazyMan lazyMan;

    public static LazyMan getInstance(){
        //加锁,双重检测锁模式的懒汉式单例 DCL懒汉式
        if (lazyMan==null){
            synchronized (LazyMan.class){
                if (lazyMan==null){
                    lazyMan = new LazyMan();//不是原子性操作
                    /*
                    1.分配内存空间
                    2.执行构造方法，初始化对象
                    3.把这个对象指向这个空间
                    可能出现问题。指令重排，123，也可能132，线程A走132，此时进入线程B判断非空，但是还未完成构造，产生问题，需要加入volatile防止指令重排
                     */
                }
            }
        }
        return lazyMan;
    }

    //多线程并发
//    public static void main(String[] args) {
//        for (int i = 0; i <10 ; i++) {
//            new Thread(()->{
//                LazyMan.getInstance();
//            }).start();
//        }
//    }

    //反射！
    public static void main(String[] args) throws Exception{
        LazyMan instance = LazyMan.getInstance();
        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);//无视了私有的构造器,破坏单例(反射不能破坏枚举的单例)
        LazyMan instance2 = declaredConstructor.newInstance();
        System.out.println(instance);
        System.out.println(instance2);

    }
}
