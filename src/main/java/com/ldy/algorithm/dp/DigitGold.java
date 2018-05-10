package com.ldy.algorithm.dp;

/**
 * Created by yanz3 on 5/3/18.
 */
public class DigitGold {
    public int n;
    int max_n = 5;//总金矿数
    int max_people = 100;//总人数
    int peopleNeed[] = {77, 22, 29, 50, 99};
    int gold[] = {92, 22, 87, 46, 90};
    //maxGold[i][j]保存了i个人挖前j个金矿能够得到的最大金子数，等于-1时表示未知
    public int[][] maxGold = new int[max_people][max_n];

    /**
     * F(n,w) = 0 (n<=1, w<p[0]);
     * F(n,w) = g[0] (n==1, w>=p[0]);
     * F(n,w) = F(n-1,w) (n>1, w<p[n-1])
     * F(n,w) = max(F(n-1,w), F(n-1,w-p[n-1])+g[n-1]) (n>1, w>=p[n-1])
     */
    public int getMaxGold(int people, int mineNum) {
        int retMaxGold = 0;
        if (people <= 0) {
            retMaxGold = 0;
        } else {
            if (maxGold[people][mineNum] != 0) {
                retMaxGold = maxGold[people][mineNum];
            } else if (mineNum == 0) {
                //如果只有一个金矿
                if (people >= peopleNeed[mineNum])
                    retMaxGold = gold[mineNum];
                else
                    retMaxGold = 0;
            } else if (people >= peopleNeed[mineNum]) {
                //剩余人数大于挖该金矿人数
                int g1 = getMaxGold(people - peopleNeed[mineNum], mineNum - 1) + gold[mineNum];
                int g2 = getMaxGold(people, mineNum - 1);
                retMaxGold = Math.max(g1, g2);
            } else {//不挖mineNum金矿
                retMaxGold = getMaxGold(people, mineNum - 1);
            }
        }
        maxGold[people][mineNum] = retMaxGold;
        return retMaxGold;
    }

    //n金矿数 w工人数 g[]金矿的产量 p[]需要的人数
    public static int getMostGold(int n, int w, int[] g, int[] p) {
        int[] preRusults = new int[w + 1];
        int[] results = new int[w + 1];

        // loop for n of workers
        for (int i = 0; i <= w; i++) {
            if (i < p[0])
                preRusults[i] = 0;
            else
                preRusults[i] = g[0];
        }

        // loop for n of mines
        for (int i = 1; i < n - 1; ++i) {
            // loop for n of workers
            for (int j = 1; j <= w; j++) {
                if (j < p[i])
                    results[j] = preRusults[j];
                else
                    results[j] = Math.max(preRusults[j], preRusults[j - p[i]] + g[i]);
            }
            preRusults = results;
        }
        return results[w];
    }

    public static void main(String[] args) {
        //DigitGold d = new DigitGold();
        //int s = d.getMaxGold(99, 4);
        //System.out.println(s);

        int p[] = {5, 5, 3, 4, 3};
        int g[] = {400, 500, 200, 300, 350};
        System.out.println(getMostGold(5, 10, g, p));
    }
}
