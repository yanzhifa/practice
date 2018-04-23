package com.ldy.test;

/**
 * Created by yanz3 on 3/14/17.
 */
public class FinalTest {

    public static void main(String[] args) {

        Person girl = new Person("Jane", 18);
        Person boy = new Person("Jay", 20);
        final Persons persons = new Persons(boy, girl);

        Person b = persons.getBoy();
        b.setAge(30);

        Person b2 = new Person("Jone", 40);
        persons.setBoy(b2);

        System.out.println(persons);
    }
}
