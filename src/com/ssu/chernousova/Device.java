package com.ssu.chernousova;

public class Device {
    private double timeEnd;

    public Device() {
        timeEnd = 0.0;
    }

    public void executeDemand(double time, Demand demand) {
        timeEnd = time + Main.getExp();
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
