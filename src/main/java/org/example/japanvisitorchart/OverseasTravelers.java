package org.example.japanvisitorchart;

public class OverseasTravelers {

    private String month;
    private int travelerIn2019;
    private int travelerIn2023;
    private double changeRate;

    public OverseasTravelers(String month, int travelerIn2019, int travelerIn2023, double changeRate) {
        this.month = month;
        this.travelerIn2019 = travelerIn2019;
        this.travelerIn2023 = travelerIn2023;
        this.changeRate = changeRate;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getTravelerIn2019() {
        return travelerIn2019;
    }

    public void setTravelerIn2019(int travelerIn2019) {
        this.travelerIn2019 = travelerIn2019;
    }

    public int getTravelerIn2023() {
        return travelerIn2023;
    }

    public void setTravelerIn2023(int travelerIn2023) {
        this.travelerIn2023 = travelerIn2023;
    }

    public double getChangeRate() {
        return changeRate;
    }

    public void setChangeRate(double changeRate) {
        this.changeRate = changeRate;
    }
}
