package org.example.financial.persistence;

public interface CrudRepository<T> {

    T create(T entity);

    void update(T entity);

    void delete(Long id);
}
