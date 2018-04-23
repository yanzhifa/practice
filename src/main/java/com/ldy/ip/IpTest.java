package com.ldy.ip;

import java.net.InetAddress;
import java.net.NetworkInterface;

public class IpTest {

    public static boolean isLocalAddress(String host) {
        if (host == null)
            return false;
        try {
            final InetAddress addr = InetAddress.getByName(host);
            // Check if the address is a valid special local or loop back
            if (addr.isAnyLocalAddress() || addr.isLoopbackAddress()) {
                return true;
            } else {
                // Check if the address is defined on any interface
                return NetworkInterface.getByInetAddress(addr) != null;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(isLocalAddress("10.62.91.138"));
    }
}
