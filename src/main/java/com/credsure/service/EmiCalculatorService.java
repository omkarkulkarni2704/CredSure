package com.credsure.service;

import org.springframework.stereotype.Service;

@Service
public class EmiCalculatorService {

    public double calculateMonthlyEmi(double principal, double annualRatePercent, int months) {
        if (!Double.isFinite(principal) || principal <= 0) {
            throw new IllegalArgumentException("Principal must be greater than zero.");
        }
        if (!Double.isFinite(annualRatePercent) || annualRatePercent < 0 || annualRatePercent > 100) {
            throw new IllegalArgumentException("Annual interest rate must be between 0 and 100.");
        }
        if (months < 1 || months > 600) {
            throw new IllegalArgumentException("Tenure must be between 1 and 600 months.");
        }

        double monthlyRate = annualRatePercent / 1200.0;
        if (monthlyRate == 0) {
            return principal / months;
        }

        double growth = Math.pow(1 + monthlyRate, months);
        return principal * monthlyRate * growth / (growth - 1);
    }
}
