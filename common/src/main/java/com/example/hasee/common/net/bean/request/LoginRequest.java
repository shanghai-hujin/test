package com.example.hasee.common.net.bean.request;

import com.example.hasee.common.net.bean.CommomParams;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginRequest extends CommomParams implements Serializable {

    /**
     * username :
     */

    @SerializedName("username")
    private String username;

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

    @SerializedName("password")
    private String password;
}
