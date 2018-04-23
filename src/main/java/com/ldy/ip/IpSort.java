package com.ldy.ip;

import sun.nio.ch.Net;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by yanz3 on 12/23/16.
 */
public class IpSort {
    static Long toNumeric(String ip) {
        Scanner sc = new Scanner(ip).useDelimiter("\\.");
        Long l = (sc.nextLong() << 24) + (sc.nextLong() << 16) + (sc.nextLong() << 8)
                + (sc.nextLong());

        sc.close();
        return l;
    }

    public static void main(String[] args) {
        Comparator<String> ipComparator = new Comparator<String>() {
            @Override
            public int compare(String ip1, String ip2) {
//                Integer leftIp = (Integer)NetworkUtil.toIntegerIP(ip1);
                return Long.compare(NetworkUtil.toLongIP(ip1), NetworkUtil.toLongIP(ip2));
//                return ((Integer)NetworkUtil.toIntegerIP(ip1)).compareTo((Integer)NetworkUtil.toIntegerIP(ip2));
//                return toNumeric(ip1).compareTo(toNumeric(ip2));
            }
        };


        SortedSet<String> ips = new TreeSet<>(ipComparator);
        ips.addAll(Arrays.asList("192.168.0.1", "192.168.0.2", "192.168.0.9",
                "9.9.9.9","127.0.0.1","200.233.233.45"));
        System.out.println(ips);

        System.out.println(1|2);
        System.out.println(NetworkUtil.toLongIP("9.9.9.9"));
        System.out.println(NetworkUtil.toLongIP("127.0.0.1"));
        System.out.println(NetworkUtil.toLongIP("192.168.10.1"));

        System.out.println(2 << 2 | 2 << 1 | 2);
        System.out.println(14 >> 1 & 0xff);
    }
}
