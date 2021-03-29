package com.example.Proxy;

/**
 * @ClassName: Host
 * @Description: 房东
 * @Author: liu
 * @Date: 2021/3/23 20:17
 */
public class Host implements Rent {
    @Override
    public void rent() {
        System.out.println("房东要出租房子！");
    }
}
