package org.example.financial.service;

import org.example.financial.model.Branch;

import java.util.List;
import java.util.Optional;

public interface BranchService {

    Branch create(Branch branch);

    void update(Branch branch);

    void delete(Long id);

    Optional<Branch> getById(Long id);

    List<Branch> getAll();

    List<Branch> getByFinancialInstitutionId(Long financialInstitutionId);
}
