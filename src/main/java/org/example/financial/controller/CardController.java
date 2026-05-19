package org.example.financial.controller;

import org.example.financial.config.ServiceFactory;
import org.example.financial.model.Card;
import org.example.financial.service.CardService;

import java.util.List;
import java.util.Optional;

public class CardController {

    private final CardService service = ServiceFactory.cardService();

    public Card create(Card card) {
        return service.create(card);
    }

    public void update(Card card) {
        service.update(card);
    }

    public void delete(Long id) {
        service.delete(id);
    }

    public Optional<Card> getById(Long id) {
        return service.getById(id);
    }

    public List<Card> getAll() {
        return service.getAll();
    }
}
