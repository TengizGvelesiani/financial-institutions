package org.example.financial.persistence;

import org.example.financial.model.FinancialInstitution;

import java.util.List;
import java.util.Optional;

public interface FinancialInstitutionRepository extends CrudRepository<FinancialInstitution> {

    Optional<FinancialInstitution> findById(Long id);

    List<FinancialInstitution> findAll();
}
