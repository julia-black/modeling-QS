package com.ssu.chernousova;

import java.util.*;

public class Main {

    public static final int N = 1000;
    private static final double p = 0.5;

    private static void modeling_2_1_12() {
        int countWin1 = 0;
        int countWin2 = 0;
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                arrayList.add(random.nextInt(6) + 1);
            }
            if (arrayList.contains(6)) {
                countWin1++;
            } else {
                countWin2++;
            }
        }

        System.out.println("Первый игрок выиграл " + countWin1 + " раз. " + "Второй игрок выиграл " + countWin2 + " раз");
        if (countWin1 > countWin2) {
            System.out.println("Чаще выигрывает первый игрок");
        } else {
            System.out.println("Чаще выигрывает второй игрок");
        }
        System.out.println("Вероятность выигрыша первого игрока = " + (double) countWin1 / N);
    }

    private static double getNumberOfNormalDist() {
        double M = 100;
        double sigma = 10;
        double X = 0;
        double sum = 0;
        for (int i = 1; i < 13; i++) {
            double r = Math.random();
            sum += r;
        }
        X = M + sigma * (sum - 6);
        return X;
    }

    private static void modeling_2_2_12() {
        ArrayList<Double> arrayList1 = new ArrayList<>();
        ArrayList<Double> arrayList2 = new ArrayList<>();
        ArrayList<Double> arrayListResult = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < N; i++) {
            arrayList1.add(getNumberOfNormalDist());
            arrayList2.add(getNumberOfNormalDist());
            arrayListResult.add(arrayList1.get(i) + arrayList2.get(i));
            sum += arrayListResult.get(i);
        }
        System.out.println("N = " + N + " Сумма = " + sum);
        System.out.println("Мат.ожидание = " + (double) sum / N);

    }

    private static void modeling_2_3_12() {
        /*В начальный момент времени частица расположена в точке 0
        вещественной оси. Частица мгновенно перемещается по оси вправо или влево
        с равными вероятностями на расстояния, которые являются экспоненциально
        распределенными случайными величинами с равными параметрами. Построить
        модель перемещения частицы. На основании 1000 полученных координат час-
                тицы оценить математическое ожидание и среднее квадратическое отклонение
        местоположения частицы.*/

        double lambda = 0.5;
        double X;
        double sum = 0;
        double s, m;

        ArrayList<Double> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            X = -(1 / lambda) * Math.log(Math.random());
            double temp = Math.random();
            if (temp > 0.5) {
                sum += X;
                list.add(X);
            } else {
                sum -= X;
                list.add(-X);
            }
            // System.out.println(X);
        }
        m = sum / N;

        double tempSum = 0;

        for (int i = 1; i <= N; i++) {
            tempSum += (list.get(i - 1) - m) * (list.get(i - 1) - m);
        }
        s = 1.0 / (N - 1) * tempSum;

        System.out.println("Мат.ожидание = " + m);
        System.out.println("Ср. квадр. отклонение = " + Math.pow(s, 1.0 / 2.0));
    }

    public static double getExp() {
        double X, l = 0.5;
        X = -(1 / l) * Math.log(Math.random());
        return X;
    }

    private static void modeling_3_1_12() {
        /* Дана СМО типа M | M |1. После обслуживания, требование с заданной вероятностью p возвращается в систему обслуживания, а с вероят-
        ностью 1− p покидает эту систему обслуживания. Построить имитационную модель системы. На основании 1000 выборочных значений оценить u и n .*/

        /*M(пуассоновский поток требований)|M(последовательность независимых, одинаково распределенных экспоненциально длительностей обслуживания на каждом приборе)
        |1(число обслуживающих приборов)|беск.(макс.длина очереди)
        |беск(число источников требований) */

        //u - математическое ожидание длительности пребывания требований в системе обслуживания;
        //n - математическое ожидание числа требований в системе обслуживания.

        //первый пришел – первым обслужен

        double time = 0.0;
        Queue<Demand> queue = new ArrayDeque<>();

        ArrayList<Demand> demands = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            double newTime = getExp();
            demands.add(new Demand(time + newTime));
            time += newTime;
        }

        System.out.println(demands);

        time = 0.0;
        Device device = new Device();

        ArrayList<Double> tempTime = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            tempTime.add(i, 0.0);
        }

        int idx = 0;
        while (idx < demands.size()) {
           // System.out.println("Time: " + time + "\n");

            //Если устройство не занято
            if (time == device.getTimeEnd()) {
                if (queue.size() == 0) {
                    tempTime.set(0, tempTime.get(0) + demands.get(idx).getTimeIn() - time);
                    time = demands.get(idx).getTimeIn();
                    device.executeDemand(time, demands.get(idx));

                  //  System.out.println("Executing in device");
                  //  System.out.println("timeIn: " + demands.get(idx).getTimeIn());
                  //  System.out.println("timeStart: " + demands.get(idx).getTimeStart());
                  //  System.out.println("timeEnd: " + device.getTimeEnd());

                    if(Math.random() > p) {
                        queue.add(demands.get(idx));
                    }

                    idx++;
                } else {
                   // System.out.println("Executing in device of queue");
                   // System.out.println("timeIn: " + queue.peek().getTimeIn());

                    Demand demand = queue.poll();
                    device.executeDemand(time, demand);

                    if(Math.random() > p) {
                        queue.add(demand);
                    }

                   // System.out.println("timeEnd: " + device.getTimeEnd());
                }
            } else {
                //если уст-во занято
                queue.add(demands.get(idx));
               // System.out.println("Adding in queue");
               // System.out.println("timeIn: " + demands.get(idx).getTimeIn());
                idx++;
            }

            if (idx < demands.size()) {
                //Если время окончания работы у устройства меньше, чем время вхождения текущего требования или верхнего в очереди,то мы можем его поместить в уст-во
                if (device.getTimeEnd() <= demands.get(idx).getTimeIn() || (queue.size() != 0 && device.getTimeEnd() <= queue.peek().getTimeIn())) {

                    //запоминаем время ожидания перед тем как требование попало в уст-во
                    tempTime.set(queue.size(), tempTime.get(queue.size()) + device.getTimeEnd() - time);
                    time = device.getTimeEnd();

                } else {
                    tempTime.set(queue.size(), queue.size() + demands.get(idx).getTimeIn() - time);
                    time = demands.get(idx).getTimeIn();
                }
            } else {
                while (queue.size() != 0) {
                    tempTime.set(queue.size(), tempTime.get(queue.size()) + device.getTimeEnd() - time);
                    time = device.getTimeEnd();
                    //System.out.println("Executing in device of queue " + queue.peek().getTimeIn());
                    device.executeDemand(time, queue.poll());
                   // System.out.println("timeEnd: " + device.getTimeEnd());
                }
            }

           // System.out.println(tempTime.toString());
           // System.out.println("\n____");
        }

       // System.out.println(demands);
        double u = 0.0, n = 0.0;
        for (int i = 0; i < N; i++) {
            u += demands.get(i).getTimeEnd() - demands.get(i).getTimeIn();
            n += (i * tempTime.get(i));
        }
        System.out.println("u = " + (u / (N * 1.0)));
        System.out.println("n = " + n / time);
    }

    public static void main(String[] args) {
        // modeling_2_1_12();
        // modeling_2_2_12();
        //modeling_2_3_12();
        modeling_3_1_12();
    }
}
