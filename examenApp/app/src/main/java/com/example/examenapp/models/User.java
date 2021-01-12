package com.example.examenapp.models;

import java.util.Date;

public class User {
    private int id;
    private String username;
    private Date created;
    private Date updated;

    public User(String username, Date updated, Date created, int id) {
        this.username = username;
        this.updated = updated;
        this.created = created;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }
}
