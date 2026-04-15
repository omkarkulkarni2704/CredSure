package com.credsure.service;

import com.credsure.entity.BankOffer;
import org.springframework.stereotype.Service;

@Service
public class BankOfferService {

    public BankOffer getOffer(int riskScore) {

        BankOffer offer = new BankOffer();

        if (riskScore >= 80) {
            offer.setBankName("HDFC Bank");
            offer.setInterestRate(8.5);
            offer.setMaxLoan(2000000.0);
        }
        else if (riskScore >= 60) {
            offer.setBankName("ICICI Bank");
            offer.setInterestRate(9.0);
            offer.setMaxLoan(1500000.0);
        }
        else {
            offer.setBankName("SBI");
            offer.setInterestRate(10.5);
            offer.setMaxLoan(1000000.0);
        }

        return offer;
    }
}
