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

    @Override
    public String toString() {
        StringBuilder visualisering = new StringBuilder();
        int max = max().price;
        int min = min().price;
        float delta = (max - min) / 5.0f;
        int[] takesCareOfTheDecimalIssue = new int[6];
        takesCareOfTheDecimalIssue[0] = max;
        takesCareOfTheDecimalIssue[1] = (int) (max - delta);
        takesCareOfTheDecimalIssue[2] = (int) (max - 2 * delta);
        takesCareOfTheDecimalIssue[3] = (int) (max - 3 * delta);
        takesCareOfTheDecimalIssue[4] = (int) (max - 4 * delta);
        takesCareOfTheDecimalIssue[5] = min;

        for (int price : takesCareOfTheDecimalIssue) {
            if (price == max || price == min) {
                visualisering.append(String.format("%3d|", price));

            } else {
                visualisering.append("   |");

            }
            for (int hour = 0; hour < prices.length; hour++) {
                if (price > prices[hour]) {
                    visualisering.append("   ");
                } else {
                    visualisering.append("  x");
                }
            }
            visualisering.append("\n");

        }
        visualisering.append("   |------------------------------------------------------------------------\n");
        visualisering.append("   |");
        for (int hour = 0; hour < prices.length; hour++) {
            visualisering.append(String.format(" %02d", hour));
        }
        visualisering.append("\n");
        return visualisering.toString();
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