package org.example.financial.controller;

import org.example.financial.config.ServiceFactory;
import org.example.financial.model.FinancialInstitution;
import org.example.financial.service.FinancialInstitutionService;

import java.util.List;
import java.util.Optional;

public class FinancialInstitutionController {

    private final FinancialInstitutionService service = ServiceFactory.financialInstitutionService();

    public FinancialInstitution create(FinancialInstitution institution) {
        return service.create(institution);
    }

    public void update(FinancialInstitution institution) {
        service.update(institution);
    }

    public void delete(Long id) {
        service.delete(id);
    }

    public Optional<FinancialInstitution> getById(Long id) {
        return service.getById(id);
    }

    public List<FinancialInstitution> getAll() {
        return service.getAll();
    }
}
