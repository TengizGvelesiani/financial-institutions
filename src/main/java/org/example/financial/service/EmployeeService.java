package org.example.financial.service;

import org.example.financial.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee create(Employee employee);

    void update(Employee employee);

    void delete(Long id);

    Optional<Employee> getById(Long id);

    List<Employee> getAll();

    List<Employee> getByFinancialInstitutionId(Long financialInstitutionId);
}
