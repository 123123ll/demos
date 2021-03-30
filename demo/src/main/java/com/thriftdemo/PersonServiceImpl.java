package com.thriftdemo;

import com.thrift.generated.DataExcepion;
import com.thrift.generated.Person;
import com.thrift.generated.PersonService;
import org.apache.thrift.TException;

/**
 * @ClassName: PersonServiceImpl
 * @Description: 实现类
 * @Author: liu
 * @Date: 2021/3/30 11:53
 */
public class PersonServiceImpl implements PersonService.Iface {

    @Override
    public Person getPersonByUsername(String usrname) throws DataExcepion, TException {
        System.out.println("Get Client Param: "+usrname);
        Person person = new Person();
        person.setUsername(usrname);
        person.setAge(24);
        person.setMarried(false);
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataExcepion, TException {
        System.out.println("Get Client Param");
        System.out.println(person.getUsername());
        System.out.println(person.getAge());
        System.out.println(person.isMarried());
    }
}
