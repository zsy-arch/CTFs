package com.vsf2f.f2f.bean;

import java.io.Serializable;
import java.util.Calendar;

/* loaded from: classes2.dex */
public class MyCalendar implements Serializable {
    private int day;
    private boolean ever;
    private int month;
    private int year;

    public MyCalendar() {
        Calendar calendar = Calendar.getInstance();
        this.year = calendar.get(1);
        this.month = calendar.get(2);
        this.day = calendar.get(5);
    }

    public MyCalendar(String date) {
        try {
            String[] str = date.split("-");
            this.year = Integer.valueOf(str[0]).intValue();
            this.month = Integer.valueOf(str[1]).intValue();
            this.day = Integer.valueOf(str[2]).intValue();
            if (this.year == 9999) {
                this.ever = true;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public MyCalendar(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean isEver() {
        return this.ever;
    }

    public void setEver(boolean ever) {
        if (ever) {
            this.year = 9999;
            this.month = 12;
            this.day = 31;
        }
        this.ever = ever;
    }

    public String toString() {
        return this.year + "-" + this.month + "-" + this.day;
    }

    public String showString() {
        return this.ever ? "长期" : toString();
    }
}
