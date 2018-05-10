package com.ldy.java8;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Collections.singleton;

/**
 * Created by yanz3 on 5/24/17.
 */
public class ListTest {

    private int count;

    private void hello1(int count){
        this.count = count;
    }

    private void hello2(){
        this.hello1(1);
    }

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("A");
        stringList.add("B");
        stringList.add("c");
        if (stringList.stream().filter(s -> s.equalsIgnoreCase("d")).findFirst().isPresent()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

        int a = 1;
        int b = 3;
        float c = (float) a / (float) b;
        System.out.print(c);
        System.out.print(Math.round(33.33));

        System.out.println(stringList.indexOf("B"));


        try {
            InetAddress e = InetAddress.getByName("10.32.98.136");
            System.out.println(e.isLoopbackAddress());
            System.out.println(e.isAnyLocalAddress());
            System.out.println(NetworkInterface.getByInetAddress(e));
            System.out.println(NetworkInterface.getByInetAddress(e) != null);
            System.out.println(!e.isAnyLocalAddress() && !e.isLoopbackAddress() ? NetworkInterface.getByInetAddress(e) != null : true);
//            return !e.isAnyLocalAddress() && !e.isLoopbackAddress()? NetworkInterface.getByInetAddress(e) != null:true;
        } catch (Exception var2) {
//            return false;
        }

        Set<String> single = new HashSet<String>();
        System.out.println(Collections.singleton("One"));
//        single.addAll(Collections.singleton("One"));
        single = Collections.singleton("One");
        System.out.println(single);
        single.add("Two");
        System.out.println(single);


    }

    public static void hello() {
        System.out.println("hello");
    }
}
