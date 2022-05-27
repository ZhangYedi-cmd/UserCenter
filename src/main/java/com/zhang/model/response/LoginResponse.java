package com.zhang.model.response;

import com.zhang.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;

    private User user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getSafeUser() {
        return user;
    }

    public void setSafeUser(User user) {
        user.setSafeUser();
        this.user = user;
    }
}
