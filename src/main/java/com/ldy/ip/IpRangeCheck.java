package com.ldy.ip;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by yanz3 on 11/2/16.
 */
public class IpRangeCheck {

    public static long ipToLong(InetAddress ip) {
        byte[] octets = ip.getAddress();
        long result = 0;
        for (byte octet : octets) {
            result <<= 8;
            result |= octet & 0xff;
        }
        return result;
    }

    public static void main(String[] args) throws UnknownHostException {
        long ipLo = ipToLong(InetAddress.getByName("10.62.91.131"));
        long ipHi = ipToLong(InetAddress.getByName("10.62.91.134"));
        long ipToTest = ipToLong(InetAddress.getByName("10.62.91.135"));

        System.out.println(ipToTest >= ipLo && ipToTest <= ipHi);
    }
}
