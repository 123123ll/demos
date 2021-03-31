package com.example.demo;

/**
 * @ClassName: Sbliulin
 * @Description: sad
 * @Author: liu
 * @Date: 2020/12/21 18:27
 */
public class sbWangShuLiang {
    public static void main(String[] args) {
        int i=1;
        int add = add(i);
        System.out.println(add);
        System.out.println(sum(100));

    }
    public static int add(int i){
        if (i>3){
            return 2;
        }else {
            return i+add(i+1);
        }

    };

    public static int sum(int i){
        if (i==1){
            return 1;
        }else {
            return sum(i-1)+i;
        }
    }

}

