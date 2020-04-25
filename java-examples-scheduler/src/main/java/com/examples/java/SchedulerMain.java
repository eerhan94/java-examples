package com.examples.java;

import java.util.Timer;

public class SchedulerMain {

    public static void main(String[] args) {
        SchedulerTimer task1 = new SchedulerTimer("Task1");
        SchedulerTimer task2 = new SchedulerTimer("Task2");
        Timer t = new Timer();
        t.scheduleAtFixedRate(task1, 0, 1000);
        t.scheduleAtFixedRate(task2, 0, 2000);
    }

}
