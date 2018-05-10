package com.ldy.algorithm;

/**
 * Created by yanz3 on 5/6/18.
 */
public class Shortest {

    public static int solution(int N) {
        int ret = 1;
        if (N == 1) {
            return ret;
        }
        while (N != 1) {
            if (N % 2 == 0) {
                N /= 2;
            } else {
                N -= 1;
            }
            ret++;
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(solution(2147483647));
    }
}
