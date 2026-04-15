package com.credsure.entity;

import jakarta.persistence.*;

@Entity
public class BankOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bankName;
    private Double interestRate;
    private Double maxLoan;
    private Integer minRiskScore;

    public Long getId() {
        return id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Double getMaxLoan() {
        return maxLoan;
    }

    public void setMaxLoan(Double maxLoan) {
        this.maxLoan = maxLoan;
    }

    public Integer getMinRiskScore() {
        return minRiskScore;
    }

    public void setMinRiskScore(Integer minRiskScore) {
        this.minRiskScore = minRiskScore;
    }
}