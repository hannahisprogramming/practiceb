package com.hannah.practiceb.models;

import javax.persistence.*;

public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @JoinTable(name = "avatarTypes", joinColumns = {
            @JoinColumn(name = "type", referencedColumnName = "id", nullable = false) })
    @ManyToMany(fetch = FetchType.LAZY)
    private String type;
    private int level;

    public Avatar() {
        this.id = 01;
        this.name = "";
        this.type = "";
        this.level = 1;
    }

    public Avatar(String name, String type, int level) {
        this.id = 0;
        this.name = name;
        this.type = type;
        this.level = level;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
