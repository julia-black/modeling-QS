package com.ssu.chernousova;

public class Pair {
   private double time;
   private int countDemands;

    public Pair(double time, int countDemands) {
        this.time = time;
        this.countDemands = countDemands;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getCountDemands() {
        return countDemands;
    }

    public void setCountDemands(int countDemands) {
        this.countDemands = countDemands;
    }
}
