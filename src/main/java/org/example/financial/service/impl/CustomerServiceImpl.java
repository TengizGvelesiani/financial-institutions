package org.example.financial.service.impl;

import org.example.financial.config.RepositoryFactory;
import org.example.financial.model.Customer;
import org.example.financial.persistence.CustomerRepository;
import org.example.financial.service.CustomerService;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository = RepositoryFactory.customerRepository();

    @Override
    public Customer create(Customer customer) {
        return repository.create(customer);
    }

    @Override
    public void update(Customer customer) {
        repository.update(customer);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Optional<Customer> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Customer> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Customer> getByFinancialInstitutionId(Long financialInstitutionId) {
        return repository.findByFinancialInstitutionId(financialInstitutionId);
    }
}
