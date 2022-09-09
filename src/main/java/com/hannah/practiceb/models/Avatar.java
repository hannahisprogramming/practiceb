package com.hannah.practiceb.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "avatars")
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
}
