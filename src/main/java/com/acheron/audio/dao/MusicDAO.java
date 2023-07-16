package com.acheron.audio.dao;

import com.acheron.audio.entity.MusicEntity;
import com.acheron.audio.util.ConnectionUtil;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MusicDAO implements DAO<Integer, MusicEntity>{
    private final String FIND_ALL = """
            select * from rshiynzr.public.music;""";
    private final String FIND_BY_ID = """
            select * from rshiynzr.public.music where id=?;""";
    private final String SAVE = """
            insert into rshiynzr.public.music(id,name,id_user,url,id_music_list) values(?,?,?,?,?) ;""";
    private final String DELETE = """
            delete FROM rshiynzr.public.music where id=?;""";

    @Override
    @SneakyThrows
    public List<MusicEntity> findAll() {
        var open = ConnectionUtil.open();
        var preparedStatement = open.prepareStatement(FIND_ALL);
        var resultSet = preparedStatement.executeQuery();
        List<MusicEntity> list =new ArrayList<>();
        while (resultSet.next()){
            list.add(build(resultSet));
        }
        return list;
    }

    @Override
    @SneakyThrows
    public Optional<MusicEntity> findById(Integer id) {
        var open = ConnectionUtil.open();
        var preparedStatement = open.prepareStatement(FIND_BY_ID);
        preparedStatement.setInt(1,id);
        var resultSet = preparedStatement.executeQuery();
        resultSet.next();
        MusicEntity musicEntity=build(resultSet);
        return Optional.ofNullable(musicEntity);
    }

    @Override
    @SneakyThrows
    public boolean delete(Integer id) {
        var open = ConnectionUtil.open();
        var preparedStatement = open.prepareStatement(DELETE);
        preparedStatement.setInt(1,id);

        return preparedStatement.execute();
    }

    @Override
    public void update(MusicEntity entity) {

    }

    @Override
    @SneakyThrows
    public MusicEntity save(MusicEntity entity) {
        var open = ConnectionUtil.open();
        var preparedStatement = open.prepareStatement(SAVE);
        preparedStatement.setInt(1,entity.getId());
        preparedStatement.setString(2,entity.getName());
        preparedStatement.setInt(3,entity.getIdUser());
        preparedStatement.setString(4,entity.getURL());
        preparedStatement.setInt(5,entity.getIdMusicList());
        preparedStatement.executeUpdate();
        return entity;
    }
    @Override
    @SneakyThrows
    public MusicEntity build(ResultSet resultSet) {
        return new MusicEntity(resultSet.getInt("id"),resultSet.getString("name")
                ,resultSet.getInt("id_user"),resultSet.getString("url")
                ,resultSet.getInt("id_music_list"));
    }
}
