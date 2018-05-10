package com.ldy.algorithm;

/**
 * Created by yanz3 on 5/6/18.
 */
public class PeriodTest {

    //"abcdabcdab"
    static int solution(int n) {
        int[] d = new int[30];
        int l = 0;
        int p;
        while (n > 0) {
            d[l] = n % 2;
            n /= 2;
            l++;
        }
        for (p = 1; p < 1 + l; ++p) {
            int i;
            boolean ok = true;
            for (i = 0; i < l - p; ++i) {
                if (d[i] != d[i + p]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                return p;
            }
        }
        return -1;
    }

    static int mySolution(int n) {
        int[] d = new int[30];
        int l = 0;
        int p;
        while (n > 0) {
            d[l] = n % 2;
            n /= 2;
            l++;
        }
        for (p = 1; p < l; ++p) {
            int i;
            boolean ok = true;
            for (i = 0; i < l - p; ++i) {
                if (d[i] != d[i + p]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                return i >= p ? p : i;
            }
        }
        return -1;
    }

    // 1101 1101 11
    // 1110 1110 11
    // 110110
    // 111011100110101100101000000000
    public static void main(String[] args) {
        System.out.println(mySolution(2510));


        System.out.println(Integer.parseInt("100111001110", 2));
        String result = Integer.toBinaryString(955);
        System.out.println(result);

        int n = 1000000000;
        int t = 0;  //用来记录位数
        double bin = 0; //用来记录最后的二进制数
        int r = 0;  //用来存储余数
        while (n != 0) {
            r = n % 2;
            n = n / 2;
            bin = r * Math.pow(10, t);
            t++;
        }
        System.out.println(bin);

        tenToTwo(995);
        System.out.println("-------");
        System.out.println(translate(123));

        for (int i = 31; i >= 0; i--)
            System.out.print(n >>> i & 1);
    }

    public static void tenToTwo(int data) {
        if (data == 1 || data == 0) {
            System.out.print(data);
        } else {
            tenToTwo(data / 2);
            System.out.print(data % 2);
        }
    }

    public static String translate(int num) {
        StringBuffer binary = new StringBuffer();
        while (num != 0 && num != 1) {
            binary.insert(0, num % 2);
            num = num / 2;
            if (num == 0 || num == 1) {
                binary.insert(0, num % 2);
            }
        }
        return binary.toString();

    }
}
