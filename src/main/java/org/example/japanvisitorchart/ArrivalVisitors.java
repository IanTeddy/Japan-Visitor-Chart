package org.example.japanvisitorchart;

// Arrival visitors in Japan class
public class ArrivalVisitors {

    // instance variables
    private int month;
    private int previousYear;
    private int currentYear;
    private double changeRate;

    // constructor
    public ArrivalVisitors(int month, int previousYear, int currentYear, double changeRate) {
        this.month = month;
        this.previousYear = previousYear;
        this.currentYear = currentYear;
        this.changeRate = changeRate;
    }

    // getter and setter
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getPreviousYear() {
        return previousYear;
    }

    public void setPreviousYear(int previousYear) {
        this.previousYear = previousYear;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    public double getChangeRate() {
        return changeRate;
    }

    public void setChangeRate(double changeRate) {
        this.changeRate = changeRate;
    }
}
