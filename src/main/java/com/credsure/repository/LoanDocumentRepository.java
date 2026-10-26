package com.credsure.repository;

import com.credsure.entity.LoanDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanDocumentRepository extends JpaRepository<LoanDocument, Long> {

    List<LoanDocument> findAllByApplication_IdOrderByUploadedAtDesc(Long applicationId);

    long countByApplication_Id(Long applicationId);
}
