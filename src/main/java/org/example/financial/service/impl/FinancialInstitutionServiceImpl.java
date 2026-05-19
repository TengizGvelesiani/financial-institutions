package org.example.financial.service.impl;

import org.example.financial.config.RepositoryFactory;
import org.example.financial.model.FinancialInstitution;
import org.example.financial.persistence.FinancialInstitutionRepository;
import org.example.financial.service.FinancialInstitutionService;

import java.util.List;
import java.util.Optional;

public class FinancialInstitutionServiceImpl implements FinancialInstitutionService {

    private final FinancialInstitutionRepository repository = RepositoryFactory.financialInstitutionRepository();

    @Override
    public FinancialInstitution create(FinancialInstitution institution) {
        return repository.create(institution);
    }

    @Override
    public void update(FinancialInstitution institution) {
        repository.update(institution);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Optional<FinancialInstitution> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<FinancialInstitution> getAll() {
        return repository.findAll();
    }
}
