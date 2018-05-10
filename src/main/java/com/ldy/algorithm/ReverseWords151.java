package com.ldy.algorithm;

/**
 * Created by yanz3 on 5/4/18.
 */
public class ReverseWords151 {

    public static String reverseWords(String s) {
        if (s.trim().equals("")) {
            return "";
        }
        String result = "";
        String[] strings = s.split(" ");

        for (int i = strings.length - 1; i >= 1; i--) {
            if(strings[i].trim().equals(""))
                continue;
            result += strings[i] + " ";
        }
        result += strings[0];
        return result;
    }

    public static void main(String[] args) {

        System.out.println(reverseWords(" the"));
    }
}
