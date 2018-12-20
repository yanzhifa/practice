package com.ldy.lombok;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@Slf4j
@EqualsAndHashCode(of = {"name"}, callSuper = false)
public class Person {

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    private String name;

    private String address;

    @ToString.Exclude
    private boolean deleted = false;

    public static void main(String[] args) {

        Person p1 = new Person("joe", "east road");
        Person p2 = new Person("jack", "address2");

        List<Person> list = new ArrayList<>();

        list.add(p1);
        list.add(p2);

        Person p3 = new Person("joe", "asdfasdf");

        System.out.println("Contains joe: "+list.contains(p3));

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(10));
        }

        try {

            System.out.println(1/0);
        } catch (Exception e) {
            log.error("error ecour because: {}","1/0",e);
        }
    }
}
