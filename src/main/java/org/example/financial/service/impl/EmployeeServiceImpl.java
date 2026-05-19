package org.example.financial.service.impl;

import org.example.financial.config.RepositoryFactory;
import org.example.financial.model.Employee;
import org.example.financial.persistence.EmployeeRepository;
import org.example.financial.service.EmployeeService;

import java.util.List;
import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository = RepositoryFactory.employeeRepository();

    @Override
    public Employee create(Employee employee) {
        return repository.create(employee);
    }

    @Override
    public void update(Employee employee) {
        repository.update(employee);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Optional<Employee> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Employee> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Employee> getByFinancialInstitutionId(Long financialInstitutionId) {
        return repository.findByFinancialInstitutionId(financialInstitutionId);
    }
}
