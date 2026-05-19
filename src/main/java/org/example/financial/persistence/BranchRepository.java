package org.example.financial.persistence;

import org.example.financial.model.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchRepository extends CrudRepository<Branch> {

    Optional<Branch> findById(Long id);

    List<Branch> findAll();

    List<Branch> findByFinancialInstitutionId(Long financialInstitutionId);
}
