package com.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * @classname: ProtoBufTest
 * @description:测试
 * @author: liulin
 * @time: 2021/3/27 14:11
 */
public class ProtoBufTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        short a = 100;
        DataInfo.Student student = DataInfo.Student.newBuilder().setName("刘林").setAge(24).setAddress("浙江省杭州市上城区南星桥").setTest(a).build();
        byte[] studentToByteArray = student.toByteArray();
        DataInfo.Student student2 = DataInfo.Student.parseFrom(studentToByteArray);
        System.out.println(student2.getName());
        System.out.println(student2.getAge());
        System.out.println(student2.getAddress());

    }
}
