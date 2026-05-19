package org.example.financial.persistence;

import org.example.financial.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer> {

    Optional<Customer> findById(Long id);

    List<Customer> findAll();

    List<Customer> findByFinancialInstitutionId(Long financialInstitutionId);
}
