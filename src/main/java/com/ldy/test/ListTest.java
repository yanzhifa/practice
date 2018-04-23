package com.ldy.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yanz3 on 8/16/16.
 */
public class ListTest {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        ListTest test = new ListTest();
        List<Person> list =  new ArrayList<>();
        Person p1 = test.new Person("Jack", 20);
        Person p2 = test.new Person("Mary", 22);
        Person p3 = test.new Person("Ming", 25);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        List<Person> ll = list.parallelStream().filter(person -> {
            if(person.getAge() < 30) {
                return  true;
            } else {
                return  false;
            }
        }).collect(Collectors.toList());
        logger.info("List size is {}", ll.size());
        System.out.println(ll.size());
    }

    class Person {
        String name;
        Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
