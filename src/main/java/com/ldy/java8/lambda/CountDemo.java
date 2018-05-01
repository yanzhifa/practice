package com.ldy.java8.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanz3 on 12/20/16.
 */
public class CountDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        long count = list.stream().filter(l->{
            if(l.equalsIgnoreCase("D"))
                return true;
            else
                return false;
        }).count();
        System.out.println(count);

        list.forEach(l->{

            if(l.equalsIgnoreCase("D")) {
                System.out.println("True");
            }
        });

        System.out.println(0x2);

        System.out.println(Integer.parseInt("42", 16));

        System.out.println(Integer.parseInt("42", 16) & 0x4);
        System.out.println(Integer.parseInt("42", 16) & 0x2);
    }
}
