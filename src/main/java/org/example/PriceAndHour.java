package org.example;

import java.util.Comparator;

/**
 * @author Angela Gustafsson, anggus-1
 */
public class PriceAndHour implements Comparable<PriceAndHour> {
    int price;
    int hour;

    public PriceAndHour(int price, int hour) {
        this.price = price;
        this.hour = hour;
    }

    public int getPrice() {
        return price;
    }

    public int getHour() {
        return hour;
    }

    @Override
    public int compareTo(PriceAndHour o) {
        return Comparator.comparingInt(PriceAndHour::getPrice).compare(o, this);
    }

    @Override
    public String toString() {
        return String.format("%02d-%02d %d öre", hour, hour + 1, price);
    }
    public String toStringWithComma() {
        return String.format("%02d-%02d, %d öre", hour, hour + 1, price);
    }

}
