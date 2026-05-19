package org.example.financial.service.impl;

import org.example.financial.config.RepositoryFactory;
import org.example.financial.model.Branch;
import org.example.financial.persistence.BranchRepository;
import org.example.financial.service.BranchService;

import java.util.List;
import java.util.Optional;

public class BranchServiceImpl implements BranchService {

    private final BranchRepository repository = RepositoryFactory.branchRepository();

    @Override
    public Branch create(Branch branch) {
        return repository.create(branch);
    }

    @Override
    public void update(Branch branch) {
        repository.update(branch);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Optional<Branch> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Branch> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Branch> getByFinancialInstitutionId(Long financialInstitutionId) {
        return repository.findByFinancialInstitutionId(financialInstitutionId);
    }
}
