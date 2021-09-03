package com.example.demo;

/**
 * @ClassName: SingleLinkedListDemo
 * @Description: 单链表
 * @Author: liu
 * @Date: 2021/6/3 17:39
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        // 进行测试
        // 先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        // 创建一个链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        /*
         * // 加入 singleLinkedList.add(hero1); singleLinkedList.add(hero4);
         * singleLinkedList.add(hero2); singleLinkedList.add(hero3);
         */

        // 加入按照编号的排序
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);

        // // 测试修改节点的代码
        // HeroNode newHeroNode = new HeroNode(2, "小卢", "煜麒麟");
        // singleLinkedList.update(newHeroNode);

        // 显示一把
        singleLinkedList.list();

        // 测试删除节点的代码
        singleLinkedList.delete(hero2.no);
//        singleLinkedList.delete(hero1.no);
//        singleLinkedList.delete(hero3.no);
//        singleLinkedList.delete(hero4.no);

        // System.out.println("修改后的链表情况：");
        System.out.println("删除后的链表情况：");
        singleLinkedList.list();

    }
}

/****************************************************************************************************
 * ******************* 定义SingleLinkedList管理我们的英雄*********************************************
 * *************************************************************************************************/
class SingleLinkedList {
    // 先初始化一个头节点，头节点不要动。不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    /********************* 第一种添加节点到单向链表 ***********************************************/
    /*
     * 思路:当不考虑编号顺序时 1、找到当前链表的最后节点 2、将最后这个节点的next 指向新的节点
     */
    public void add(HeroNode heroNode) {

        // 因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode temp = head;
        // 遍历链表，找到最后
        while (true) {
            // 找到链表的最后
            if (temp.next == null) {
                break;
            }
            // 如果没有找到最后，就将temp后移
            temp = temp.next;
        }
        // 当退出while 循环时，temp就指向了链表的最后
        // 将最后这个节点的next 指向 新的节点
        temp.next = heroNode;
    }

    /********** 第二种方式在添加英雄时，根据排名将英雄插入到指定位置 ***********************************/

    // 好处：在内存中就排好序，比在数据库中排序更优。

    // （如果有这个排名，则添加失败，并给出提示）
    public void addByOrder(HeroNode heroNode) {
        // 因为头节点不能动，因此我们仍然通过一个辅助指针（变量）来帮助找到添加的位置。
        // 因为是单链表，因此我们找的temp 是位于 添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false;// flag标志添加的编号是否存在，默认为false
        while (true) {
            if (temp.next == null) {// 说明temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) {// 位置找到，就在temp 的后面插入
                break;
            } else if (temp.next.no == heroNode.no) {// 说明希望添加的heroNode的编号已然存在
                flag = true;// 说明编号存在
                break;
            }
            temp = temp.next;// 后移，遍历当前链表
        }
        // 判断flag 的值
        if (flag) {// 不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号 %d 已经存在了，不能加入\n", heroNode.no);
        } else {// 插入到链表中，temp 的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /****************** 修改节点的信息，根据据no编号来修改，即no编号不能改 *************************/
    // 说明：1.根据newHeroNode 的 no 来修改即可
    public void update(HeroNode newHeroNode) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        // 找到需要修改的节点，根据no 编号
        // 定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;// 表示是否找到该节点
        while (true) {// 遍历完了退出，或者找到了退出
            if (temp == null) {// 最后一个节点为null
                break;// 已经遍历完链表
            }
            if (temp.no == newHeroNode.no) {
                // 找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 根据flag 判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {// 没有找到
            System.out.printf("没有找到编号为%d 的节点，不能修改\n", newHeroNode.no);
        }
    }

    /************************ 删除节点******************************** *************************/
    // 原理：被删除的节点，没有任何引用指向它，在Java中就会成为一个垃圾，就会被垃圾回收机制回收
    // 思路：1.head不能动，因此我们需要一个temp辅助节点找到待删除节点的前一个节点
    // 2.说明我们在比较时，是temp.next.no 和 需要删除的节点的no 比较
    public void delete(int delno) {
        if (head.next == null) {
            System.out.println("链表为空~");
        }
        HeroNode temp = head;

        boolean flag = false;// 标志是否找到待删除节点
        while (true) {// 两种情况退出，遍历完了还没找到、找到了。
            if (temp.next == null) {
                System.out.println("没找到~");
                break;
            }
            if (temp.next.no == delno) {
                // 找到了待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;// temp后移，遍历
        }
        if (flag) {// 找到
            temp.next = temp.next.next;

        } else {
            System.out.printf("没有找到编号为%d 的节点，删除失败~", delno);
        }

    }

    /************************ 显示链表（遍历） **************************************************/
    public void list() {
        // 先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        // 因为头节点不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            // 判断是否到链表最后
            if (temp == null) {
                break;
            }
            // 输出节点的信息
            System.out.println(temp);
            // 将temp后移,一定记住
            temp = temp.next;
        }
    }
}

/****************************************************************************************************
 ******************** 定义一个HeroNode,每个HeroNode对象就是一个节点*********************************
 **************************************************************************************************/

class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;// 指向下一个节点

    // 构造器
    public HeroNode(int No, String name, String Nickname) {
        this.no = No;
        this.name = name;
        this.nickname = Nickname;
    }

    // 为了显示方便，我们重写toString(右键Source ——> toString)
    @Override
    public String toString() {
        return "HeroNode [no=" + no + ", name=" + name + ", nickname="
                + nickname + "]";
    }
}
