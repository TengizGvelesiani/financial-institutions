package org.example.financial.service;

import org.example.financial.model.Card;

import java.util.List;
import java.util.Optional;

public interface CardService {

    Card create(Card card);

    void update(Card card);

    void delete(Long id);

    Optional<Card> getById(Long id);

    List<Card> getAll();
}
