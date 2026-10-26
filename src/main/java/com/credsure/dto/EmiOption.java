package com.credsure.dto;

public class EmiOption {

    private final int months;
    private final double monthlyEmi;

    public EmiOption(int months, double monthlyEmi) {
        this.months = months;
        this.monthlyEmi = monthlyEmi;
    }

    public int getMonths() {
        return months;
    }

    public double getMonthlyEmi() {
        return monthlyEmi;
    }
}
