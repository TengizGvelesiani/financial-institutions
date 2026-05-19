package org.example.financial.model;

public class Branch {
    private Long id;
    private Long financialInstitutionId;
    private String name;
    private String address;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
