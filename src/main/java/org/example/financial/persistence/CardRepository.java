package org.example.financial.persistence;

import org.example.financial.model.Card;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends CrudRepository<Card> {

    Optional<Card> findById(Long id);

    List<Card> findAll();
}
