package com.ssu.chernousova;

public class Demand {

    private double timeIn;
    private double timeStart;
    private double timeEnd;

    public Demand(double timeIn) {
        this.timeIn = timeIn;
    }

    public double getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(double timeIn) {
        this.timeIn = timeIn;
    }

    public Demand(double timeIn, double timeStart, double timeEnd) {
        this.timeIn = timeIn;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public double getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(double timeStart) {
        this.timeStart = timeStart;
    }

    public double getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(double timeEnd) {
        this.timeEnd = timeEnd;
    }

    @Override
    public String toString() {
        return "Demand{" +
                "timeIn=" + timeIn +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                '}';
    }
}
