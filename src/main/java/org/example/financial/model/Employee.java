package org.example.financial.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Employee {
    private Long id;
    private Long financialInstitutionId;
    private String fullName;
    private LocalDate hireDate;
    private BigDecimal salary;
    private Passport passport;
    private List<Task> tasks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFinancialInstitutionId() {
        return financialInstitutionId;
    }

    public void setFinancialInstitutionId(Long financialInstitutionId) {
        this.financialInstitutionId = financialInstitutionId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
