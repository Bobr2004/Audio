package com.acheron.audio.dao;

import com.acheron.audio.entity.MusicEntity;
import com.acheron.audio.entity.MusicListEntity;
import com.acheron.audio.util.ConnectionUtil;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MusicListDAO implements DAO<Integer, MusicListEntity> {
    private final String FIND_ALL = """
            select * from rshiynzr.public.music_list;""";
    private final String FIND_BY_ID = """
            select * from rshiynzr.public.music_list where id=?;""";
    private final String SAVE = """
            insert into rshiynzr.public.music_list(id,id_session) values(?,?) ;""";
    private final String DELETE = """
            delete FROM rshiynzr.public.music_list where id=?;""";

    @Override
    @SneakyThrows
    public List<MusicListEntity> findAll() {
        var open = ConnectionUtil.open();
        var preparedStatement = open.prepareStatement(FIND_ALL);
        var resultSet = preparedStatement.executeQuery();
        List<MusicListEntity> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(build(resultSet));
        }
        return list;
    }

    @Override
    @SneakyThrows
    public Optional<MusicListEntity> findById(Integer id) {
        var open = ConnectionUtil.open();
        var preparedStatement = open.prepareStatement(FIND_BY_ID);
        preparedStatement.setInt(1, id);
        var resultSet = preparedStatement.executeQuery();
        resultSet.next();
        MusicListEntity musicEntity = build(resultSet);
        return Optional.ofNullable(musicEntity);
    }

    @Override
    @SneakyThrows
    public boolean delete(Integer id) {
        var open = ConnectionUtil.open();
        var preparedStatement = open.prepareStatement(DELETE);
        preparedStatement.setInt(1, id);

        return preparedStatement.execute();
    }

    @Override
    public void update(MusicListEntity entity) {

    }

    @Override
    @SneakyThrows
    public MusicListEntity save(MusicListEntity entity) {

            var open = ConnectionUtil.open();
            var preparedStatement = open.prepareStatement(SAVE);
            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setInt(2, entity.getId_session());

            preparedStatement.executeUpdate();
            return entity;
        }

        @Override
        @SneakyThrows
        public MusicListEntity build (ResultSet resultSet){
            return new MusicListEntity(resultSet.getInt("id"),resultSet.getInt("id_session"));
        }
    }

