package com.hannah.practiceb.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "avatarTypes")
public class AvatarType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private  String type;
    private int health;
    private String attack;
    private int attackDamage;
}
