package com.credsure.service;

import com.credsure.entity.LoanApplication;
import com.credsure.repository.LoanApplicationRepository;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

    private final LoanApplicationRepository repository;

    public LoanService(LoanApplicationRepository repository) {
        this.repository = repository;
    }

    public LoanApplication processLoan(LoanApplication loan) {

        int riskScore = 100;

        if (loan.getSalary() < 25000) {
            riskScore -= 25;
        }

        if (loan.getExistingEmi() > loan.getSalary() * 0.4) {
            riskScore -= 30;
        }

        if (loan.getEmploymentType().equalsIgnoreCase("Temporary Employee")) {
            riskScore -= 20;
        }

        if (loan.getEmploymentType().equalsIgnoreCase("Self Employed")) {
            riskScore -= 10;
        }

        if (loan.getAge() < 21 || loan.getAge() > 60) {
            riskScore -= 50;
        }

        if (loan.getLoanAmount() > loan.getSalary() * 20) {
            riskScore -= 30;
        }

        loan.setRiskScore(riskScore);

        if (riskScore >= 80) {
            loan.setStatus("APPROVED");
        } else if (riskScore >= 50) {
            loan.setStatus("UNDER REVIEW");
        } else {
            loan.setStatus("REJECTED");
        }

        return repository.save(loan);
    }
}