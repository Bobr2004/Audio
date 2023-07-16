package com.acheron.audio.entity;

import lombok.Value;

@Value
public class SessionEntity {
    Integer id;
    String name;
    String password;

    public SessionEntity(String name, String password) {
        this.id=null;
        this.name = name;
        this.password = password;
    }

    public SessionEntity(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
