package com.credsure.repository;

import com.credsure.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {

    List<LoanApplication> findAllByApplicant_IdOrderByIdDesc(Long applicantId);

    List<LoanApplication> findTop5ByApplicant_IdOrderByIdDesc(Long applicantId);

    Optional<LoanApplication> findByIdAndApplicant_Id(Long id, Long applicantId);
}
