package com.ldy.ip;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Created by yanz3 on 11/15/16.
 */
public class IPCheck {

    public static void main(String[] args) {
        byte[] ipAddr = new byte[]{127, 0, 0, 1};
        try {
            InetAddress address = InetAddress.getByName("10.62.81.0");
            System.out.println("Local Address: " + address.isAnyLocalAddress());
            System.out.println("Link Local Address: " + address.isLinkLocalAddress());
            System.out.println("Loopback Address: " + address.isLoopbackAddress());
            System.out.println("Multicast Address: " + address.isMulticastAddress());
            System.out.println("Site local Address: " + address.isSiteLocalAddress());

            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(address);
            System.out.println(networkInterface.getInterfaceAddresses());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
