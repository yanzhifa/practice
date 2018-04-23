package com.ldy.ip;

/* **********************************************************
 * NetworkUtil.java
 *
 * Copyright (C) 2015 VMware, Inc.
 * All Rights Reserved
 * **********************************************************/

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract public class NetworkUtil {
    private static final Logger logger = LoggerFactory.getLogger(NetworkUtil.class);
    /**
     * Contains all valid subnet masks and their lengths for quick checking.
     */
    private static final HashMap<String, Long> VALID_SUBNET_MASKS = new HashMap<String, Long>();
    static {
        VALID_SUBNET_MASKS.put("255.255.255.255", 1L);           VALID_SUBNET_MASKS.put("255.255.255.0", 256L);
        VALID_SUBNET_MASKS.put("255.255.255.254", 2L);           VALID_SUBNET_MASKS.put("255.255.254.0", 512L);
        VALID_SUBNET_MASKS.put("255.255.255.252", 4L);           VALID_SUBNET_MASKS.put("255.255.252.0", 1024L);
        VALID_SUBNET_MASKS.put("255.255.255.248", 8L);           VALID_SUBNET_MASKS.put("255.255.248.0", 2048L);
        VALID_SUBNET_MASKS.put("255.255.255.240", 16L);          VALID_SUBNET_MASKS.put("255.255.240.0", 4096L);
        VALID_SUBNET_MASKS.put("255.255.255.224", 32L);          VALID_SUBNET_MASKS.put("255.255.224.0", 8192L);
        VALID_SUBNET_MASKS.put("255.255.255.192", 64L);          VALID_SUBNET_MASKS.put("255.255.192.0", 16384L);
        VALID_SUBNET_MASKS.put("255.255.255.128", 128L);         VALID_SUBNET_MASKS.put("255.255.128.0", 32768L);

        VALID_SUBNET_MASKS.put("255.255.0.0", 65536L);       VALID_SUBNET_MASKS.put("255.0.0.0", 16777216L);
        VALID_SUBNET_MASKS.put("255.254.0.0", 131072L);      VALID_SUBNET_MASKS.put("254.0.0.0", 33554432L);
        VALID_SUBNET_MASKS.put("255.252.0.0", 262144L);      VALID_SUBNET_MASKS.put("252.0.0.0", 67108864L);
        VALID_SUBNET_MASKS.put("255.248.0.0", 524288L);      VALID_SUBNET_MASKS.put("248.0.0.0", 134217728L);
        VALID_SUBNET_MASKS.put("255.240.0.0", 1048576L);     VALID_SUBNET_MASKS.put("240.0.0.0", 268435456L);
        VALID_SUBNET_MASKS.put("255.224.0.0", 2097152L);     VALID_SUBNET_MASKS.put("224.0.0.0", 536870912L);
        VALID_SUBNET_MASKS.put("255.192.0.0", 4194304L);     VALID_SUBNET_MASKS.put("192.0.0.0", 1073741824L);
        VALID_SUBNET_MASKS.put("255.128.0.0", 8388608L);     VALID_SUBNET_MASKS.put("128.0.0.0", 2147483648L);

        VALID_SUBNET_MASKS.put("0.0.0.0", 4294967296L);
    }

    /**
     * Regex to determine if an IP is valid.  Found on stack overflow.
     */
    private static final Pattern IPV4_PATTERN = Pattern.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

    /**
     * Regex to extract IPV4 from a text.
     * */
    private static final Pattern IPV4_PATTERN_IN_A_TEXT = Pattern.compile("(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})");

    /**
     * Regex to determine if an IPv6 is valid.
     * See: http://www.java2s.com/Code/Java/Network-Protocol/DetermineifthegivenstringisavalidIPv4orIPv6address.htm
     */
    private static final Pattern IPV6_PATTERN = Pattern.compile("([0-9a-f]{1,4}:){7}([0-9a-f]){1,4}",
            Pattern.CASE_INSENSITIVE);

    /**
     * Regex to determine if a hostname is valid.
     */
    // http://stackoverflow.com/questions/106179/regular-expression-to-match-hostname-or-ip-address
    private static final Pattern HOSTNAME_PATTERN = Pattern.compile("^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])\\.)*([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9\\-]*[A-Za-z0-9])$");

    /**
     * Regex to determine if a leaf domain is valid.
     */
    private static final Pattern SHORT_HOSTNAME_PATTERN = Pattern.compile("^([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9\\-]*[a-zA-Z0-9])$");

    private static final int MAX_PORT = 65535;

    /**
     * Return if a host is the local machine
     * @param host host
     * @throws Exception
     */
    public static boolean isLocalAddress(String host) {
        if (host == null) return false;
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



    /**
     * Returns the bijective base 26 representation of a number
     * @param n number
     * @return bijective base 26 (a, b, c, d... z, aa, ab, ... az, ba, bb...)
     */
    private static String toBijectiveBase26(int n) {
        final String alphabet = "abcdefghijklmnopqrstuvwxyz";
        final StringBuffer buffer = new StringBuffer();
        while (n > 0) {
            buffer.append(alphabet.charAt((--n) % 26));
            n /= 26;
        }
        return buffer.reverse().toString();
    }




    /**
     * Increment an IP address.
     * @param ipMin IP address to increment.
     * @param amount amount to increment by.
     */
    public static String incrementIp(String ipMin, int amount) {
        int i = toIntegerIP(ipMin) + amount;
        return String.format("%d.%d.%d.%d", i >>> 24 & 0xFF, i >> 16 & 0xFF,
                i >>   8 & 0xFF, i >>  0 & 0xFF);
    }



    /**
     * Determine if a given port is valid.
     */
    public static boolean isValidPort(int port) {
        return port > 0 && port <= MAX_PORT;
    }

    /**
     * Determine if an address is reacheable
     */
    public static boolean isReachableAddress(String address) {
        try {
            return InetAddress.getByName(address).isReachable(10000);
        } catch (Exception e) {
            logger.error("Failed to check reachable address: " + address, e);
            return false;
        }
    }


    /**
     * Get last octet of an IP.
     */
    public static String getLastOctet(String ip) {
        // Reverse the octets
        final int integerIP = NetworkUtil.toIntegerIP(ip);
        return String.valueOf(integerIP >> 0 & 0xFF);
    }

    /**
     * Get a range of addresses, inclusive
     * @param ipMin min IP address
     * @param ipMax max IP address
     */
    public static List<String> getRange(String ipMin, String ipMax) {
        final int min = toIntegerIP(ipMin);
        final int max = toIntegerIP(ipMax);
        final List<String> result = new ArrayList<String>(max - min);
        for (int i = min; i <= max; i++) {
            final String ip = String.format("%d.%d.%d.%d", i >>> 24 & 0xFF, i >> 16 & 0xFF,
                    i >>   8 & 0xFF, i >>  0 & 0xFF);
            result.add(ip);
        }
        return result;
    }

    /**
     * Get size of range, inclusive
     * @param ipMin min IP address
     * @param ipMax max IP address
     */
    public static int getRangeSize(String ipMin, String ipMax) {
        final int min = toIntegerIP(ipMin);
        final int max = toIntegerIP(ipMax);
        return max - min + 1;
    }


    /**
     * Determine if IP ranges overlap.
     * @param firstMin lower bound of first range.
     * @param firstMax upper bound of first range.
     * @param secondMin lower bound of second range.
     * @param secondMax upper bound of second range.
     */
    public static boolean isOverlappingIPRange(String firstMin, String firstMax,
                                               String secondMin, String secondMax) {
        int firstMinInteger = toIntegerIP(firstMin);
        int firstMaxInteger = toIntegerIP(firstMax);

        int secondMinInteger = toIntegerIP(secondMin);
        int secondMaxInteger = toIntegerIP(secondMax);

        return firstMinInteger <= secondMaxInteger && secondMinInteger <= firstMaxInteger;
    }


    /**
     * Convert a textual IP to an integer.  NB: Java uses network byte order internally.
     */
    public static int toIntegerIP(String ip) {
        if (StringUtils.isBlank(ip)) {
            return 0;
        }
        String[] parts = StringUtils.split(ip, '.');
        return (Integer.parseInt(parts[0]) << 24 | Integer.parseInt(parts[2]) << 8
                |  Integer.parseInt(parts[1]) << 16 | Integer.parseInt(parts[3]));
    }

    public static long toLongIP(String ip) {
        if (StringUtils.isBlank(ip)) {
            return 0;
        }
        String[] parts = StringUtils.split(ip, '.');
        return (Long.parseLong(parts[0]) << 24 | Long.parseLong(parts[2]) << 8
                |  Long.parseLong(parts[1]) << 16 | Long.parseLong(parts[3]));
    }



    /**
     * Check if a string is a subnet mask.
     */
    public static boolean isValidSubnet(String s) {
        if (s == null) return false;
        return VALID_SUBNET_MASKS.containsKey(s);
    }

    /**
     * Check IPs could be in the same subnet.
     * Reference: http://stackoverflow.com/questions/8555847/test-with-java-if-two-ips-are-in-the-same-network
     * @param subnetMask subnet mask
     * @param ips arbitrary IPs
     */
    public static boolean isIPListInSubnet(String subnetMask, String... ips) {
        int n = ips.length;
        if (n == 0) {
            logger.error("calling isIPListInSubnet() function expects at least one IP.");
            return false;
        }
        try {
            byte[] base = InetAddress.getByName(ips[0]).getAddress();
            byte[] mask = InetAddress.getByName(subnetMask).getAddress();
            if (base.length != mask.length)
                return false;

            for (int i = 1; i < n; i++) {
                byte[] cand = InetAddress.getByName(ips[i]).getAddress();
                if (cand.length != mask.length)
                    return false;
                for (int j = 0; j < mask.length; j++) {
                    if ((base[j] & mask[j]) != (cand[j] & mask[j])) {
                        return false;
                    }
                }
            }
        } catch (UnknownHostException e) {
            logger.error("One of IP address could not be determined. netmask: {}, ips: {}", subnetMask, ips);
            return false;
        }

        return true;
    }

    /**
     * Check for a valid hostname.
     */
    public static boolean isValidHostname(String s) {
        if (s == null) return false;
        return s.length() <= 255 && HOSTNAME_PATTERN.matcher(s).matches();
    }

    /**
     * Check for a valid hostname without domain
     */
    public static boolean isValidShortHostname(String s) {
        if (s == null) return false;
        return s.length() <= 64 && SHORT_HOSTNAME_PATTERN.matcher(s).matches();
    }

    /**
     * Check for a valid FQDN hostname.
     */
    public static boolean isValidFqdnHostname(String s) {
        if (StringUtils.isBlank(s)) {
            return false;
        }

        int hostIndex = s.indexOf('.');
        if (hostIndex == -1) {
            return false;
        }
        if (!isValidHostname(s.substring(0, hostIndex))) {
            return false;
        }
        String domainName = s.substring(hostIndex + 1);
        logger.info("domain name part: {}", domainName);
        // We allow local domains so we can't use the Apache lib here.
        if (!isValidHostname(domainName)){
            return false;
        }
        return true;
    }

    /**
     * Check for a valid vlanId.
     */
    public static boolean isValidVlanId(int vlanId) {
        return vlanId >= 0 && vlanId <= 4095;
    }

    /**
     * Get a link local address
     */
    public static String findLinkLocalAddress(List<String> addresses) {
        for (final String address: addresses) {
            try {
                if (InetAddress.getByName(address).isLinkLocalAddress()) {
                    return address;
                }
            } catch (Exception e) {

            }
        }
        return null;
    }

    /**
     * Get the network prefix length corresponding to the specified subnet mask.
     */
    public static int getPrefixLengthFromSubnet(String subnet) {
        long maxLength = VALID_SUBNET_MASKS.get(subnet);
        int oneCount = 0;
        maxLength >>= 1;
        while (maxLength > 0) {
            maxLength >>= 1;
            oneCount++;
        }
        return 32 - oneCount;
    }

    public static boolean isHttpUrlAccessible(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                logger.error("The url: {} cannot be accessed with response code: {}", url, responseCode);
                return false;
            }
            return true;
        } catch (MalformedURLException me) {
            logger.error("The url: {} is invalid.", url, me);
            return false;
        } catch (IOException ie) {
            logger.error("The url: {} cannot be connected.", url, ie);
            return false;
        }
    }

    /**
     * Extract ipv4 address from input text, returns first occurrence if multiple are found, returns empty string if not found.
     * */
    public static String extractIPV4FromText(String text){
        String ipAddress = "";

        if (text == null || text.isEmpty()) {
            return ipAddress;
        }

        Matcher match = IPV4_PATTERN_IN_A_TEXT.matcher(text);
        if(match.find()) {
            ipAddress = match.group();
        }

        return ipAddress;
    }

    public static void main(String[] args) {
        int size = getRangeSize("10.62.81.100", "10.62.81.100");
        System.out.println(size);

        List<String> range = getRange("10.62.81.100", "10.62.81.102");
        List<String> range1 = getRange("10.62.81.90", "10.62.81.92");
        System.out.println(range);
        System.out.println(range1);
        range.addAll(range1);
        Collections.sort(range);
        System.out.println(range);

        System.out.println(incrementIp("10.62.91.74", -1));



    }
}
