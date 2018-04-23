package com.ldy.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yanz3 on 10/18/16.
 */
public class StringTest {
    public static void main(String[] args) {
        String str = "asdfasdf\r\nggggggg";
        System.out.println(str);
//        System.out.format("The value of i is: %d%n", 1);

        StringBuffer buffer = new StringBuffer();
        buffer.append("asdf").append("\n\r").append("44444");
        System.out.println(buffer.toString());

        String test = "asdfasdf\"22222\"22";
        System.out.println(test.replace("\"","\'"));

        Map<String, Integer> map = new HashMap<>();
        //map.put("1",11);
        //map.put("2",22);
        System.out.println(map.entrySet().size());

        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("222");
        System.out.println(list.toString());
        list.remove(0);
        System.out.println(list.toString());


        String a1 = "hello world";
        String a2=new String("hello world");
        String a3 = "hello";
        String a4 = " world";
        String a5="hello"+" world";
        String a6=a3+a4;
        System.out.println(a1==a5);
        System.out.println(a1==a6);
        System.out.println(a1 == a5.intern());
        System.out.println(a1 == a6.intern());

    }
}
