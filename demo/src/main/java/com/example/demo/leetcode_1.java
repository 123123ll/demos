package com.example.demo;

import java.util.HashMap;

/**
 * @ClassName: leetcode_1
 * @Description: 力扣1
 * @Author: liu
 * @Date: 2020/12/16 17:01
 */
public class leetcode_1 {
    public static  boolean wordPattern(String pattern, String string) {
        HashMap<String,String> h1 = new HashMap<>();
        HashMap<String,String> h2 =new HashMap<>();
        String[] strings = string.split("");
        if (strings.length == pattern.length()){
            for (int i = 0; i < pattern.length(); i++) {
                char c = pattern.charAt(i);
                if (!h1.containsKey(c)){
                    if (!h1.containsValue(strings[i])){
                        h1.put("c",strings[i]);
                    }else {
                        return false;
                    }
                }else if (h1.get(c).equals(strings[i])){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(wordPattern("1231","abcc"));
    }
}
