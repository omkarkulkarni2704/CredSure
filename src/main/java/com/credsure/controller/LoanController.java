package com.credsure.controller;

import com.credsure.entity.BankOffer;
import com.credsure.service.BankOfferService;
import com.credsure.entity.LoanApplication;
import com.credsure.service.LoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoanController {

	private final LoanService loanService;
	private final BankOfferService bankOfferService;

	public LoanController(LoanService loanService, BankOfferService bankOfferService) {
	    this.loanService = loanService;
	    this.bankOfferService = bankOfferService;
	}

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("loanApplication", new LoanApplication());
        return "index";
    }

    @GetMapping("/offers")
    public String offers() {
        return "offers";
    }

    @GetMapping("/emi")
    public String emi() {
        return "emi";
    }

    @GetMapping("/support")
    public String support() {
        return "support";
    }

    @PostMapping("/apply")
    public String applyLoan(@ModelAttribute LoanApplication loanApplication, Model model) {

        LoanApplication saved = loanService.processLoan(loanApplication);
        BankOffer offer = bankOfferService.getOffer(saved.getRiskScore());
        model.addAttribute("offer", offer);

        model.addAttribute("result", saved);

        return "result";
    }
}