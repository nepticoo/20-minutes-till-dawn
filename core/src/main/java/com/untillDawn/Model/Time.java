package com.untillDawn.Model;

public class Time {
    private int seconds;

    public Time(int seconds) {
        this.seconds = seconds;
    }

    public Time() {
        seconds = 0;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getMinutes() {
        return seconds / 60;
    }

    public int getSecondsMod() {
        return seconds % 60;
    }

    @Override
    public String toString() {
        return getMinutes() + ":" + getSeconds();
    }

    public static Time Max(Time a, Time b) {
        if(a.getSeconds() > b.getSeconds()) {
            return a;
        }
        return b;
    }
}
