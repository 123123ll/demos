package com.example;

/**
 * @ClassName: thread
 * @Description: 线程
 * @Author: liu
 * @Date: 2021/3/19 14:59
 */
public class thread implements Runnable {
    private static Integer ticket = 10;

    synchronized void buy() throws InterruptedException {
        if (ticket > 0) {
            Thread.sleep(300);
            --ticket;
            System.out.println(Thread.currentThread().getName() + "号窗口售票，票余" + ticket + "票");

        } else {
            flag=false;
            System.out.println("万清伟没钱了");

        }

    }
boolean flag=true;
    @Override
    public void run() {
        while (flag) {
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        thread thread = new thread();
        Thread thread1 = new Thread(thread,"1");
        Thread thread2 = new Thread(thread,"2");
        thread1.start();
        thread2.start();
    }
}
