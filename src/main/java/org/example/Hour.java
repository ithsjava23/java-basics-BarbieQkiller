package org.example;

/**
 * @author Angela Gustafsson, anggus-1
 */
public abstract class Hour {
    protected final int hour;

    public int getHour() {
        return hour;
    }

    public Hour(int hour) {
        this.hour = hour;
    }
}
