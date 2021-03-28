package com.example.demo;

/**
 * @ClassName: Recursion
 * @Description: 递归
 * @Author: liu
 * @Date: 2020/12/21 15:14
 */
public class Recursion {

    public static void PrintN(int N){
        if (N!=0){
            PrintN(N-1);
            System.out.println("========"+N);
        }
        return;
    }

    public static void main(String[] args) {
        PrintN(2);
    }
}
