package com.example.collection;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: LinkedHashMap
 * @Description: 有序hashmap
 * @Author: liu
 * @Date: 2021/8/31 12:21
 */
public class LinkedHashMap {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
        HashMap<Object, Object> hashMap = new HashMap<>();
//        while (true){
//            String next = scanner.next();
//            hashMap.put(next,"www"+next);
        hashMap.put(1,11);
        hashMap.put(2,22);
        hashMap.put(4,44);
        hashMap.put(3,33);
        hashMap.put(7,77);
        hashMap.put(6,66);
            System.out.println(hashMap.toString());
            List<Object> collect = hashMap.keySet().stream().collect(Collectors.toList());
            List<Object> collect1 = collect.stream().sorted(Comparator.comparing(Object::toString).reversed()).collect(Collectors.toList());
            System.out.println(collect1.toString());
//        }
    }
}
