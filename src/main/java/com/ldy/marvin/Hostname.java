package com.ldy.marvin;

import com.ldy.test.Persons;

/**
 * Created by yanz3 on 3/14/17.
 */
public class Hostname {

    enum HostnamePoolIteratorType {
        /**
         * Normal numbers, e.g. 0,1,2,3..9,10,11..
         */
        NUMERIC_N("0", "99"),

        /**
         * Double digit numbers, e.g. 00,01,02,03...09,10,11...
         */
        NUMERIC_NN("00", "99"),

        /**
         * Triple digit numbers, e.g. 000,001,002,003...009,010,011...100,101,102....
         */
        NUMERIC_NNN("000", "999"),

        /**
         * Quadruple digit numbers, e.g. 0000,0001,0002,0003...0009,0010,0011...1000,1001,1002....
         */
        NUMERIC_NNNN("0000", "9999"),

        /**
         * Alphabetical counting, e.g. A,B,C..Y,Z,AA,AB..,BA,BB..
         */
        ALPHA("a", "zz");

        final String minimumExample;
        final String maximumExample;

        HostnamePoolIteratorType(String minimumExample, String maximumExample) {
            this.minimumExample = minimumExample;
            this.maximumExample = maximumExample;
        }

        public String getMinimumExample() {
            return minimumExample;
        }

        public String getMaximumExample() {
            return maximumExample;
        }
    }

    public static String generateHostname(int offset, HostnamePoolIteratorType hostnamePoolIteratorType, String prefix,
                                          String separator, String postfix, String tld, int hostIndex) {
        final String numericPart;
        int hostnameIndex = offset + hostIndex;
        if (hostnamePoolIteratorType == HostnamePoolIteratorType.ALPHA) {
            numericPart = toBijectiveBase26(hostnameIndex + 1);
        } else if (hostnamePoolIteratorType == HostnamePoolIteratorType.NUMERIC_NN) {
            numericPart = (hostnameIndex <= 9) ? "0" + String.valueOf(hostnameIndex) : String.valueOf(hostnameIndex);
        } else if (hostnamePoolIteratorType == HostnamePoolIteratorType.NUMERIC_NNN) {
            if (hostnameIndex <= 9) {
                numericPart = "00" + String.valueOf(hostnameIndex);
            } else if (offset + hostIndex <= 99) {
                numericPart = "0" + String.valueOf(hostnameIndex);
            } else {
                numericPart = String.valueOf(hostnameIndex);
            }
        } else if (hostnamePoolIteratorType == HostnamePoolIteratorType.NUMERIC_NNNN) {
            if (hostnameIndex <= 9) {
                numericPart = "000" + String.valueOf(hostnameIndex);
            } else if (offset + hostIndex <= 99) {
                numericPart = "00" + String.valueOf(hostnameIndex);
            } else if (offset + hostIndex <= 999) {
                numericPart = "0" + String.valueOf(hostnameIndex);
            }  else {
                numericPart = String.valueOf(hostnameIndex);
            }
        } else {
            numericPart = String.valueOf(hostnameIndex);
        }

        return prefix + separator + numericPart + postfix + "." + tld;
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


    public static void main(String[] args) {
        System.out.println(generateHostname(0, HostnamePoolIteratorType.NUMERIC_N, "APP", "-", "local", "com.cn", 1));


    }
}
