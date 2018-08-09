package cn.leslie.entity;

import java.io.Serializable;

public class User implements Serializable {
    private Integer user_id;
    private String username;
    private  String password;
    private String email;

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    private int type;
    public User(){

    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




}
