package me.baymac;

public class DpChange {

    int money;
    int coins[];

    public DpChange(int money, int[] coins) {
        this.money = money;
        this.coins = coins;
    }

    public int getChangeByDp(int money, int[] coins) {
        int[] minCoins = new int[money + 1];
        for(int m = 1; m <= money; m++) {
            minCoins[m] = Integer.MAX_VALUE;
            for(int i = 0; i < coins.length; i++) {
                if(m >= coins[i]) {
                    int numCoins = minCoins[m - coins[i]] + 1;
                    minCoins[m] = Math.min(minCoins[m], numCoins);
                }
            }
        }
        return minCoins[money];
    }

}
