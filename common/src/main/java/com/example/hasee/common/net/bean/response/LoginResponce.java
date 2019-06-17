package com.example.hasee.common.net.bean.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class LoginResponce implements Serializable {

    /**
     * admin : false
     * chapterTops : []
     * collectIds : [2447,7689]
     * email :
     * icon :
     * id : 2070
     * password :
     * token :
     * type : 0
     * username : hujinjava
     */

    @SerializedName("admin")
    private boolean admin;
    @SerializedName("email")
    private String email;
    @SerializedName("icon")
    private String icon;
    @SerializedName("id")
    private int id;
    @SerializedName("password")
    private String password;
    @SerializedName("token")
    private String token;
    @SerializedName("type")
    private int type;
    @SerializedName("username")
    private String username;
    @SerializedName("chapterTops")
    private List<?> chapterTops;
    @SerializedName("collectIds")
    private List<Integer> collectIds;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<?> getChapterTops() {
        return chapterTops;
    }

    public void setChapterTops(List<?> chapterTops) {
        this.chapterTops = chapterTops;
    }

    public List<Integer> getCollectIds() {
        return collectIds;
    }

    public void setCollectIds(List<Integer> collectIds) {
        this.collectIds = collectIds;
    }
}
