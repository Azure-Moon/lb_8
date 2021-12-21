package com.example.lb5;

public class UserInfo {
    private int id;
    private String email;
    private String login;
    private String pass;

    public UserInfo(int id, String email, String login, String pass) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.pass = pass;
    }

    public UserInfo(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
