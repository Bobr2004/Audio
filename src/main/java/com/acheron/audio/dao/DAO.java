package com.acheron.audio.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public interface DAO<K,T> {
    List<T> findAll();

    Optional<T> findById(Integer id);

    boolean delete(K id);

    void update(T entity);

    T save(T entity);

    T build(ResultSet resultSet);
}
