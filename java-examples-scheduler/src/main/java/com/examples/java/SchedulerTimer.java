package com.examples.java;

import java.util.Date;
import java.util.TimerTask;

public class SchedulerTimer extends TimerTask {
    private String taskName;

    public SchedulerTimer(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " " + taskName + " executed " + new Date());
    }

}