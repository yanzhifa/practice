package com.ldy.ip;

/**
 * Created by yanz3 on 12/27/16.
 */
public class SortNumber {

    public static void main(String[] args) {
        int[] range = {1};
        generateRange(range);
    }

    public static void generateRange(int[] range) {
        int left = 0;
        for (int i = 0, j = 1; i < range.length && j < range.length; i++, j++) {
            if (left == 0) {
                left = range[i];
            }

            if (Math.abs(range[j] - range[i]) == 1) {
                if (j == range.length - 1) {
                    System.out.println(left + ":" + range[j]);
                }
                continue;
            } else {
                System.out.println(left + ":" + range[i]);
                left = range[j];
                if (j == range.length - 1) {
                    System.out.println(left + ":" + range[j]);
                }
            }

        }

    }
}
