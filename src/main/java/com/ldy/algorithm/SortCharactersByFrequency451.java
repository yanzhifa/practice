package com.ldy.algorithm;

/**
 * Created by yanz3 on 4/23/18.
 */
public class SortCharactersByFrequency451 {

    public static String frequencySort(String str){
        StringBuffer sb = new StringBuffer();
        int[] array = new int[122];
        for (int i = 0; i < str.length(); i++) {
            array[(int) str.charAt(i)]++;
        }
        for (int i = array.length; i > 0; i--) {
            int index = 0, max = 0;
            for (int j = 0; j < array.length; j++) {
                max = max > array[j] ? max : array[j];
                index = max > array[j] ? index : j;
            }
            if (index == 0) break;

            for (int n = 0; n < max; n++) {
                sb.append((char) index);
            }
            array[index] = 0;
        }
        return sb.toString();
    }

    public static void main(String[] args) {

        String str = "wesfdsges";

        System.out.println(frequencySort(str));



        char c = 'a';
//        System.out.println((int) c);
//        System.out.println((int) 'Z');
//        System.out.println((int) 'z');


//        System.out.println((char) 97);
    }
}
