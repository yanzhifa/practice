package com.ldy.algorithm;

/**
 * Created by yanz3 on 5/6/18.
 */
public class ArrangeCoins441 {

    public static int arrangeCoins(int n) {

        int temp = n;
        int count = 0;
        if (n < 2)
            return n;
        if (n == 2)
            return 1;
        if (n == 3)
            return 2;
        for (int i = 2; i < n; i++) {
            if (temp <= 0)
                break;
            temp = temp - i;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
//        System.out.println(arrangeCoins(2147483647));
        System.out.println(arrangeCoins(2));


    }
}
