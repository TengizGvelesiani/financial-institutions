package org.example.financial.persistence;

import org.example.financial.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee> {

    Optional<Employee> findById(Long id);

    List<Employee> findAll();

    List<Employee> findByFinancialInstitutionId(Long financialInstitutionId);
}
