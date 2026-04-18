# 💳 CredSure – Smart Loan Origination Platform

### **CredSure helps users apply for different types of loans by analyzing their personal, employment, and financial information. The platform calculates a risk score, determines loan approval status, recommends the best bank offer, and allows users to upload documents and track their application progress.**

---

## 🎥 Project Demo

[Watch Demo Video](https://drive.google.com/file/d/1hWx5PHEp13hV82X3eeEPhT5djProug3P/view?usp=drive_link)

---


## 🌟 Key Features

### 👤 For Loan Applicants:

* **Multi-Step Loan Application Form**: Enter personal, employment, and loan details in separate sections.
* **Dynamic Risk Assessment**: Calculates a financial risk score based on salary, age, employment type, EMI, and requested loan amount.
* **Loan Approval Prediction**: Displays whether the loan is Approved, Under Review, or Rejected.
* **Bank Recommendation Engine**: Suggests the most suitable bank and interest rate based on the applicant’s risk profile.
* **EMI Estimation**: Shows estimated monthly EMI for 12, 24, 36, and 60 months.
* **Document Upload Module**: Upload Aadhaar, PAN, salary slip, and bank statement.
* **Application Progress Tracking**: Displays every stage of the application process.

### 🏦 For Banks / Admin:

* **Risk-Based Loan Review**: Evaluate users based on generated risk score.
* **Dynamic Bank Offers**: Different banks are recommended depending on the user's profile.
* **Application Monitoring**: View and verify uploaded documents and loan details.
* **Approval Workflow**: Applications move from submission to review and final approval.

---

## 🛠️ Technologies Used

### 1. Backend:

* **Java 25**: Core business logic and risk calculation engine.
* **Spring Boot**: Application framework and backend server.
* **Spring MVC**: Handles routing and request processing.
* **Spring Data JPA**: Database ORM for storing loan applications and bank data.
* **Maven**: Dependency management and project build tool.

### 2. Frontend:

* **HTML, CSS, Thymeleaf**: Dynamic multi-page UI and server-side rendering.
* **JavaScript**: Used for section progress tracking and form validation.

### 3. Database:

* **MySQL**: Stores loan applications, bank offers, and applicant details.

---

## 🚀 Setup and Run

### 1. Database Setup:

* Install MySQL.
* Create a database named:

```sql
CREATE DATABASE credsure;
```

### 2. Configure Application:

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/credsure
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Run the Project:

Use your IDE or run:

```bash
mvn spring-boot:run
```

Then open:

```text
http://localhost:8080/
```

---

## 🧭 System Architecture

```text
                   ┌───────────────────────────────┐
                   │         End Users             │
                   │   Loan Applicants / Banks     │
                   │       Web Browser             │
                   └───────────────┬───────────────┘
                                   │
                         HTTP Requests / Forms
                                   │
                   ┌───────────────▼────────────────┐
                   │       Web Server Layer         │
                   │      (Spring Boot Backend)     │
                   ├────────────────────────────────┤
                   │ Controllers                    │
                   │ - LoanController               │
                   │ - DocumentController           │
                   │                                │
                   │ Services                       │
                   │ - LoanService                  │
                   │ - BankOfferService             │
                   │ - Risk Calculation Engine      │
                   └───────────────┬────────────────┘
                                   │
                     Hibernate ORM / JDBC Connection
                                   │
                   ┌───────────────▼────────────────┐
                   │         MySQL Database         │
                   │                                │
                   │ Stores:                        │
                   │ - Loan Applications            │
                   │ - Risk Scores                  │
                   │ - Bank Offers                  │
                   │ - Uploaded Documents           │
                   └───────────────┬────────────────┘
                                   │
                     Dynamic Recommendation Engine
                                   │
                   ┌───────────────▼────────────────┐
                   │     Bank Recommendation        │
                   │ - HDFC                         │
                   │ - ICICI                        │
                   │ - SBI                          │
                   │ Based on Risk Score            │
                   └────────────────────────────────┘
```

---

## 📋 Application Workflow

```text
1. User enters personal details
2. User saves employment details
3. User enters loan details
4. System calculates risk score
5. Loan status is generated
6. Best bank offer is displayed
7. User uploads required documents
8. Application moves for final approval
```

---

## 📝 Current Features Implemented

* Multi-step loan application form
* Personal, employment, and loan sections
* Dynamic progress tracker with save buttons
* Risk score generation
* Approved / Under Review / Rejected status
* Dynamic bank recommendation
* EMI calculation table
* Document upload page
* Application status tracking

---

## 📷 Screenshots

* Smart Loan Application Dashboard
* Risk Analysis Result Page
* Bank Offer Recommendation Page
* Document Upload Page

---

## 🤝 Contributing

Contributions are welcome.

* Fork the repository
* Create a new branch
* Make your changes
* Submit a pull request

---

## 💬 Contact
* Developer: [Omkar Kulkarni](https://www.linkedin.com/in/omkarkulkarni-dev/)
  
---
