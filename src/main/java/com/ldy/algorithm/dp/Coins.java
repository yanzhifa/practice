package com.ldy.algorithm.dp;

/**
 * Created by yanz3 on 5/5/18.
 */
public class Coins {

    public static int search(int value) {
        if (value == 1 || value == 3 || value == 5) {
            return 1;
        }

        int coin1 = search(value - 1);
        int coin2 = search(value - 3);
        int coin3 = search(value - 5);

        return 1 + Math.min(coin1, Math.min(coin2, coin3));
    }

    /**
     * d(i) = min{d(i-1) + 1, d(i-3)+1, d(i-5)+1}
     *
     * @param number
     * @return
     */
    public static int coinNumber(int number) {
//        int[] value = {1, 3, 5};
        int[] value = {2};
        int[] result = new int[number];
        //result[0] = 0;
        //result[1] = 1;

        for (int i = 1; i < number; i++) {
            result[i] = number;
            for (int num : value) {
                if (num <= i && result[i - num] + 1 < result[i])
                    result[i] = result[i - num] + 1;
            }
        }
        return result[number - 1];
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        System.out.println(coinNumber(8));
    }
}
