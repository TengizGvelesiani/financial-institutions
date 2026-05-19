package org.example.financial.service;

import org.example.financial.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer create(Customer customer);

    void update(Customer customer);

    void delete(Long id);

    Optional<Customer> getById(Long id);

    List<Customer> getAll();

    List<Customer> getByFinancialInstitutionId(Long financialInstitutionId);
}
