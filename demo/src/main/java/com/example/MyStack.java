package com.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @ClassName: funny
 * @Description: funny
 * @Author: liu
 * @Date: 2021/3/19 16:37
 */
public class MyStack {
   LinkedList<Integer> queue1 = new LinkedList<>();
   LinkedList<Integer> queue2 = new LinkedList<>();

   public MyStack(){

   }

   public void push(int x){
       if (queue1.isEmpty()){
           queue2.offer(x);
       }else {
           queue1.offer(x);
       }
   }

   public int pop(){
       if (queue1.isEmpty()){
           while (queue2.size()>1){
               queue1.offer(queue2.poll());
           }
           return queue2.poll();
       }else {
           while (queue1.size()>1){
               queue2.offer(queue1.poll());
           }
           return queue1.poll();
       }
   }

   public int top(){
       int top = 0 ;
       if (queue1.isEmpty()){
           while (queue2.size()>1){
               queue1.offer(queue2.poll());
           }
           top = queue2.poll();
           queue1.offer(top);
       }else {
           while (queue1.size()>1){
               queue2.offer(queue1.poll());
           }
           top = queue1.poll();
           queue2.offer(top);
       }
       return top;
   }

   public boolean empty(){
       return queue1.isEmpty()&&queue2.isEmpty();
   }

    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(10);
        System.out.println(myStack.top());
        System.out.println(myStack.empty());
        myStack.pop();
        System.out.println(myStack.empty());
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            System.out.println(scanner.nextLine());
            Queue<String> objects = new LinkedBlockingDeque<String>();
            objects.offer("队列");
//            System.out.println(objects.poll());
            System.out.println(objects.peek());
        }

    }

}
