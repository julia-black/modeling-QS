package com.ssu.chernousova;

public class Device {
    private double timeEnd;

    public Device() {
        timeEnd = 0.0;
    }

    public static double getExp() {
        double X, lambda = 2;
        X = -(1 / lambda) * Math.log(Math.random());
        return X;
    }


    public void executeDemand(double time, Demand demand) {
        timeEnd = time + getExp();
        demand.setTimeStart(time);
        demand.setTimeEnd(timeEnd);
    }

    public double getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(double timeEnd) {
        this.timeEnd = timeEnd;
    }
}