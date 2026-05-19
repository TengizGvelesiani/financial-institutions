package org.example.financial.controller;

import org.example.financial.config.ServiceFactory;
import org.example.financial.model.Employee;
import org.example.financial.service.EmployeeService;

import java.util.List;
import java.util.Optional;

public class EmployeeController {

    private final EmployeeService service = ServiceFactory.employeeService();

    public Employee create(Employee employee) {
        return service.create(employee);
    }

    public void update(Employee employee) {
        service.update(employee);
    }

    public void delete(Long id) {
        service.delete(id);
    }

    public Optional<Employee> getById(Long id) {
        return service.getById(id);
    }

    public List<Employee> getAll() {
        return service.getAll();
    }

    public List<Employee> getByFinancialInstitutionId(Long financialInstitutionId) {
        return service.getByFinancialInstitutionId(financialInstitutionId);
    }
}
