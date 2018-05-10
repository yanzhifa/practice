package com.ldy.algorithm.dp;

/**
 * Created by yanz3 on 5/6/18.
 */
public class CoinChange {

    public static int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount <= 0)
            return 0;

        int[] result = new int[amount + 1];
        result[0] = 0;

        for (int money = 1; money <= amount; money++) {
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                if (money >= coin) {
                    min = Math.min(min, result[money - coin]);
                }
            }

            result[money] = (min != Integer.MAX_VALUE) ? min + 1 : min;
        }
        return result[amount] == Integer.MAX_VALUE ? -1 : result[amount];
    }

    public static void main(String[] args) {
        int[] coins = {2};
        System.out.println(coinChange(coins, 3));
    }

}
