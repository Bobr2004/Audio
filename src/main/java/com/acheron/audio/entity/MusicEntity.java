package com.acheron.audio.entity;

import lombok.Data;
import lombok.Value;

import java.sql.ResultSet;

@Value
public class MusicEntity {
    Integer id;
    String name;
    Integer idUser;
    String URL;
    Integer idMusicList;
}
