package com.ldy.java8.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yanz3 on 11/8/16.
 */
public class ComparatorDemo {
    public static void main(String[] args) {
        List<Student> list = new ArrayList();
        list.add(new Student("Ram", 20));
        list.add(new Student("Shyam", 22));
        list.add(new Student("Kabir", 18));
        System.out.println("...befor sorting...");
        for (Student s : list) {
            System.out.println(s.getName());
        }
        //define comparator
        Comparator<Student> comp = (Student s1, Student s2) -> s1.getName().compareTo(s2.getName());
        Collections.sort(list, comp);
        System.out.println("...after sorting...");
        for (Student s : list) {
            System.out.println(s.getName());
        }

        System.out.println("==========================");
        List<Student> list1 = new ArrayList();
        list1.add(new Student("Ram", 20));
        list1.add(new Student("Shyam", 22));
        list1.add(new Student("Kabir", 18));
        for (Student st : list1) {
            System.out.println(st.customShow(s -> s.getName() + ": " + s.getAge()));
        }


        List<String> st1 = new ArrayList<>();
        List<String> st2 = new ArrayList<>();
        st1.add("1");
        st1.add("2");
        st1.add("3");

        st2.add("1");
        st2.add("22");
        st2.add("33");

        st1.contains("2");

        System.out.println(st1+":"+st2);
        st2.retainAll(st1);
        System.out.println(st2);
    }
}
