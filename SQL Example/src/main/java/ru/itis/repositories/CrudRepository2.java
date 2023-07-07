package ru.itis.repositories;

import java.util.List;

public interface CrudRepository2 <T> {
    void save(T model);
    List<T> findAll();
}
