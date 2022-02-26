package com.k2tech.ehcacheprac.data.dao;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AuthGroup {
    @Id
    private int id;
    private String authGroup;
    private String userName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthGroup() {
        return authGroup;
    }

    public void setAuthGroup(String authGroup) {
        this.authGroup = authGroup;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
