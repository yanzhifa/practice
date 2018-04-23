package com.ldy.ip;

import javafx.collections.transformation.SortedList;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by yanz3 on 11/28/16.
 */
public class IpRangeList {

    public static void main(String[] args) {
        showIpList("192.168.101.151", "192.168.101.160");

        System.out.println("===================");

        List<String> list = new ArrayList<>();
        list.add("192.168.102.150");
        list.add("192.168.101.151");
        list.add("192.168.101.99");
        list.add("192.168.101.152");
        list.add("192.168.100.153");
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);

        Set<String> set = new TreeSet<>();
        set.add("192.168.100.150");
        set.add("192.168.100.155");
        set.add("192.168.100.152");
        set.add("192.168.100.151");
        set.add("192.168.100.156");
        set.add("192.168.100.150");
        System.out.println(set);
        set.remove("192.168.100.152");
        System.out.println(set);

        //InetAddress address = InetAddress.getByName("");


        Comparator<String> ipComparator = new Comparator<String>() {
            @Override
            public int compare(String ip1, String ip2) {
                return Long.compare(NetworkUtil.toIntegerIP(ip1), NetworkUtil.toIntegerIP(ip2));
            }
        };
        SortedSet<String> ips = new TreeSet<>(ipComparator);
        ips.addAll(Arrays.asList("192.168.0.1", "192.168.0.2", "192.168.0.3",
                "192.168.0.5","192.168.0.7","192.168.0.8"));
        ips.addAll(list);
        System.out.println(ips);


        // Generate new pool
        List<String> ipLists = Arrays.asList("192.168.0.1", "192.168.0.2", "192.168.0.3",
                "192.168.0.5","192.168.0.4","192.168.0.8");
        ipLists.stream().sorted(ipComparator);
        System.out.println(ipLists);

//        Comparator<String> ipCom = (ip1, ip2) -> Integer.compare(
//                NetworkUtil.toIntegerIP(ip1), NetworkUtil.toIntegerIP(ip2));

        ipLists.stream().sorted((ip1, ip2) -> Integer.compare(
                NetworkUtil.toIntegerIP(ip1), NetworkUtil.toIntegerIP(ip2))).collect(Collectors.toList());
        System.out.println(ipLists);

    }

    public static void generateNewIpPool(List<String> ipLists) {

        String minIp = "";

        for (int i = 0, j = 1; i < ipLists.size() && j < ipLists.size(); i++, j++) {
            if (StringUtils.isEmpty(minIp)) {
                minIp = ipLists.get(i);
            }
            ipLists.get(j);

        }

    }

    public static void showIpRange(SortedList<String> ipList) {
//        long start = host2long(ipStart);
//        long end = host2long(ipEnd);


    }


    public static void showIpList(String ipStart, String ipEnd) {
        try {
            long start = host2long(ipStart);
            long end = host2long(ipEnd);
            for (long i = start; i <= end; i++) {
                System.out.println(long2dotted(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static long ip2long(InetAddress ip) {
        long l = 0;
        byte[] addr = ip.getAddress();
        if (addr.length == 4) { //IPV4
            for (int i = 0; i < 4; ++i) {
                l += (((long) addr[i] & 0xFF) << 8 * (3 - i));
            }
        } else { //IPV6
            return 0;  // I dont know how to deal with these
        }
        return l;
    }


    public static long host2long(String host) {
        long ip = 0;
        if (!Character.isDigit(host.charAt(0))) return -1;
        int[] addr = ip2intarray(host);
        if (addr == null) return -1;
        for (int i = 0; i < addr.length; ++i) {
            ip += ((long) (addr[i] >= 0 ? addr[i] : 0)) << 8 * (3 - i);
        }
        return ip;
    }

    public static int[] ip2intarray(String host) {
        int[] address = {-1, -1, -1, -1};
        int i = 0;
        StringTokenizer tokens = new StringTokenizer(host, ".");
        if (tokens.countTokens() > 4) return null;
        while (tokens.hasMoreTokens()) {
            try {
                address[i++] = Integer.parseInt(tokens.nextToken()) & 0xFF;
            } catch (NumberFormatException nfe) {
                return null;
            }
        }
        return address;
    }

    public static String long2dotted(long address) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0, shift = 24; i < 4; i++, shift -= 8) {
            long value = (address >> shift) & 0xff;
            sb.append(value);
            if (i != 3) {
                sb.append('.');
            }
        }
        return sb.toString();
    }
}
