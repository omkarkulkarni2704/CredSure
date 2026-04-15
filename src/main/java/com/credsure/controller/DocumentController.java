package com.credsure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class DocumentController {

    @GetMapping("/upload-documents")
    public String uploadPage() {
        return "upload-documents";
    }

    @PostMapping("/upload-documents")
    public String uploadDocuments(
            @RequestParam("aadhaar") MultipartFile aadhaar,
            @RequestParam("pan") MultipartFile pan,
            @RequestParam("salarySlip") MultipartFile salarySlip,
            @RequestParam("bankStatement") MultipartFile bankStatement,
            Model model) {

        model.addAttribute("aadhaarName", aadhaar.getOriginalFilename());
        model.addAttribute("panName", pan.getOriginalFilename());
        model.addAttribute("salarySlipName", salarySlip.getOriginalFilename());
        model.addAttribute("bankStatementName", bankStatement.getOriginalFilename());

        return "upload-documents";
    }
}
