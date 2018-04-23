package com.ldy.ip;

import org.apache.commons.lang3.StringUtils;
import sun.net.spi.nameservice.dns.DNSNameService;

import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanz3 on 1/15/17.
 */
public class Ip2Hostname {
    public static void main(String[] args) throws UnknownHostException, SocketException {

        InetAddress addr = InetAddress.getByName("vcapp32.localdomain.local");
        System.out.println(addr.getHostAddress());
        String host = addr.getHostName();
        System.out.println(host);

        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");

        System.out.println(list.toString());


        InetAddress address = InetAddress.getLocalHost();
//        ipadd = addr.getHostAddress();
        System.out.println("Ip: " + address.getHostAddress());

        InetAddress localHost = Inet4Address.getLocalHost();
        NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);

        for (InterfaceAddress iaddress : networkInterface.getInterfaceAddresses()) {
            System.out.println(iaddress.getNetworkPrefixLength());
        }
    }

}
