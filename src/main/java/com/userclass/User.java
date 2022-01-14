package com.userclass;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class User {
    @Id private Integer id;
    private String name;
    private String email;
    private String avatarURI;
    private String status;

    public User() {
    }

    public User(Integer id, String name,String email,String avatarURI,String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.avatarURI = avatarURI;
        this.status = Objects.requireNonNullElse(status, "Offline");
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getAvatarURI() {
        return avatarURI;
    }

    public void setAvatarURI(String avatarURI) {
        this.avatarURI = avatarURI;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
