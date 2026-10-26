package com.credsure.service;

import com.credsure.entity.LoanApplication;
import com.credsure.repository.LoanApplicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
public class LoanService {

    private final LoanApplicationRepository repository;

    public LoanService(LoanApplicationRepository repository) {
        this.repository = repository;
    }

    /**
     * Produces a preliminary, demo-only risk score and persists the application.
     * The score is a transparent rules-based estimate, not a bank decision.
     */
    @Transactional
    public LoanApplication processLoan(LoanApplication loan) {
        validate(loan);

        String employment = loan.getEmploymentType().trim().toLowerCase(Locale.ROOT);
        int score = 100;

        if (loan.getSalary() < 25000) {
            score -= 20;
        }

        double emiToIncome = loan.getExistingEmi() / loan.getSalary();
        if (emiToIncome >= 0.50) {
            score -= 35;
        } else if (emiToIncome >= 0.40) {
            score -= 25;
        } else if (emiToIncome >= 0.30) {
            score -= 15;
        }

        if ("self employed".equals(employment)
                || "business".equals(employment)
                || "freelancer".equals(employment)) {
            score -= 10;
        } else if ("temporary employee".equals(employment)) {
            score -= 20;
        }

        if (loan.getAge() < 21 || loan.getAge() > 60) {
            score -= 20;
        }

        if (loan.getLoanAmount() > loan.getSalary() * 24) {
            score -= 25;
        }

        if (loan.getExperienceYears() == 0) {
            score -= 5;
        }

        score = Math.max(0, Math.min(100, score));
        loan.setRiskScore(score);

        // A preliminary score must not silently approve/reject a real application.
        // A permitted admin reviews the case and sets the final workflow status.
        loan.setStatus("UNDER REVIEW");

        return repository.save(loan);
    }

    private void validate(LoanApplication loan) {
        if (loan == null) {
            throw new IllegalArgumentException("Application data is required.");
        }
        requireText(loan.getFullName(), "Full name is required.");
        requireText(loan.getEmail(), "Email address is required.");
        requireText(loan.getMobileNumber(), "Mobile number is required.");
        requireText(loan.getCity(), "City is required.");
        requireText(loan.getEmploymentType(), "Employment type is required.");
        requireText(loan.getLoanType(), "Loan type is required.");
        requireText(loan.getPurpose(), "Loan purpose is required.");

        if (!loan.getEmail().trim().matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            throw new IllegalArgumentException("Enter a valid email address.");
        }
        if (!loan.getMobileNumber().trim().matches("^[0-9]{10}$")) {
            throw new IllegalArgumentException("Enter a 10-digit mobile number.");
        }
        if (loan.getAge() == null || loan.getAge() < 18 || loan.getAge() > 100) {
            throw new IllegalArgumentException("Age must be between 18 and 100.");
        }
        if (loan.getExperienceYears() == null
                || loan.getExperienceYears() < 0
                || loan.getExperienceYears() > 60) {
            throw new IllegalArgumentException("Work experience must be between 0 and 60 years.");
        }
        if (loan.getSalary() == null || !Double.isFinite(loan.getSalary()) || loan.getSalary() <= 0) {
            throw new IllegalArgumentException("Monthly income must be greater than zero.");
        }
        if (loan.getExistingEmi() == null) {
            loan.setExistingEmi(0.0);
        }
        if (!Double.isFinite(loan.getExistingEmi()) || loan.getExistingEmi() < 0) {
            throw new IllegalArgumentException("Existing EMI cannot be negative.");
        }
        if (loan.getLoanAmount() == null
                || !Double.isFinite(loan.getLoanAmount())
                || loan.getLoanAmount() <= 0
                || loan.getLoanAmount() > 100_000_000) {
            throw new IllegalArgumentException("Requested amount must be between ₹1 and ₹10 crore.");
        }
        if (loan.getPurpose().trim().length() > 1000) {
            throw new IllegalArgumentException("Loan purpose must be 1000 characters or fewer.");
        }

        loan.setFullName(loan.getFullName().trim());
        loan.setEmail(loan.getEmail().trim().toLowerCase(Locale.ROOT));
        loan.setMobileNumber(loan.getMobileNumber().trim());
        loan.setCity(loan.getCity().trim());
        loan.setEmploymentType(loan.getEmploymentType().trim());
        loan.setLoanType(loan.getLoanType().trim());
        loan.setPurpose(loan.getPurpose().trim());
    }

    private void requireText(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }
}
