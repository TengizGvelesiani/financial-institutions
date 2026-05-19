package org.example.financial.controller;

import org.example.financial.config.ServiceFactory;
import org.example.financial.model.Branch;
import org.example.financial.service.BranchService;

import java.util.List;
import java.util.Optional;

public class BranchController {

    private final BranchService service = ServiceFactory.branchService();

    public Branch create(Branch branch) {
        return service.create(branch);
    }

    public void update(Branch branch) {
        service.update(branch);
    }

    public void delete(Long id) {
        service.delete(id);
    }

    public Optional<Branch> getById(Long id) {
        return service.getById(id);
    }

    public List<Branch> getAll() {
        return service.getAll();
    }

    public List<Branch> getByFinancialInstitutionId(Long financialInstitutionId) {
        return service.getByFinancialInstitutionId(financialInstitutionId);
    }
}
