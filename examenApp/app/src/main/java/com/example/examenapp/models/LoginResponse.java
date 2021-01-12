package com.example.examenapp.models;

import java.sql.Date;

public class LoginResponse {
    private Date datenow;
    private String token;

    public Date getDatenow() {
        return datenow;
    }

    public void setDatenow(Date datenow) {
        this.datenow = datenow;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}