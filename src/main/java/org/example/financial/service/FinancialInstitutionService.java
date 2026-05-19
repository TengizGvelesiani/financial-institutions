package org.example.financial.service;

import org.example.financial.model.FinancialInstitution;

import java.util.List;
import java.util.Optional;

public interface FinancialInstitutionService {

    FinancialInstitution create(FinancialInstitution institution);

    void update(FinancialInstitution institution);

    void delete(Long id);

    Optional<FinancialInstitution> getById(Long id);

    List<FinancialInstitution> getAll();
}
