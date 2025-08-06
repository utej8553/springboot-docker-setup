package org.example.springbootauthjwt.Entity;

import lombok.Data;

@Data
public class UserEntity {
    private String username;
    private String password;

    public UserEntity(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
