package com.ldy.algorithm;

import java.util.HashMap;

/**
 * Created by yanz3 on 5/4/18.
 */
public class LongestSubstring3 {

    public static int lengthOfLongestSubstring(String s) {
        if (s.trim().equals(""))
            return 0;
        int max = 0, tmp = 0;
        String substring = "";
        for (int i = 0; i < s.length(); i++) {
            if (substring.contains(String.valueOf(s.charAt(i)))) {
                int index = substring.indexOf(String.valueOf(s.charAt(i)));
                int move = substring.length() - index - 1;
                substring = substring.substring(index+1);
                substring += s.charAt(i);
                tmp = 1+move;
            } else {
                substring += s.charAt(i);
                tmp++;
            }
            if (tmp > max) {
                max = tmp;
            }
        }
        return max;
    }

    public static int lengthOfLongestSubstring1(String s) {
        if (s.length()==0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max=0;
        for (int i=0, j=0; i<s.length(); ++i){
            if (map.containsKey(s.charAt(i))){
                j = Math.max(j,map.get(s.charAt(i))+1);
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i-j+1);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }
}
