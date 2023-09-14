package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public List<PriceAndHour> sortedList() {
        List<PriceAndHour> priceAndHours = new ArrayList<>();
        for (int i = 0; i < prices.length; i++) {
            priceAndHours.add(new PriceAndHour(prices[i], i));
        }
        Collections.sort(priceAndHours);
        return priceAndHours;
    }

    public float mean() {
        int sum = 0;
        for (int price : prices) {
            sum = price + sum;
        }
        return ((float) sum) / prices.length;
    }

    public MeanAndHour optimizedCharging() {
        int hour = 0;
        int cheapestFourHours = Integer.MAX_VALUE;
        for (int i = 0; i < (prices.length - 3); i++) {
            int sum = prices[i] + prices[i + 1] + prices[i + 2] + prices[i + 3];
            if (sum < cheapestFourHours) {
                cheapestFourHours = sum;
                hour = i;
            }
        }
        return new MeanAndHour(((float) cheapestFourHours) / 4, hour);
    }
}