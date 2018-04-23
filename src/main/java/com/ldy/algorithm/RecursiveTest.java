package com.ldy.algorithm;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by yanz3 on 4/23/18.
 */
public class RecursiveTest {


    private static String reverse(String str) {
        if ((null == str) || (str.length() <= 1)) {
            return str;
        }
        String reverse = reverse(str.substring(1));
        //System.out.println(reverse);
        return reverse + str.charAt(0);
    }

    public static int reverse(int x) {
        long ret = 0;

        while (x != 0) {
            ret = ret * 10 + x % 10;
            x /= 10;
        }
        return (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE) ? 0 : (int) ret;
    }

    public static void main(String[] args) {

        Set<String> set = new HashSet<>();
        System.out.println(reverse("12345"));

        System.out.println(reverse(12345));

    }
}
