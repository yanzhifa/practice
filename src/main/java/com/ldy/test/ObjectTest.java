package com.ldy.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanz3 on 2/8/17.
 */
public class ObjectTest {
    public static void main(String[] args) {
        Person p1 = new Person("jack", 20);
        System.out.println(p1);
        List<Person> pList = new ArrayList<>();
        Person p2 = p1;
        Person p3 = p1;
        pList.add(p2);
        pList.add(p3);
//        pList.add(new Person());
//        pList.add(new Person());
///        pList.forEach(person -> {
//            person = p1;
//        });
        System.out.println(pList);


        System.out.println("----------------------");

        Persons ps = new Persons(new Person("Mary", 10), new Person("Jack", 12));
        System.out.println(ps);
        List<Persons> personsList = new ArrayList<>();
        personsList.add(new Persons(new Person("11",1), new Person("2",2)));
        System.out.println(personsList);
        personsList.forEach(persons -> {
            persons.setBoy(ps.getBoy());
            persons.setGirl(ps.getGirl());
        });
        System.out.println(personsList);
    }
}

