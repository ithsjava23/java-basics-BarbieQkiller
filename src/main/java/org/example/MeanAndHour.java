package org.example;

/**
 * @author Angela Gustafsson, anggus-1
 */
public class MeanAndHour extends Hour {

    float mean;

    public float getMean() {
        return mean;
    }

    public MeanAndHour(float mean, int hour) {
        super(hour);
        this.mean = mean;
    }
}
