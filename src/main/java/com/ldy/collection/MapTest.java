package com.ldy.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yanz3 on 1/10/17.
 */
public class MapTest {

    public static void main(String[] args) {
        Map<String, Person> map = new HashMap<>();
        map.put("1", new Person("Joe", 20));
        map.put("2", new Person("Nancy", 21));
        List<Person> list = new ArrayList<>();
        list.addAll(map.values());
        System.out.println(list);
    }



    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
