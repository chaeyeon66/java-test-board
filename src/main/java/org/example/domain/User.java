package org.example.domain;

import java.io.Serializable;

public class User implements Serializable {
    User(int userId, String id, String password, String nickname){
        this.userId = userId;
        this.id = id;
        this.password = password;
        this.nickname = nickname;
    }
    User(){

    }

    private int userId;
    private String id;
    private String password;
    private String nickname;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
