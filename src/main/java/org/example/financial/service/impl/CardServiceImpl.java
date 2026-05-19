package org.example.financial.service.impl;

import org.example.financial.config.RepositoryFactory;
import org.example.financial.model.Card;
import org.example.financial.persistence.CardRepository;
import org.example.financial.service.CardService;

import java.util.List;
import java.util.Optional;

public class CardServiceImpl implements CardService {

    private final CardRepository repository = RepositoryFactory.cardRepository();

    @Override
    public Card create(Card card) {
        return repository.create(card);
    }

    @Override
    public void update(Card card) {
        repository.update(card);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Optional<Card> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Card> getAll() {
        return repository.findAll();
    }
}
