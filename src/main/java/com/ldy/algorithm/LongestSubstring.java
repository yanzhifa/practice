package com.ldy.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanz3 on 11/7/17.
 */
public class LongestSubstring {

    public static int lengthOfLongestSubstring(String s) {
        int result = 0;
        int[] cache = new int[256];
        for (int i = 0, j = 0; i < s.length(); i++) {

            System.out.println(s.charAt(i) + ":" + cache[s.charAt(i)]);
            j = (cache[s.charAt(i)] > 0) ? Math.max(j, cache[s.charAt(i)]) : j;
            cache[s.charAt(i)] = i + 1;
            result = Math.max(result, i - j + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwkew"));
        String out = "test";
//        while (out != null) {
//            System.out.println(out);
//        }
        System.out.println("finish."+out);

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        System.out.println(list);
        String[] str = new String[2];
        str[0] = "a";
        str[1] = "b";
        System.out.println(str);

        int a=1, b=2;
        a=a^b;
        b=a^b;
        a=b^a;
        System.out.println(a +":"+b);

        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(1);
        System.out.println(list1);

    }

    private void sort(int[] ints) {
        for(int i=0;i<ints.length;i++) {
            for(int j=i;j<ints.length-1;j++) {
                if(ints[j] == 0) {
                    ints[j]=ints[j]^ints[j++];
                    ints[j++]=ints[j]^ints[j++];
                    ints[j]=ints[j++]^ints[j];
                }
            }
        }
        int[] ints1 = new int[5];


    }
}
