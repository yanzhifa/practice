package com.ldy.algorithm;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by yanz3 on 5/3/18.
 */
public class SmallestNumber {

    public static void main(String[] args) {

        long n = 2147483647;
        System.out.println(Long.MAX_VALUE);


        int i = 1000000001;

        int k = ((Double) Math.sqrt(2147483647)).intValue();
        System.out.println(Math.sqrt(2147483647));
        int b = 46340 * 46340;
        System.out.println(b);

        int[] a = {1, 3, 6, 4, 1, 2};
        //System.out.println(small(a));

        //System.out.println(solution(4, 17));

        //System.out.println(bc(40, 20));

        System.out.println(binomial(9,4 ));

    }


    private static long binomial(int n, int k)
    {
        if (k>n-k)
            k=n-k;

        long b=1;
        for (int i=1, m=n; i<=k; i++, m--){
            b=b*m/i;
            if(b>1000000000) {
                return -1;
            }
        }

        return b;
    }

    private static int small(int[] a) {
        int result = 0;

        int[] array = new int[100];
        for (int i = 0; i < a.length; i++) {
            array[a[i]]++;
        }
        for (int i = 1; i < array.length; i++) {
            if (array[i] == 0) {
                result = array[i];
                break;
            }
        }
        return result;
    }

    public static int solution(int A, int B) {
        // write your code in Java SE 8
        int result = 0;
        int max = ((Double) Math.sqrt(B)).intValue();
        for (int i = 0; i <= max; i++) {
            int value = i * i;
            if (value >= A && value <= B) {
                result++;
            } else if (value > B) {
                break;
            }

        }
        return result;
    }

    public static int bc(int N, int K) {

        if (N <= K || K == 0) {
            return -1;
        }

        int diff = N - K;

        long sum = 1;
        int start = 1, end = 1;
        if (diff > K) {
            start = diff + 1;
            end = K;
        } else {
            start = K + 1;
            end = diff;
        }
        int j = end;
        for (int i = start; i < N; i++) {
            sum *= i;
            if(j>0) {
                end --;
            }
        }

        int sub = 1;
        for (int i = 1; i <= end; i++) {
            sub *= i;
        }

        long result = sum / sub;
        if (result > 1000000000) {
            return -1;
        }

        return (int) result;
    }

}
