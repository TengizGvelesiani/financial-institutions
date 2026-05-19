package org.example.financial.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountOverviewDto {
    private Long financialInstitutionId;
    private String financialInstitutionName;
    private Long customerId;
    private String customerName;
    private Long accountId;
    private String accountNumber;
    private BigDecimal balance;
    private Long transactionId;
    private BigDecimal transactionAmount;
    private Long loanId;
    private BigDecimal loanPrincipal;
    private Long cardId;
    private String cardNumber;
    private String passportNumber;

    public Long getFinancialInstitutionId() {
        return financialInstitutionId;
    }

    public void setFinancialInstitutionId(Long financialInstitutionId) {
        this.financialInstitutionId = financialInstitutionId;
    }

    public String getFinancialInstitutionName() {
        return financialInstitutionName;
    }

    public void setFinancialInstitutionName(String financialInstitutionName) {
        this.financialInstitutionName = financialInstitutionName;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public BigDecimal getLoanPrincipal() {
        return loanPrincipal;
    }

    public void setLoanPrincipal(BigDecimal loanPrincipal) {
        this.loanPrincipal = loanPrincipal;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }
}
