package org.example;

/**
 * @author Angela Gustafsson, anggus-1
 */
public class Database {
    int[] prices;

    public Database(int[] prices) {
        this.prices = prices;
    }

    public PriceAndHour min() {
        int minValue = Integer.MAX_VALUE;
        int minPosition = 0;

        for (int i = 0; i < prices.length; i++) {
            int price = prices[i];
            if (price < minValue) {
                minValue = price;
                minPosition = i;
            }

        }
        return new PriceAndHour(minValue, minPosition);
    }

    public PriceAndHour max() {
        int maxValue = Integer.MIN_VALUE;
        int maxPosition = 0;

        for (int i = 0; i < prices.length; i++) {
            int price = prices[i];
            if (price > maxValue) {
                maxValue = price;
                maxPosition = i;
            }
        }
        return new PriceAndHour(maxValue, maxPosition);
    }

    public float mean() {
        int sum = 0;
        for (int price : prices) {
            sum = price + sum;
        }
        return ((float) sum) / prices.length;
    }

}
