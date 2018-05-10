package com.ldy.algorithm.dp;

/**
 * Created by yanz3 on 5/5/18.
 */
public class Rober {

    public static int search(int index, int[] nums) {
        if (index < 0)
            return 0;
        return Math.max(nums[index] + search(index - 2, nums), search(index - 1, nums));
    }

    public static int rob(int[] nums) {
        if (nums.length == 0)
            return 0;
        int[] f = new int[nums.length + 1];
        int[] s = new int[nums.length + 1];
        f[0] = 0; s[0] = 0;
        f[1] = nums[0]; s[1] = 1;

        for (int i = 2; i <= nums.length; ++i) {
            f[i] = Math.max(nums[i - 1] + f[i - 2], f[i - 1]);
            s[i] = nums[i - 1] + f[i - 2] > f[i - 1] ? i - 2 : i - 1;
        }

        int n = nums.length;
        while (s[n] != 0) {
            System.out.println(s[n]);
            n = s[n-1];
        }
        return f[nums.length];
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 4, 5};
        System.out.println(rob(nums));
    }
}
