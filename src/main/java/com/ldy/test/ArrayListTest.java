package com.ldy.test;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yanz3 on 12/27/16.
 */
public class ArrayListTest {

    public static void main(String[] args) {
        String[] strings = new String[]{"a", "b"};
        List<String> params = new ArrayList<>(2);
        params.add("a");
        params.add("b");
        //params = Arrays.asList("a", "b");
        params.add("c");

        params.add(null);
        System.out.println(params);
        params.clear();
        System.out.println(params);

        List<String> strings1 = null;
        for(String s: strings1) {
            System.out.println(s);
        }

        List<String> list = Lists.newArrayList(strings);
        list.add("c");
        System.out.println(list);
        list.clear();
        System.out.println(list);


        System.out.println("-----------------------------------");
        List<Person> personList = new ArrayList<>();
        personList.clear();
        System.out.println(personList);
        personList.add(new Person("Jack", 20));
        personList.add(new Person("Mary", 25));
        personList.add(new Person("Jone", 30));
        System.out.println(personList);
        test(personList);
        System.out.println(personList);

//        for(Person person:personList) {
//            personList.remove(person);
//        }

        System.out.println(personList);

        Person person = new Person("test", 15);

        System.out.println(person.getClass());
        personList.clear();
        System.out.println(personList);

        List<String> list1 = Arrays.asList("aaa","bbb");
        List<String> arrayList = new ArrayList<>();
        arrayList.addAll(list1);
        System.out.println(arrayList);
        arrayList.remove("aaa");
        System.out.println(arrayList);

        System.out.println(StringUtils.substringAfterLast("com.vmware.core.Hello", "."));

        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        List<Integer> list3 = new ArrayList<>();
        list3.add(4);
        list3.add(2);
        list3.add(3);
        list2.removeAll(list3);
        System.out.println(list2);

    }

    public static void test(List<Person> personList) {
        personList.forEach(person -> {
            if(person.getName().equals("Jack")) {
                person.setAge(100);
            }
        });

    }
}
