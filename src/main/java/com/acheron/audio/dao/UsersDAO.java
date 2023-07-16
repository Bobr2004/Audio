package com.acheron.audio.dao;

import com.acheron.audio.entity.UsersEntity;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class UsersDAO implements DAO<Integer, UsersEntity> {
    @Override
    public List<UsersEntity> findAll() {
        return null;
    }

    @Override
    public Optional<UsersEntity> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(UsersEntity entity) {

    }

    @Override
    public UsersEntity save(UsersEntity entity) {
        return null;
    }

    @Override
    public UsersEntity build(ResultSet resultSet) {
        return null;
    }
}
