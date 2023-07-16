package com.acheron.audio.dao;

import com.acheron.audio.entity.MusicListEntity;
import com.acheron.audio.entity.SessionEntity;
import com.acheron.audio.util.ConnectionUtil;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SessionDAO implements DAO<Integer, SessionEntity> {
    private final String FIND_ALL = """
            select * from rshiynzr.public.session;""";
    private final String SAVE = """
            insert into rshiynzr.public.session(name, password) values(?,?);""";
    @Override
    @SneakyThrows
    public List<SessionEntity> findAll() {
        var open = ConnectionUtil.open();
        var preparedStatement = open.prepareStatement(FIND_ALL);
        var resultSet = preparedStatement.executeQuery();
        List<SessionEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(build(resultSet));
        }
        return list;
    }

    @Override
    public Optional<SessionEntity> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(SessionEntity entity) {

    }

    @Override
    @SneakyThrows
    public SessionEntity save(SessionEntity entity) {
        var open = ConnectionUtil.open();
        var preparedStatement = open.prepareStatement(SAVE);
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setString(2, entity.getPassword());
        preparedStatement.executeUpdate();
        return entity;
    }

    @Override
    @SneakyThrows
    public SessionEntity build(ResultSet resultSet) {
        return new SessionEntity(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("password"));
    }
}
