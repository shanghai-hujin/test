package com.example.hasee.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/2 11:38
 */

public class LoginResponse extends BaseResponce {
    private LoginData data;

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
        this.data = data;
    }

    class LoginData implements Serializable {

        private String username;
        private String password;
        private String icon;
        private int type;
        private List<Integer> collectIds;

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

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<Integer> getCollectIds() {
            return collectIds;
        }

        public void setCollectIds(List<Integer> collectIds) {
            this.collectIds = collectIds;
        }
    }

}
