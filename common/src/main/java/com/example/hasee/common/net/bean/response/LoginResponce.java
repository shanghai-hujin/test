package com.example.hasee.common.net.bean.response;

import com.example.hasee.common.db.propertyconverter.IntegerConverter;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import java.util.List;

@Entity
public class LoginResponce implements Serializable {

    @Id
    private Long id;

    private static final long serialVersionUID = 1994L;
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
    @SerializedName("password")
    private String password;
    @SerializedName("token")
    private String token;
    @SerializedName("type")
    private int type;
    @SerializedName("username")
    private String username;
    //    @SerializedName("chapterTops")
//    private List<?> chapterTops;

    @Convert(columnType = String.class, converter = IntegerConverter.class)
    @SerializedName("collectIds")
    private List<Integer> collectIds;



    @Generated(hash = 1575517983)
    public LoginResponce(Long id, boolean admin, String email, String icon,
            String password, String token, int type, String username,
            List<Integer> collectIds) {
        this.id = id;
        this.admin = admin;
        this.email = email;
        this.icon = icon;
        this.password = password;
        this.token = token;
        this.type = type;
        this.username = username;
        this.collectIds = collectIds;
    }

    @Generated(hash = 352940109)
    public LoginResponce() {
    }



    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getAdmin() {
        return this.admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Integer> getCollectIds() {
        return this.collectIds;
    }

    public void setCollectIds(List<Integer> collectIds) {
        this.collectIds = collectIds;
    }

}
