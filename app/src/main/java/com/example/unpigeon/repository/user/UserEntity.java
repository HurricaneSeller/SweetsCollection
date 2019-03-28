package com.example.unpigeon.repository.user;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserEntity {
    @PrimaryKey
    private int uid;

    @ColumnInfo(name = "user_name")
    private String name;

    @ColumnInfo(name = "user_password")
    private String password;

    @ColumnInfo(name = "user_email")
    private String email;

    @ColumnInfo(name = "user_tel")
    private String telephone;

    @ColumnInfo(name = "user_login")
    private boolean isLogin;

    @ColumnInfo(name = "token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public UserEntity(String name, String password, String email, String telephone, boolean isLogin) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.telephone = telephone;
        this.isLogin = isLogin;
    }

    public static class Builder {
        private String name;
        private String password;
        private String email;
        private String telephone;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder telephone(String telephone) {
            this.telephone = telephone;
            return this;
        }

        public UserEntity build() {
            return new UserEntity(name, password, email, telephone, true);
        }
    }
}
