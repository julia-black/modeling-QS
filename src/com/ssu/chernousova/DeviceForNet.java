package com.ssu.chernousova;

public class DeviceForNet {
    public double timeEnd;
    public int number;
    public DemandForNet demandForNet;

    public DeviceForNet(int number) {
        timeEnd = 0.0;
        this.number = number;
    }

    public void executeDemand(double TIME, DemandForNet demandForNet) {

        timeEnd = TIME + getExp();
        this.demandForNet = demandForNet;
        switch (number) {
            case 1:
                demandForNet.timeStartOne = TIME;
                demandForNet.timeEndOne = timeEnd;
                break;
            case 2:
                demandForNet.timeStartTwo = TIME;
                demandForNet.timeEndTwo = timeEnd;
                break;
            case 3:
                demandForNet.timeStartThree = TIME;
                demandForNet.timeEndThree = timeEnd;
                break;
        }
    }

    public static double getExp() {
        double X, lambda = 0.5;
        X = -(1 / lambda) * Math.log(Math.random());
        return X;
    }

    public double getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(double timeEnd) {
        this.timeEnd = timeEnd;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public DemandForNet getDemandForNet() {
        return demandForNet;
    }

    public void setDemandForNet(DemandForNet demandForNet) {
        this.demandForNet = demandForNet;
    }
}
