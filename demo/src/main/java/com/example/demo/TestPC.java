package com.example.demo;

//测试:生产者消费者模型--->利用缓冲区解决：管程法

//生产者、消费者、产品、缓冲区
public class TestPC {

    public static void main(String[] args) {
        SynContainer container = new SynContainer();
        new Producer(container, "肯德基").start();
        new Consumer(container, "me").start();
//        new Consumer(container, "小李").start();
    }
}

//生产者
class Producer extends Thread {
    SynContainer container;
    String name;

    public Producer(SynContainer container, String name) {
        super(name);
        this.container = container;
    }

    //生产

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            container.push(new FirendChicken(i));
        }
    }
}

//消费者
class Consumer extends Thread {
    SynContainer container;
    String name;

    public Consumer(SynContainer container, String name) {
        super(name);
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            container.pop();
        }
    }
}

//产品  炸鸡
class FirendChicken {
    int id;//产品编号

    public FirendChicken(int id) {
        this.id = id;
    }
}


//缓冲区
class SynContainer {

    //容器大小
    FirendChicken[] chickens = new FirendChicken[10];

    //容器计数器  局部变量不受线程安全影响（数据一致性）
    int count = 0;

    //生产者放入产品
    public synchronized void push(FirendChicken chicken) {
        //如果容器满了，就需要等待消费
        if (count == chickens.length) {
            //通知消费者消费，生产等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //如果没有满，生产产品加入缓冲区
        chickens[count] = chicken;
        System.out.println(Thread.currentThread().getName() + "生产了" + count + "炸鸡");
        count++;
        //可以通知消费者消费了。
        this.notifyAll();
    }

    //消费者消费产品
    public synchronized FirendChicken pop() {
        //判断能否消费 使用while而不使用if判断是防止 if语句中醒来的线程 不会再一次进行判断了
        if (count == 0) {
            //等待生产者生产，消费者等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //如果可以消费
        count--;
        FirendChicken chicken = chickens[count];
        System.out.println(Thread.currentThread().getName() + "消费了-->" + chicken.id + "炸鸡");
        //通知生产者 生产
        this.notifyAll();
        return chicken;

    }

}