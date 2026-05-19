package org.example.financial.controller;

import org.example.financial.config.ServiceFactory;
import org.example.financial.model.Customer;
import org.example.financial.service.CustomerService;

import java.util.List;
import java.util.Optional;

public class CustomerController {

    private final CustomerService service = ServiceFactory.customerService();

    public Customer create(Customer customer) {
        return service.create(customer);
    }

    public void update(Customer customer) {
        service.update(customer);
    }

    public void delete(Long id) {
        service.delete(id);
    }

    public Optional<Customer> getById(Long id) {
        return service.getById(id);
    }

    public List<Customer> getAll() {
        return service.getAll();
    }

    public List<Customer> getByFinancialInstitutionId(Long financialInstitutionId) {
        return service.getByFinancialInstitutionId(financialInstitutionId);
    }
}
