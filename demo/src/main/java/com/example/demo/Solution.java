package com.example.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Solution
 * @Description: two sum
 * @Author: liu
 * @Date: 2021/3/16 9:21
 */
public class Solution {
    public static void main(String[] args) {

        int[] nums = {1, 3, 5, 7, 10, 13, 15, 20};
        int target = 20;

        long a= System.nanoTime();//获取当前系统时间(纳秒)
        Solution.twoSum1(nums, target);
        System.out.print("程序执行时间为：");
        System.out.println(System.nanoTime()-a+"纳秒");

        long b= System.nanoTime();//获取当前系统时间(纳秒)
        Solution.twoSum2(nums, target);
        System.out.print("程序执行时间为：");
        System.out.println(System.nanoTime()-b+"纳秒");

        long c= System.nanoTime();//获取当前系统时间(纳秒)
        Solution.twoSum3(nums, target);
        System.out.print("程序执行时间为：");
        System.out.println(System.nanoTime()-c+"纳秒");

    }

    public static void twoSum1(int[] nums, int target) {
        int length = nums.length;
        for (int i = 0; i < length; ++i)
            for (int j = i + 1; j < length; ++j)
                if (nums[i] + nums[j] == target)
                    System.out.println("i:"+i+",j:"+j);
    }

    public static void twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {//这里假如是排序的数组
            int complement = target - nums[i];
            //这里可能来回找了两遍，怎么优化呢？？
            if (map.containsKey(complement) && map.get(complement) != i) {
                System.out.println("i:"+i+",j:"+map.get(complement));
            }
        }
    }

    public static void twoSum3(int[] nums, int target) {
        HashMap<Integer, Integer> m = new HashMap<Integer, Integer>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; ++i) {
            if (m.containsKey(target - nums[i])) {
                res[0] = i;
                res[1] = m.get(target - nums[i]);
                System.out.println("i:"+res[0]+",j:"+res[1]);
            }else{
                m.put(nums[i], i);
            }
        }
    }
}
