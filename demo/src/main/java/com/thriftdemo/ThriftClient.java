package com.thriftdemo;

import com.thrift.generated.Person;
import com.thrift.generated.PersonService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.layered.TFramedTransport;

/**
 * @ClassName: ThriftClient
 * @Description:客户端
 * @Author: liu
 * @Date: 2021/3/30 12:09
 */
public class ThriftClient {
    public static void main(String[] args) throws Exception{
        TTransport transport = new TFramedTransport(new TSocket("localhost",8899),600);
        TProtocol protocol = new TCompactProtocol(transport);
        PersonService.Client client = new PersonService.Client(protocol);

        try {
            transport.open();
            Person person = client.getPersonByUsername("张三");
            System.out.println(person.getUsername());
            System.out.println(person.getAge());
            System.out.println(person.isMarried());

            System.out.println("============");

            Person person2 = new Person();
            person2.setUsername("李四");
            person2.setAge(22);
            person2.setMarried(true);
            client.savePerson(person2);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage(),e);
        }finally {
            transport.close();
        }

    }
}
